package com.example.cafebackend.service;

import com.example.cafebackend.exception.BaseException;
import com.example.cafebackend.exception.ProductException;
import com.example.cafebackend.repository.ProductBaseRepository;
import com.example.cafebackend.table.MaterialUsed;
import com.example.cafebackend.table.ProductBase;
import com.example.cafebackend.table.ProductForm;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductBaseService {

    private final ProductBaseRepository productBaseRepository;

    private final MaterialUsedService materialUsedService;

    private final ProductFormService productFormService;

    public ProductBaseService(ProductBaseRepository productBaseRepository, MaterialUsedService materialUsedService,
            ProductFormService productFormService) {
        this.productBaseRepository = productBaseRepository;
        this.materialUsedService = materialUsedService;
        this.productFormService = productFormService;
    }

    //////////////////////////////////////////////////////////
    public ProductBase createProductBase(String prodTitleTh, String prodTitleEng) throws BaseException {
        /// verify
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = "PB" + uuid.substring(0, 13);
        /// save
        ProductBase table = new ProductBase();
        table.setProdBaseId(uuid);
        table.setProdTitleTh(prodTitleTh);
        table.setProdTitleEng(prodTitleEng);
        table.setImage("none");
        table.setIsEnable(true);
        table.setIsMaterialEnable(true);
        table.setDescription("none");
        table.setIsDelete(false);
        return productBaseRepository.save(table);
    }
    //////////////////////////////////

    public ProductBase updateProductBase(ProductBase productBase) throws BaseException {
        /// verify
        if (Objects.isNull(productBase))
            throw ProductException.findBaseFail();
        /// save
        return productBaseRepository.save(productBase);
    }
    //////////////////////////////////

    public Optional<ProductBase> findBaseById(String id) {
        ///
        return productBaseRepository.findProdBaseById(id);
    }

    public Boolean findBaseDeleteById(String id) {
        ///
        return productBaseRepository.findProdBaseDeleteById(id);
    }

    public List<ProductBase> findBaseAll() {
        ///
        return productBaseRepository.findProdBaseAll();
    }

    public List<ProductBase> findBaseAllASC() {
        ///
        return productBaseRepository.findProdBaseAll();
    }

    public List<ProductBase> findBaseAllASCPageable(Pageable pageable) {
        ///
        return productBaseRepository.findProdBaseAllPageable(pageable).getContent();
    }

    public List<ProductBase> findBaseAllASCByCateIdPageable(String cateId, Pageable pageable) {
        ///
        return productBaseRepository.findProdBaseByCateId(cateId, pageable).getContent();
    }
    //////////////////////////////////

    public List<ProductBase> findBaseByMateId(String mateId) {
        ///
        return productBaseRepository.findProdBaseByMateId(mateId);
    }
    //////////////////////////////////

    public Optional<ProductBase> findByTitleTh(String title) {
        /// validate
        return productBaseRepository.findByProdTitleTh(title);
    }

    public Boolean checkExistsByTitleTh(String title) {
        /// validate
        return productBaseRepository.existsByProdTitleTh(title);
    }

    public Boolean checkExistsByTitleEng(String title) {
        /// validate
        return productBaseRepository.existsByProdTitleEng(title);
    }

    public Boolean checkDeleteByTitleTh(String title) {
        /// validate
        return productBaseRepository.findProdBaseDeleteByTitleTh(title);
    }

    public Boolean checkDeleteByTitleEng(String title) {
        /// validate
        return productBaseRepository.findProdBaseDeleteByTitleEng(title);
    }
    //////////////////////////////////

    public Boolean deleteProductBase(String id) throws BaseException {
        // verify
        Optional<ProductBase> base = productBaseRepository.findById(id);
        ProductBase baseDelete = base.get();
        List<MaterialUsed> listDeleteMateUse = new ArrayList<>();
        baseDelete.getMaterialUsed().forEach(e -> listDeleteMateUse.add(e));
        for (MaterialUsed mateUse : listDeleteMateUse) {
            materialUsedService.deleteMaterialUsed(mateUse.getMateUsedId());
        }
        List<ProductForm> listDeleteForm = new ArrayList<>();
        baseDelete.getProductForms().forEach(e -> listDeleteForm.add(e));
        for (ProductForm form : listDeleteForm) {
            productFormService.deleteFormById(form.getProdFormId());
        }

        productBaseRepository.deleteById(id);
        Optional<ProductBase> prodOpt = productBaseRepository.findById(id);
        if (prodOpt.isEmpty()) {
            return true;
        }
        throw ProductException.deleteFail();

    }
    //////////////////////////////////

}
