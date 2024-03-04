package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.ProductFormRepository;
import com.example.cafebackend.table.*;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductFormService {

    private final ProductFormRepository productFormRepository;

    private final MaterialUsedService materialUsedService;

    public ProductFormService(ProductFormRepository productFormRepository, MaterialUsedService materialUsedService) {
        this.productFormRepository = productFormRepository;
        this.materialUsedService = materialUsedService;

    }

    //////////////////////////////////////////////////////////
    public ProductForm createProductForm(ProductBase prod, String prodFormTh, String prodFormEng, Double prodPrice,
            String description)
            throws BaseException {
        /// verify
        // String uuid = UUID.randomUUID().toString().replace("-", "");
        // uuid = "PF" + uuid.substring(0, 13);
        /// save
        ProductForm table = new ProductForm();
        table.setProductBase(prod);
        // table.setProdFormId(uuid);
        table.setProdFormTh(prodFormTh);
        table.setProdFormEng(prodFormEng);
        table.setIsEnable(true);
        table.setIsMaterialEnable(true);
        table.setPrice(prodPrice);
        table.setDescription(description);
        table.setIsDelete(false);
        return productFormRepository.save(table);
    }
    //////////////////////////////////

    public ProductForm updateProductForm(ProductForm prod) throws BaseException {
        /// verify
        if (Objects.isNull(prod))
            throw ProductException.updateFailProductNull();
        /// save
        return productFormRepository.save(prod);

    }
    //////////////////////////////////////////////////////// checkExists

    public Boolean checkExistsByFormTh(String form) {
        /// validate
        return productFormRepository.existsByProdFormTh(form);
    }

    public Boolean checkExistsByFormEng(String form) {
        /// validate
        return productFormRepository.existsByProdFormEng(form);
    }
    ////////////////////////////////////////////////////////

    public Optional<ProductForm> findProductFormById(Long prodId) {
        ///
        return productFormRepository.findProductFormById(prodId);
    }

    public List<ProductForm> findProductFormAll() {
        /// search
        return productFormRepository.findProductFormAllASC();
    }

    public List<ProductForm> findProductFormAllPageable(Pageable pageable) {
        /// search
        return productFormRepository.findProductFormAllASCPageable(pageable).getContent();
    }

    public List<ProductForm> findProductFormByBaseId(String baseId) {
        ///
        return productFormRepository.findProductFormByBaseId(baseId);
    }

    public List<ProductForm> findProductFormByBaseIdPageable(String baseId, Pageable pageable) {
        ///
        return productFormRepository.findProductFormByBaseId(baseId, pageable).getContent();
    }

    public List<String> findFormThByBaseId(String baseId) {
        ///
        return productFormRepository.findFormThByBaseId(baseId);
    }

    public List<ProductForm> findFormByMateId(String mateId) {
        ///
        return productFormRepository.findProductFormByMateId(mateId);
    }

    public Double findProductMinPriceByBaseId(String prodId) {
        ///
        return productFormRepository.findMinPrice(prodId);
    }
    ////////////////////////////////////////////////////////

    public Boolean deleteFormById(Long id) throws BaseException {
        /// verify
        Optional<ProductForm> form = productFormRepository.findById(id);
        ProductForm formDelete = form.get();
        List<MaterialUsed> listDeleteMateUse = new ArrayList<>();
        formDelete.getMaterialUsed().forEach(e -> listDeleteMateUse.add(e));
        for (MaterialUsed mateUse : listDeleteMateUse) {
            materialUsedService.deleteMaterialUsed(mateUse.getMateUsedId());
        }
        
        productFormRepository.deleteById(id);
        Optional<ProductForm> product = productFormRepository.findById(id);
        if (product.isEmpty())
            return true;
        throw ProductException.deleteFail();

        // formDelete.setIsDelete(true);
        // formDelete.setIsEnable(false);

        // formDelete.setProductBase(null);
        // formDelete.setAddOn(null);
        // productFormRepository.save(formDelete);
        // return true;
    }

    //////////////////////////////////

}
