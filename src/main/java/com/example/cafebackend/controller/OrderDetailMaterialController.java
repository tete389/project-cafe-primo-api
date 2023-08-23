package com.example.cafebackend.controller;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class OrderDetailMaterialController {

    private OrderDetailMaterialService orderDetailMaterialService;


    ////////////////////////////////////////////////

    public List<OrderDetailMaterial> getAddProdRecord(){
        ///
        return orderDetailMaterialService.findAllDetail();
    }
    ////////////////////////////////////////////////

    public Optional<OrderDetailMaterial> getByOrderId(String id){
        ///
        return orderDetailMaterialService.findById(id);
    }
    ////////////////////////////////////////////////

    public OrderDetailMaterial createOrderDetailMaterial(Order order, String idMate, List<OrderDetailMaterial> orderDetailMaterial) throws BaseException {
        OrderDetailMaterial saveMateRc = new OrderDetailMaterial();
        List<OrderDetailMaterial> removeMateRc = new ArrayList<>();
        double useCount = 0.0;
        saveMateRc.setMateId(idMate);
        for (OrderDetailMaterial mateRc : orderDetailMaterial){
            if(mateRc.getMateId().equals(idMate)) {
                useCount = useCount + mateRc.getAmountUsed();
                saveMateRc.setAmountUsed(useCount);
                saveMateRc.setMateUnit(mateRc.getMateUnit());
                saveMateRc.setMateName(mateRc.getMateName());
                removeMateRc.add(mateRc);
            }
        }
        orderDetailMaterial.removeAll(removeMateRc);

        /// save order
        saveMateRc.setOrder(order);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "ODM"+uuid.substring(0, 14);
        saveMateRc.setOdtMateId(uuid);
        /// save
        return orderDetailMaterialService.updateOrderDetailMaterial(saveMateRc);
    }
    ////////////////////////////////////////////////////

    public boolean clearProductRecordInOrder(Order order) throws BaseException {
        /// validate
        List<OrderDetailMaterial> orderDetailMaterial = order.getOrderDetailMaterials();
        if(orderDetailMaterial.isEmpty()) return true;
        ///
        List<String> OdtMateIdList = new ArrayList<>();
        for (OrderDetailMaterial detailMaterial : orderDetailMaterial) {
            OdtMateIdList.add(detailMaterial.getOdtMateId());
        }
        for (String OdtMate : OdtMateIdList) {
            orderDetailMaterialService.deleteOrderDetailMaterial(OdtMate);
            Optional<OrderDetailMaterial> detailMaterial = orderDetailMaterialService.findById(OdtMate);
            if (!(Objects.isNull(detailMaterial) || detailMaterial.isEmpty())) return false;
        }
        return true;
    }
}
