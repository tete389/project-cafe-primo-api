package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.BaseProductRepository;
import com.example.cafebackend.table.BaseProduct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BaseProductService {

    private final BaseProductRepository baseProductRepository;

    public BaseProductService(BaseProductRepository baseProductRepository) {
        this.baseProductRepository = baseProductRepository;
    }


    //////////////////////////////////////////////////////////
    public BaseProduct createBaseProduct(String prodTitle, String description) throws BaseException {
        /// verify
        if(baseProductRepository.existsByProdTitle(prodTitle)) throw ProductException.createFailTitleDuplicate();
//        String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
//        String[] arrOfStr = timeStamp.split(" ", 5);
//        String[] arrOfStr2 = arrOfStr[0].split("-",5);
//        String[] arrOfStr3 = arrOfStr[1].split(":",5);
//        String id = "P"+arrOfStr3[2]+arrOfStr3[1]+arrOfStr3[0]+arrOfStr2[0]+arrOfStr2[1];
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        String n1 = String.valueOf(1000 + now.get(Calendar.SECOND) * now.get(Calendar.MINUTE));
        String n2 = String.valueOf(100 + now.get(Calendar.SECOND)+ now.get(Calendar.MINUTE));
        String Id = "BP"+n1+n2;
        /// save
        BaseProduct table = new BaseProduct();
        table.setBaseProdId(Id);
        table.setProdTitle(prodTitle);
        table.setIsEnable(true);
        table.setDescription(description);
        return baseProductRepository.save(table);
    }

    public Optional<BaseProduct> findBaseById(String id){
        ///
        return baseProductRepository.findById(id);
    }

    public List<BaseProduct> findListBase(){
        ///
        return baseProductRepository.findAllBaseProduct();
    }

    public BaseProduct updateBaseProduct(BaseProduct baseProduct) throws BaseException {
        /// verify
        if(Objects.isNull(baseProduct)) throw ProductException.findBaseFail();
        /// save
        return baseProductRepository.save(baseProduct);
    }

    public Boolean checkExistsByTitle(String title){
        /// validate
        return baseProductRepository.existsByProdTitle(title);
    }

    //////////////////////////////////
    public Boolean deleteBaseProduct(String id) throws BaseException {
        /// verify
        baseProductRepository.deleteById(id);
        Optional<BaseProduct> prodOpt = baseProductRepository.findById(id);
        if(prodOpt.isEmpty()){
            return true;
        }
        throw ProductException.deleteFail();

    }

    //////////////////////////////////

}
