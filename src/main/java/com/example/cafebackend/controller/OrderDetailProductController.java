package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.OptionException;
import com.example.cafebackend.exception.OrderException;
import com.example.cafebackend.model.request.ProdRequest;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderDetailProductController {

    private OrderDetailProductService orderDetailProductService;

    private OrderDetailOptionService orderDetailOptionService;

    private ProductFormService productFormService;

    private OptionService optionService;


    ////////////////////////////////////////////////

    public OrderDetailProduct createOrderDetailProduct(Order order, ProdRequest request, Order keepMateUse) throws BaseException {
        /// validate product
        Optional<ProductForm> product = productFormService.findProductFormById(request.getProdFormId());
        if(product.isEmpty()) throw OrderException.createFail();
        ProductForm prod = product.get();
        /// add order
        OrderDetailProduct orderDetailProduct = orderDetailProductService.createOrderDetailProduct(order);
        /// price
        double prodOptionPrice = prod.getPrice();
        /// check material used prod base
        if (!prod.getProductBase().getMaterialUsed().isEmpty()) {
            OrderDetailMaterial orderDetailMaterial = new OrderDetailMaterial();
            for (MaterialUsed mateUse : prod.getProductBase().getMaterialUsed()){
                keepMateUse.getOrderDetailMaterials().add(setMateUse(orderDetailMaterial, mateUse));
            }
        }
        /// check material used prod form
        if (!prod.getMaterialUsed().isEmpty()) {
            OrderDetailMaterial orderDetailMaterial = new OrderDetailMaterial();
            for (MaterialUsed mateUse : prod.getMaterialUsed()){
                keepMateUse.getOrderDetailMaterials().add(setMateUse(orderDetailMaterial, mateUse));
            }
        }
        /// check options
        if(!request.getOptions().isEmpty()){
            for(Option optionRequest : request.getOptions()){
                Optional<Option> option = optionService.findOptionById(optionRequest.getOptionId());
                if(option.isEmpty()) throw OptionException.findFail();
                Option opt = option.get();
                /// create odt option and add odt prod
                OrderDetailOption orderDetailOption = orderDetailOptionService.createOrderDetailOption(orderDetailProduct, opt.getOptionName(), opt.getPrice());
                /// check options price
                prodOptionPrice = prodOptionPrice + opt.getPrice();
                /// add odt option
                orderDetailProduct.getOrderDetailOptions().add(orderDetailOption);
                /// check material used option
                if (!opt.getMaterialUsed().isEmpty()) {
                    OrderDetailMaterial orderDetailMaterial = new OrderDetailMaterial();
                    for (MaterialUsed mateUse : opt.getMaterialUsed()){
                        keepMateUse.getOrderDetailMaterials().add(setMateUse(orderDetailMaterial, mateUse));
                    }
                }
            }
        }
        /// check quantity
        Double quantity = Double.valueOf(request.getQuantity());
        if(quantity <= 0 ) quantity = 1.0;
        /// check detail price
        double detailPrice = prodOptionPrice * quantity;
        /// check bonus point
        double bonusPoint = prod.getBonusPoint() * quantity;
        /// check product name
        String prodName =  prod.getProductBase().getProdTitle() + prod.getProdForm();
        /// add data
        orderDetailProduct.setProdName(prodName);
        orderDetailProduct.setProdPrice(prod.getPrice());
        orderDetailProduct.setQuantity(quantity);
        orderDetailProduct.setProdOptionPrice(prodOptionPrice);
        orderDetailProduct.setDetailPrice(detailPrice);
        orderDetailProduct.setBonusPoint(bonusPoint);
        orderDetailProduct.setProdFormId(prod.getProdFormId());
        return orderDetailProductService.updaterOderDetailProduct(orderDetailProduct);
    }
    ////////////////////////////////////////////////////

    public boolean clearOrderDetailProductInOrder(Order order) throws BaseException {
        /// validate
        List<OrderDetailProduct> orderDetailProduct = order.getOrderDetailProducts();
        if(orderDetailProduct.isEmpty()) return true;
        /// keep date to clear
        List<String> OdtOptionIdList = new ArrayList<>();
        List<String> OdtProdIdList = new ArrayList<>();
        for (OrderDetailProduct detailProduct : orderDetailProduct) {
            List<OrderDetailOption> detailOptionList = detailProduct.getOrderDetailOptions();
            for(OrderDetailOption detailOption :  detailOptionList) {
                OdtOptionIdList.add(detailOption.getOdtOptionId());
            }
            OdtProdIdList.add(detailProduct.getOdtProdId());
        }
        /// clear odt prod and odt option
        for (String OdtOption: OdtOptionIdList) {
            orderDetailOptionService.deleteOrderDetailOption(OdtOption);
        }
        for (String OdtProd : OdtProdIdList) {
            orderDetailProductService.deleteOderDetailProduct(OdtProd);
            Optional<OrderDetailProduct> deleteProdRec = orderDetailProductService.findById(OdtProd);
            if (!(Objects.isNull(deleteProdRec) || deleteProdRec.isEmpty())) return false;
        }
        return true;
    }

    public OrderDetailMaterial setMateUse(OrderDetailMaterial orderDetailMaterial, MaterialUsed materialUsed) {
        orderDetailMaterial.setMateName(materialUsed.getMaterial().getMateName());
        orderDetailMaterial.setAmountUsed(materialUsed.getAmountUsed());
        orderDetailMaterial.setMateUnit(materialUsed.getMaterial().getMateUnit());
        orderDetailMaterial.setMateId(materialUsed.getMaterial().getMateId());
        return orderDetailMaterial;
    }
}
