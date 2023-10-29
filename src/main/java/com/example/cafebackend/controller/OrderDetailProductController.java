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

    private ProductBaseService productBaseService;

    private OptionService optionService;

    ////////////////////////////////////////////////

    public OrderDetailProduct createOrderDetailProduct(Order order, ProdRequest request,
            List<OrderDetailMaterial> keepMateUse) throws BaseException {
        /// validate product
        Optional<ProductForm> product = productFormService.findProductFormById(request.getProdFormId());
        if (product.isEmpty())
            throw OrderException.createFail();
        ProductForm prod = product.get();
        /// add order
        OrderDetailProduct orderDetailProduct = orderDetailProductService.createOrderDetailProduct(order);
        /// option price
        double optionPrice = 0.0;
        /// check material used prod base
        Optional<ProductBase> baseOpt = productBaseService.findBaseById(prod.getProductBase().getProdBaseId());
        ProductBase base = baseOpt.get();

        /// check quantity
        double quantity = Double.parseDouble(request.getQuantity());
        if (quantity <= 0)
            quantity = 1.0;

        if (!base.getMaterialUsed().isEmpty()) {
            for (MaterialUsed mateUse : base.getMaterialUsed()) {
                keepMateUse.add(setMateUse(mateUse, quantity));
            }
        }
        /// check material used prod form
        if (!prod.getMaterialUsed().isEmpty()) {
            for (MaterialUsed mateUse : prod.getMaterialUsed()) {
                keepMateUse.add(setMateUse(mateUse, quantity));
            }
        }
        /// check options
        if (!request.getOptions().isEmpty()) {
            for (Option optionRequest : request.getOptions()) {
                Optional<Option> option = optionService.findOptionById(optionRequest.getOptionId());
                if (option.isEmpty())
                    throw OptionException.findFail();
                Option opt = option.get();
                /// create odt option and add odt prod
                OrderDetailOption orderDetailOption = orderDetailOptionService.createOrderDetailOption(
                        orderDetailProduct, opt.getOptionNameTh(), opt.getOptionNameEng(), opt.getPrice(),
                        opt.getOptionId());
                /// check options price
                optionPrice = optionPrice + opt.getPrice();
                /// add odt option
                orderDetailProduct.getOrderDetailOptions().add(orderDetailOption);
                /// check material used option
                if (!opt.getMaterialUsed().isEmpty()) {
                    for (MaterialUsed mateUse : opt.getMaterialUsed()) {
                        keepMateUse.add(setMateUse(mateUse, quantity));
                    }
                }
            }
        }

        /// check detail price
        Double detailPrice = (optionPrice + prod.getPrice()) * quantity;
        /// check product name
        String prodNameTh = prod.getProductBase().getProdTitleTh() + " " + prod.getProdFormTh();
        String prodNameEng = prod.getProductBase().getProdTitleEng() + " " + prod.getProdFormEng();
        /// add data
        orderDetailProduct.setProdNameTh(prodNameTh);
        orderDetailProduct.setProdNameEng(prodNameEng);
        orderDetailProduct.setProdPrice(prod.getPrice());
        orderDetailProduct.setQuantity(quantity);
        orderDetailProduct.setOptionPrice(optionPrice);
        orderDetailProduct.setDetailPrice(detailPrice);
        orderDetailProduct.setProdFormId(prod.getProdFormId());
        return orderDetailProductService.updaterOderDetailProduct(orderDetailProduct);
    }
    ////////////////////////////////////////////////////

    public boolean clearOrderDetailProductInOrder(Order order) throws BaseException {
        /// validate
        List<OrderDetailProduct> orderDetailProduct = order.getOrderDetailProducts();
        if (orderDetailProduct.isEmpty())
            return true;
        /// keep date to clear
        List<String> OdtOptionIdList = new ArrayList<>();
        List<String> OdtProdIdList = new ArrayList<>();
        for (OrderDetailProduct detailProduct : orderDetailProduct) {
            List<OrderDetailOption> detailOptionList = detailProduct.getOrderDetailOptions();
            for (OrderDetailOption detailOption : detailOptionList) {
                OdtOptionIdList.add(detailOption.getOdtOptionId());
            }
            OdtProdIdList.add(detailProduct.getOdtProdId());
        }
        /// clear odt prod and odt option
        for (String OdtOption : OdtOptionIdList) {
            orderDetailOptionService.deleteOrderDetailOption(OdtOption);
        }
        for (String OdtProd : OdtProdIdList) {
            orderDetailProductService.deleteOderDetailProduct(OdtProd);
            Optional<OrderDetailProduct> deleteProdRec = orderDetailProductService.findById(OdtProd);
            if (!(Objects.isNull(deleteProdRec) || deleteProdRec.isEmpty()))
                return false;
        }
        return true;
    }

    public OrderDetailMaterial setMateUse(MaterialUsed materialUsed,
            Double quantity) {
        OrderDetailMaterial orderDetailMaterial = new OrderDetailMaterial();
        orderDetailMaterial.setMateName(materialUsed.getMaterial().getMateName());
        orderDetailMaterial.setAmountUsed(materialUsed.getAmountUsed() * quantity);
        orderDetailMaterial.setMateUnit(materialUsed.getMaterial().getMateUnit());
        orderDetailMaterial.setMateId(materialUsed.getMaterial().getMateId());
        return orderDetailMaterial;
    }
}
