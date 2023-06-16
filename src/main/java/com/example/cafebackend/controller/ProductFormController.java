package com.example.cafebackend.controller;

import com.example.cafebackend.exception.*;
import com.example.cafebackend.mapper.ProductMapper;
import com.example.cafebackend.model.response.*;
import com.example.cafebackend.service.*;
import com.example.cafebackend.table.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@AllArgsConstructor
@Service
public class ProductFormController {

    private ProductFormService productFormService;

    private ProductBaseService productBaseService;

    private CategoryService categoryService;

    private MaterialService materialService;

    private AddOnService addOnService;

    //private IngredientService ingredientService;

    private ProductMapper productMapper;

    public static String uploadDirectory = System.getProperty("user.dir");


    //////////////////////////////////////////////////////////////////////////

    public MessageResponse createProductForm(String baseId, String prodForm, String prodPrice, String bonusPoint, String description) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty()) throw ProductException.createFailRequestBaseNull();
        if(Objects.isNull(prodForm) || prodForm.isEmpty()) throw ProductException.createFailRequestFormNull();
        if(Objects.isNull(prodPrice)) throw ProductException.createFailPriceRequestNull();
        if(Objects.isNull(bonusPoint)) throw ProductException.createFailRequestFormNull();
        if(Objects.isNull(description) || description.isEmpty()) throw ProductException.createFailRequestFormNull();
        /// verify
        Optional<ProductBase> productBase = productBaseService.findBaseById(baseId);
        if(productBase.isEmpty()) throw ProductException.findBaseFail();
        ProductBase base = productBase.get();
        /// check product form
        if(productFormService.checkExistsByForm(prodForm)) {
            for(ProductForm listForm : base.getProductForms())
                if (listForm.getProdForm().equals(prodForm)) throw ProductException.createFailFormDuplicate();
        }
        /// create product form
        Double setPrice = Double.valueOf(prodPrice);
        Double setBonusPoint = Double.valueOf(bonusPoint);
        ProductForm productForm = productFormService.createProductForm(base, prodForm, setPrice, setBonusPoint, description);
        /// set response
        ForProductOnlyResponse prodOnly = productMapper.toProductFormOnlyResponse(productForm);
        MessageResponse res = new MessageResponse();
        res.setMessage("add product form success");
        res.setRes(prodOnly);
        return res;
    }

    public MessageResponse updateProductForm(String formId, String prodForm, String prodPrice, String bonusPoint, String description, String isEnable) throws Exception{
        /// validate
        if(Objects.isNull(formId) || formId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(prodForm) || prodForm.isEmpty()) throw ProductException.findFailRequestFormNull();
        if(Objects.isNull(prodPrice) || prodPrice.isEmpty()) throw ProductException.findFailRequestPriceNull();
        if(Objects.isNull(bonusPoint) || bonusPoint.isEmpty()) throw ProductException.findFailRequestPointNull();
        if(Objects.isNull(description) || description.isEmpty()) throw ProductException.findProductFail();
        if(Objects.isNull(isEnable) || isEnable.isEmpty()) throw ProductException.findFailRequestEnableNull();
        /// verify
        Optional<ProductForm> prodOpt = productFormService.findProductFormById(formId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        ProductForm productForm = prodOpt.get();
        /// check product form
        if(!prodForm.equals(productForm.getProdForm())) {
            for(ProductForm listForm : productForm.getProductBase().getProductForms())
                if (listForm.getProdForm().equals(prodForm)) throw ProductException.updateFailFormDuplicate();
            productForm.setProdForm(prodForm);
        }
        /// check price
        Double setPrice = Double.valueOf(prodPrice);
        if(!setPrice.equals(productForm.getPrice())) {
            productForm.setPrice(setPrice);
        }
        /// check bonus point
        Double setBonusPoint = Double.valueOf(bonusPoint);
        if(!setBonusPoint.equals(productForm.getBonusPoint())) {
            productForm.setPrice(setBonusPoint);
        }
        /// check description
        if(!description.equals(productForm.getDescription())) {
            productForm.setDescription(description);
        }
        /// check isEnable
        if(isEnable.equals("true")){
            if(productForm.getIsEnable().equals(false)) productForm.setIsEnable(true);
        } else if (isEnable.equals("false")) {
            if(productForm.getIsEnable().equals(true)) productForm.setIsEnable(false);
        }
        /// update product form
        ProductForm prod = productFormService.updateProduct(productForm);
        /// set response
        ForProductOnlyResponse prodOnly = productMapper.toProductFormOnlyResponse(prod);
        MessageResponse res = new MessageResponse();
        res.setMessage("update product form success");
        res.setRes(prodOnly);
        return res;
    }


//    public MessageResponse setFormProduct(String prodId, String newForm) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(newForm) || newForm.isEmpty()) throw ProductException.findFailRequestFormNull();
//        /// verify
//        Optional<ProductForm> prodOpt = productFormService.findProductById(prodId);
//        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
//        ProductForm productForm = prodOpt.get();
//        /// check new product name and form
//        String newProdName = productForm.getProductBase().getProdTitle() + newForm;
//        if (productFormService.checkExistsByName(newProdName)) throw ProductException.createFailFormDuplicate();
//        /// save new product name and form
//        productForm.setProdForm(newForm);
//        //productForm.setProdName(newProdName);
//        ProductForm prod = productFormService.updateProduct(productForm);
//        /// set response
//        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set form success");
//        res.setRes(prodOnly);
//        return res;
//    }
//
//
//    public MessageResponse setDescriptionProduct(String prodId, String description) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(description) || description.isEmpty()) description = "none";
//        /// verify
//        Optional<ProductForm> prodOpt = productFormService.findProductById(prodId);
//        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
//        ProductForm productForm = prodOpt.get();
//        /// save new description
//        productForm.setDescription(description);
//        ProductForm prod = productFormService.updateProduct(productForm);
//        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
//        /// set response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("save description success");
//        res.setRes(prodOnly);
//        return res;
//    }
//
//
//    public MessageResponse setBonusPointProduct(String prodId, Double point) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(point)) point = 0.0;
//        /// verify
//        Optional<ProductForm> prodOpt = productFormService.findProductById(prodId);
//        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
//        ProductForm productForm = prodOpt.get();
//        /// save new bonus point
//        productForm.setBonusPoint(point);
//        ProductForm prod = productFormService.updateProduct(productForm);
//        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
//        /// set response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set bonus point success");
//        res.setRes(prodOnly);
//        return res;
//    }


//    public MessageResponse setForSaleProduct(String prodId, Boolean forSale) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(forSale)) throw ProductException.findFailRequestForSaleNull();
//        /// verify
//        Optional<ProductForm> prodOpt = productFormService.findProductById(prodId);
//        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
//        ProductForm productForm = prodOpt.get();
//        /// set boolean forSale
//        productForm.setIsForSale(forSale);
//        ProductForm prod = productFormService.updateProduct(productForm);
//        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
//        /// set response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set isForSale success");
//        res.setRes(prodOnly);
//        return res;
//    }


//    public MessageResponse setEnableProduct(String prodId, Boolean enable) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(enable)) throw ProductException.findFailRequestEnableNull();
//        /// verify
//        Optional<ProductForm> prodOpt = productFormService.findProductById(prodId);
//        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
//        ProductForm productForm = prodOpt.get();
//        /// set boolean enable
//        productForm.setIsEnable(enable);
//        ProductForm prod = productFormService.updateProduct(productForm);
//        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
//        /// set response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set isEnable success");
//        res.setRes(prodOnly);
//        return res;
//    }


//    public MessageResponse setPriceProduct(String prodId, Double newPrice) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(newPrice)) throw ProductException.findFailRequestPriceNull();
//        /// verify
//        Optional<ProductForm> prodOpt = productFormService.findProductById(prodId);
//        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
//        ProductForm productForm = prodOpt.get();
//        /// save new price
//        productForm.setPrice(newPrice);
//        ProductForm prod = productFormService.updateProduct(productForm);
//        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
//        /// set response
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set price success");
//        res.setRes(prodOnly);
//        return res;
//    }


    public MessageResponse uploadImage(String formId, MultipartFile image) throws BaseException{
        /// validate
        if(Objects.isNull(formId) || formId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(image == null) throw FileException.fileNull();
        if(image.getSize() > 1048576 * 5 ) throw FileException.fileMaxSize();
        String contentType = image.getContentType();
        if (contentType == null) throw FileException.createFail();
        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
        if(!supportTypes.contains(contentType)) throw FileException.updateFailTypes();

        // TODO * upload to sever

        /// verify
        Optional<ProductForm> product = productFormService.findProductFormById(formId);
        if(product.isEmpty()) throw ProductException.findProductFail();
        //// ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
        MessageResponse res = new MessageResponse();
        res.setMessage("update product form success");
        //res.setRes(prodDetail);
        return res;
    }

    //////////////////////////////////////////////////////////////////////////
//
//    public MessageResponse addInfoCategory(String formId, List<String> listCateId) throws BaseException{
//        /// validate
//        if(Objects.isNull(formId) || formId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(listCateId)|| listCateId.isEmpty()) throw CategoryException.addInfoFailRequestCateNull();
//        /// verify
//        Optional<ProductForm> product = productFormService.findProductFormById(formId);
//        if(product.isEmpty()) throw ProductException.findProductFail();
//        List<Category> categoryList = new ArrayList<>();
//        for (String cateId : listCateId) {
//            Optional<Category> category = categoryService.findById(cateId);
//            if(category.isEmpty()) throw CategoryException.addInfoFailCategoryNull();
//            categoryList.add(category.get());
////            Boolean exists = productService.checkExistsByCategory(cateId);
////            if(!exists) product.get().getCategory().add(category.get());
//        }
//        product.get().getCategory().clear();
//        product.get().getCategory().addAll(categoryList);
//        ProductForm prod = productFormService.updateProduct(product.get());
//        ForProdAndListCategoryResponse prodCate = productMapper.toForProdAndListCategoryResponse(prod);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("add Info Categories success");
//        res.setRes(prodCate);
//        return res;
//    }


//    public MessageResponse delInfoCategory(String prodId, List<String> listCateId) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(listCateId)|| listCateId.isEmpty()) throw CategoryException.delInfoFailRequestCateNull();
//        /// verify
//        Optional<Product> product = productService.findProductById(prodId);
//        if(product.isEmpty()) throw ProductException.findProductFail();
//        for (String cateId : listCateId) {
//            Optional<Category> category = categoryService.findById(cateId);
//            if(category.isEmpty()) throw CategoryException.addInfoFailCategoryNull();
//            product.get().getCategory().remove(category.get());
//        }
//        Product prod = productService.updateProduct(product.get());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("delete Info Categories success");
//        res.setRes(prod);
//        return res;
//    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// Handle addon

    public MessageResponse updateAddOnToForm(String prodId, List<String> listAddOnId) throws Exception{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(listAddOnId)|| listAddOnId.isEmpty()) throw OptionException.addInfoFailRequestAddOnNull();
        /// verify
        Optional<ProductForm> product = productFormService.findProductFormById(prodId);
        if(product.isEmpty()) throw ProductException.findProductFail();
//        for (String addId : listAddOnId) {
//            Optional<AddOn> addOn = addOnService.findAddOnById(addId);
//            if(addOn.isEmpty()) throw OptionException.findFail();
//            Boolean exists = productService.checkExistsByAddOn(addId);
//            if(!exists) product.get().getAddOn().add(addOn.get());
//        }
        List<AddOn> addOnList = new ArrayList<>();
        for (String addId : listAddOnId) {
            Optional<AddOn> addOn = addOnService.findAddOnById(addId);
            if(addOn.isEmpty()) throw OptionException.findFail();
            addOnList.add(addOn.get());
        }
        product.get().getAddOn().clear();
        product.get().getAddOn().addAll(addOnList);
        ProductForm prod = productFormService.updateProduct(product.get());
        ForProdAndListAddOnResponse prodAddon = productMapper.toForProdAndListAddOnResponse(prod);
        MessageResponse res = new MessageResponse();
        res.setMessage("add Info AddOn success");
        res.setRes(prodAddon);
        return res;
    }

//    public MessageResponse delInfoAddOn(String prodId, List<String> listOptionId) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(listOptionId)|| listOptionId.isEmpty()) throw OptionException.delInfoFailRequestOptionNull();
//        /// verify
//        Optional<Product> product = productService.findProductById(prodId);
//        if(product.isEmpty()) throw ProductException.findProductFail();
//        for (String addId : listOptionId) {
//            Optional<AddOn> addOn = addOnService.findAddOnById(addId);
//            if(addOn.isEmpty()) throw OptionException.findFail();
//            product.get().getAddOn().remove(addOn.get());
//        }
//        Product prod = productService.updateProduct(product.get());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("delete Info Options success");
//        res.setRes(prod);
//        return res;
//    }


    //////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////  Handle IngredientAndMaterial
//    public MessageResponse addInfoIngredientAndMaterial(String prodId, String mateId, Double  mateCount, String mateUnit,String description) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(mateId)|| mateId.isEmpty()) throw MaterialException.addInfoFailRequestMateNull();
//        if(Objects.isNull(mateCount)) throw MaterialException.addInfoFailRequestMateNull();
//        if(Objects.isNull(description)|| description.isEmpty()) description = "none";
//        /// verify
//        Optional<ProductForm> product = productFormService.findProductById(prodId);
//        if(product.isEmpty()) throw ProductException.findProductFail();
//        Optional<Material> material = materialService.findById(mateId);
//        if(material.isEmpty()) throw MaterialException.findFail();
//        //
//        Boolean exists = productFormService.checkExistsByMaterial(mateId);
//        if(exists) throw MaterialException.addInfoFailMateDuplicate();
//        Ingredient ingProd = ingredientService.createIngredient(product.get(), material.get(), mateCount, mateUnit, description);
//        //product.get().getIngredients().add(ingProd);
//        //Product prod = productService.updateProduct(product.get());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("add Info Ingredient success");
//        res.setRes(ingProd);
//        return res;
//    }

//    public MessageResponse delInfoIngredientAndMaterial(String prodId, List<String> listMateId) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
//        if(Objects.isNull(listMateId)|| listMateId.isEmpty()) throw MaterialException.delInfoFailRequestMateNull();
//        /// verify
//        Optional<ProductForm> product = productFormService.findProductById(prodId);
//        if(product.isEmpty()) throw ProductException.findProductFail();
//        for (String mateId : listMateId) {
//            Optional<Ingredient> ingProd = ingredientService.findIngredientByIdKey(mateId, prodId);
//            if(ingProd.isEmpty()) throw MaterialException.findFail();
//            product.get().getIngredients().remove(ingProd.get());
//            ingredientService.delIngredient(ingProd.get().getId());
//        }
//        ProductForm prod = productFormService.updateProduct(product.get());
//        ForProdAndListIngResponse prodIng = productMapper.toFoProdAndListIngResponse(prod);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("delete Info Ingredient success");
//        res.setRes(prodIng);
//        return res;
//    }


//    public MessageResponse updateAddMaterialUsed(String mateId, String useCount, String formId) throws BaseException{
//        /// validate
//        if(Objects.isNull(mateId) || mateId.isEmpty()) throw MaterialException.addInfoFailRequestMateNull();
//        if(Objects.isNull(useCount)|| useCount.isEmpty()) throw MaterialException.addInfoFailRequestMateNull();
//        if(Objects.isNull(formId) || formId.isEmpty()) throw MaterialException.updateFail();
//        /// verify
//        Optional<ProductForm> ProductForm = productFormService.findProductFormById(formId);
//        if(productDetail.isEmpty()) throw ProductException.findProductDetailFailDetailNull();
//        for (MateUsedRequest ingRequest : request.getIngredients()) {
//            Optional<Material> material = materialService.findById(ingRequest.getMateId());
//            if(material.isEmpty()) throw MaterialException.findFail();
//            Boolean exists = productDetailService.checkExistsByMaterial(ingRequest.getMateId());
//            if(exists) throw  MaterialException.addInfoFailMateDuplicate();
//            Ingredient ingProd = ingredientService.createIngredient(productDetail.get(), material.get(), ingRequest.getMateCount(), ingRequest.getMateUnit());
//            productDetail.get().getIngredients().add(ingProd);
//        }
//        ProductDetail prodDetail = productDetailService.updateProductDetail(productDetail.get());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("add Info Ingredient & Material success");
//        res.setRes(prodDetail);
//        return res;
//    }
    ////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// find List Product Detail by **

    public MessageResponse findListProductByBaseId(String baseId) throws BaseException {
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty())throw ProductException.findBaseFail();
        /// verify
        Optional<ProductBase> product =  productBaseService.findBaseById(baseId);
        if(product.isEmpty()) throw ProductException.findProductFail();
        List<ProductForm> productForms = productFormService.findProductByBaseId(baseId);
        List<ForProductOnlyResponse> pdList = productMapper.toListProductFormOnlyResponse(productForms);
        MessageResponse res = new MessageResponse();
        res.setMessage("find ListProducts By Base ID");
        res.setRes(pdList);
        return res;
    }





//    public MessageResponse findListProductInfo() {
//        /// verify
//        List<ProductForm> productForm =  productFormService.findListProduct();
//        List<ForProductInfoResponse> pdList = productMapper.toListForProductInfoResponse(productForm);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("find ListProductsInfo");
//        res.setRes(pdList);
//        return res;
//    }
    //////////////////////////////////////////////////////////////// find Product And List **

    public MessageResponse findProductAndListOptionById(String prodId) throws BaseException {
        /// validate
        Optional<ProductForm> p =  productFormService.findProductFormById(prodId);
        if(p.isEmpty()) throw ProductException.findProductFail();
        ForProdAndListAddOnResponse prodRes = productMapper.toForProdAndListAddOnResponse(p.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("find Product And ListAddon By Product ID");
        res.setRes(prodRes);
        return res;
    }

//    public MessageResponse findProductAndListIngredientById(String prodId) throws BaseException {
//        /// validate
//        Optional<ProductForm> p =  productFormService.findProductFormById(prodId);
//        if(p.isEmpty()) throw ProductException.findProductFail();
//        ForProdAndListIngResponse prodRes = productMapper.toFoProdAndListIngResponse(p.get());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("find Product And ListIngredient By Product ID");
//        res.setRes(prodRes);
//        return res;
//    }

    public MessageResponse findProductAndListCategoryById(String prodId) throws BaseException {
        /// validate
        Optional<ProductForm> p =  productFormService.findProductFormById(prodId);
        if(p.isEmpty()) throw ProductException.findProductFail();
        ForProdAndListCategoryResponse prodRes = productMapper.toForProdAndListCategoryResponse(p.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("find Product And ListCategory By Product ID");
        res.setRes(prodRes);
        return res;
    }

    public MessageResponse findProductById(String prodId) throws BaseException {
        /// validate
        Optional<ProductForm> p =  productFormService.findProductFormById(prodId);
        if(p.isEmpty()) throw ProductException.findProductFail();
        ForProductOnlyResponse prodRes = productMapper.toProductFormOnlyResponse(p.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("find Product By ID");
        res.setRes(prodRes);
        return res;
    }
    ////////////////////////////////////////////////////////////////

    public MessageResponse deleteProduct(String prodId) throws BaseException {
        Boolean product =  productFormService.deleteProduct(prodId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete product success");
        res.setRes(product);
        return res;
    }


}
