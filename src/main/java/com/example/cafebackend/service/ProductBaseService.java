package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.ProductBaseRepository;
import com.example.cafebackend.table.ProductBase;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductBaseService {

    private final ProductBaseRepository productBaseRepository;

    public ProductBaseService(ProductBaseRepository productBaseRepository) {
        this.productBaseRepository = productBaseRepository;
    }


    //////////////////////////////////////////////////////////
    public ProductBase createProductBase(String prodTitle) throws BaseException {
        /// verify
        if(productBaseRepository.existsByProdTitle(prodTitle)) throw ProductException.createFailTitleDuplicate();
//        String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
//        String[] arrOfStr = timeStamp.split(" ", 5);
//        String[] arrOfStr2 = arrOfStr[0].split("-",5);
//        String[] arrOfStr3 = arrOfStr[1].split(":",5);
//        String id = "P"+arrOfStr3[2]+arrOfStr3[1]+arrOfStr3[0]+arrOfStr2[0]+arrOfStr2[1];
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        String n1 = String.valueOf(1000 + now.get(Calendar.SECOND) * now.get(Calendar.MINUTE));
        String n2 = String.valueOf(100 + now.get(Calendar.SECOND)+ now.get(Calendar.MINUTE));
        String Id = "PB"+n1+n2;
        /// save
        ProductBase table = new ProductBase();
        table.setProdBaseId(Id);
        table.setProdTitle(prodTitle);
        table.setIsEnable(true);
        table.setDescription("none");
        return productBaseRepository.save(table);
    }
    //////////////////////////////////

    public Optional<ProductBase> findBaseById(String id){
        ///
        return productBaseRepository.findById(id);
    }
    //////////////////////////////////

    public ProductBase updateBaseProduct(ProductBase productBase) throws BaseException {
        /// verify
        if(Objects.isNull(productBase)) throw ProductException.findBaseFail();
        /// save
        return productBaseRepository.save(productBase);
    }
    //////////////////////////////////

    public Boolean checkExistsByTitle(String title){
        /// validate
        return productBaseRepository.existsByProdTitle(title);
    }
    //////////////////////////////////

    public Boolean deleteProductBase(String id) throws BaseException {
        /// verify
        productBaseRepository.deleteById(id);
        Optional<ProductBase> prodOpt = productBaseRepository.findById(id);
        if(prodOpt.isEmpty()){
            return true;
        }
        throw ProductException.deleteFail();

    }
    //////////////////////////////////

}
