package com.example.cafebackend.controller;

import com.example.cafebackend.exception.*;
import com.example.cafebackend.mapper.CategoryMapper;
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
public class ProductController {

    private ProductService productService;

    private BaseProductService baseProductService;

    private CategoryService categoryService;

    private MaterialService materialService;

    private AddOnService addOnService;

    private IngredientService ingredientService;

    private ProductMapper productMapper;

    public static String uploadDirectory = System.getProperty("user.dir");


    //////////////////////////////////////////////////////////////////////////  create edit info
    //////////////////////////////////////////////// Handle Product Detail

    public MessageResponse createProduct(String baseId, String prodForm, Double prodPrice) throws BaseException{
        /// validate
        if(Objects.isNull(baseId) || baseId.isEmpty()) throw ProductException.createFailRequestBaseNull();
        if(Objects.isNull(prodForm) || prodForm.isEmpty()) throw ProductException.createFailRequestFormNull();
        if(Objects.isNull(prodPrice) || prodPrice.isInfinite()) throw ProductException.createFailPriceRequestNull();
        /// verify
        Optional<BaseProduct> baseProduct = baseProductService.findBaseById(baseId);
        if(baseProduct.isEmpty()) throw ProductException.findBaseFail();
        BaseProduct base = baseProduct.get();
        /// check product name and form
        String prodName = base.getProdTitle() +" "+ prodForm;
        /// create product name and form
        Product product = productService.createProduct(base, prodForm, prodName, prodPrice);
        /// set response
        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(product);
        MessageResponse res = new MessageResponse();
        res.setMessage("add product success");
        res.setRes(prodOnly);
        return res;
    }

    public MessageResponse setFormProduct(String prodId, String newForm) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(newForm) || newForm.isEmpty()) throw ProductException.findFailRequestFormNull();
        /// verify
        Optional<Product> prodOpt = productService.findProductById(prodId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        Product product = prodOpt.get();
        /// check new product name and form
        String newProdName = product.getBaseProduct().getProdTitle() +" "+ newForm;
        if (productService.checkExistsByName(newProdName)) throw ProductException.createFailFormDuplicate();
        /// save new product name and form
        product.setProdForm(newForm);
        product.setProdName(newProdName);
        Product prod = productService.updateProduct(product);
        /// set response
        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
        MessageResponse res = new MessageResponse();
        res.setMessage("set form success");
        res.setRes(prodOnly);
        return res;
    }


    public MessageResponse setDescriptionProduct(String prodId, String description) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(description) || description.isEmpty()) description = "none";
        /// verify
        Optional<Product> prodOpt = productService.findProductById(prodId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        Product product = prodOpt.get();
        /// save new description
        product.setDescription(description);
        Product prod = productService.updateProduct(product);
        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("save description success");
        res.setRes(prodOnly);
        return res;
    }


    public MessageResponse setBonusPointProduct(String prodId, Double point) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(point)) point = 0.0;
        /// verify
        Optional<Product> prodOpt = productService.findProductById(prodId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        Product product = prodOpt.get();
        /// save new bonus point
        product.setBonusPoint(point);
        Product prod = productService.updateProduct(product);
        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("set bonus point success");
        res.setRes(prodOnly);
        return res;
    }


    public MessageResponse setForSaleProduct(String prodId, Boolean forSale) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(forSale)) throw ProductException.findFailRequestForSaleNull();
        /// verify
        Optional<Product> prodOpt = productService.findProductById(prodId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        Product product = prodOpt.get();
        /// set boolean forSale
        product.setIsForSale(forSale);
        Product prod = productService.updateProduct(product);
        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("set isForSale success");
        res.setRes(prodOnly);
        return res;
    }


    public MessageResponse setEnableProduct(String prodId, Boolean enable) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(enable)) throw ProductException.findFailRequestEnableNull();
        /// verify
        Optional<Product> prodOpt = productService.findProductById(prodId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        Product product = prodOpt.get();
        /// set boolean enable
        product.setIsEnable(enable);
        Product prod = productService.updateProduct(product);
        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("set isEnable success");
        res.setRes(prodOnly);
        return res;
    }


    public MessageResponse setPriceProduct(String prodId, Double newPrice) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(newPrice)) throw ProductException.findFailRequestPriceNull();
        /// verify
        Optional<Product> prodOpt = productService.findProductById(prodId);
        if(prodOpt.isEmpty()) throw ProductException.findProductFail();
        Product product = prodOpt.get();
        /// save new price
        product.setPrice(newPrice);
        Product prod = productService.updateProduct(product);
        ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
        /// set response
        MessageResponse res = new MessageResponse();
        res.setMessage("set price success");
        res.setRes(prodOnly);
        return res;
    }


    public MessageResponse uploadImageProduct(String prodId, MultipartFile image) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(image == null) throw FileException.fileNull();
        if(image.getSize() > 1048576 * 5 ) throw FileException.fileMaxSize();
        String contentType = image.getContentType();
        if (contentType == null) throw FileException.createFail();
        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
        if(!supportTypes.contains(contentType)) throw FileException.updateFailTypes();

        // TODO * upload to sever

        /// verify
        Optional<Product> product = productService.findProductById(prodId);
        if(product.isEmpty()) throw ProductException.findProductFail();
        //ProductDetail prodDetail = productDetailService.setNewPriceProductDetail(productDetail.get(), newPrice);
        //// ForProductOnlyResponse prodOnly = productMapper.toProductOnlyResponse(prod);
        MessageResponse res = new MessageResponse();
        res.setMessage("set price success");
        //res.setRes(prodDetail);
        return res;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// Handle Category

    public MessageResponse addInfoCategory(String prodId, List<String> listCateId) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(listCateId)|| listCateId.isEmpty()) throw CategoryException.addInfoFailRequestCateNull();
        /// verify
        Optional<Product> product = productService.findProductById(prodId);
        if(product.isEmpty()) throw ProductException.findProductFail();
        List<Category> categoryList = new ArrayList<>();
        for (String cateId : listCateId) {
            Optional<Category> category = categoryService.findById(cateId);
            if(category.isEmpty()) throw CategoryException.addInfoFailCategoryNull();
            categoryList.add(category.get());
//            Boolean exists = productService.checkExistsByCategory(cateId);
//            if(!exists) product.get().getCategory().add(category.get());
        }
        product.get().getCategory().clear();
        product.get().getCategory().addAll(categoryList);
        Product prod = productService.updateProduct(product.get());
        ForProdAndListCategoryResponse prodCate = productMapper.toForProdAndListCategoryResponse(prod);
        MessageResponse res = new MessageResponse();
        res.setMessage("add Info Categories success");
        res.setRes(prodCate);
        return res;
    }


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

    public MessageResponse addInfoAddOn(String prodId, List<String> listAddOnId) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(listAddOnId)|| listAddOnId.isEmpty()) throw OptionException.addInfoFailRequestAddOnNull();
        /// verify
        Optional<Product> product = productService.findProductById(prodId);
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
        Product prod = productService.updateProduct(product.get());
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
    public MessageResponse addInfoIngredientAndMaterial(String prodId, String mateId, Double  mateCount, String mateUnit,String description) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(mateId)|| mateId.isEmpty()) throw MaterialException.addInfoFailRequestMateNull();
        if(Objects.isNull(mateCount)) throw MaterialException.addInfoFailRequestMateNull();
        if(Objects.isNull(description)|| description.isEmpty()) description = "none";
        /// verify
        Optional<Product> product = productService.findProductById(prodId);
        if(product.isEmpty()) throw ProductException.findProductFail();
        Optional<Material> material = materialService.findById(mateId);
        if(material.isEmpty()) throw MaterialException.findFail();
        //
        Boolean exists = productService.checkExistsByMaterial(mateId);
        if(exists) throw MaterialException.addInfoFailMateDuplicate();
        Ingredient ingProd = ingredientService.createIngredient(product.get(), material.get(), mateCount, mateUnit, description);
        //product.get().getIngredients().add(ingProd);
        //Product prod = productService.updateProduct(product.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("add Info Ingredient success");
        res.setRes(ingProd);
        return res;
    }

    public MessageResponse delInfoIngredientAndMaterial(String prodId, List<String> listMateId) throws BaseException{
        /// validate
        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.findFailRequestProductIdNull();
        if(Objects.isNull(listMateId)|| listMateId.isEmpty()) throw MaterialException.delInfoFailRequestMateNull();
        /// verify
        Optional<Product> product = productService.findProductById(prodId);
        if(product.isEmpty()) throw ProductException.findProductFail();
        for (String mateId : listMateId) {
            Optional<Ingredient> ingProd = ingredientService.findIngredientByIdKey(mateId, prodId);
            if(ingProd.isEmpty()) throw MaterialException.findFail();
            product.get().getIngredients().remove(ingProd.get());
            ingredientService.delIngredient(ingProd.get().getId());
        }
        Product prod = productService.updateProduct(product.get());
        ForProdAndListIngResponse prodIng = productMapper.toFoProdAndListIngResponse(prod);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete Info Ingredient success");
        res.setRes(prodIng);
        return res;
    }


//    public MessageResponse updateInfoIngredientAndMaterial(IngredientRequest request) throws BaseException{
//        /// validate
//        if(Objects.isNull(request.getProdId()) || request.getProdId().isEmpty()) throw ProductException.findDetailFailRequestDetailNull();
//        if(Objects.isNull(request.getMateId())|| request.getMateId().isEmpty()) throw MaterialException.addInfoFailRequestMateNull();
//        if(Objects.isNull(request.getMateCount())) throw MaterialException.addInfoFailRequestMateNull();
//        if(Objects.isNull(request.getMateUnit())|| request.getMateUnit().isEmpty()) throw MaterialException.addInfoFailRequestMateNull();
//        /// verify
//        Optional<ProductDetail> productDetail = productDetailService.findProductDetailById(request.getProdId());
//        if(productDetail.isEmpty()) throw ProductException.findProductDetailFailDetailNull();
//        for (IngredientRequest ingRequest : request.getIngredients()) {
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
        Optional<BaseProduct> product =  baseProductService.findBaseById(baseId);
        if(product.isEmpty()) throw ProductException.findProductFail();
        List<Product> products = productService.findProductByBaseId(baseId);
        List<ForProductOnlyResponse> pdList = productMapper.toListProductOnlyResponse(products);
        MessageResponse res = new MessageResponse();
        res.setMessage("find ListProducts By Base ID");
        res.setRes(pdList);
        return res;
    }





    public MessageResponse findListProductInfo() {
        /// verify
        List<Product> product =  productService.findListProduct();
        List<ForProductInfoResponse> pdList = productMapper.toListForProductInfoResponse(product);
        MessageResponse res = new MessageResponse();
        res.setMessage("find ListProductsInfo");
        res.setRes(pdList);
        return res;
    }
    //////////////////////////////////////////////////////////////// find Product And List **

    public MessageResponse findProductAndListOptionById(String prodId) throws BaseException {
        /// validate
        Optional<Product> p =  productService.findProductById(prodId);
        if(p.isEmpty()) throw ProductException.findProductFail();
        ForProdAndListAddOnResponse prodRes = productMapper.toForProdAndListAddOnResponse(p.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("find Product And ListAddon By Product ID");
        res.setRes(prodRes);
        return res;
    }

    public MessageResponse findProductAndListIngredientById(String prodId) throws BaseException {
        /// validate
        Optional<Product> p =  productService.findProductById(prodId);
        if(p.isEmpty()) throw ProductException.findProductFail();
        ForProdAndListIngResponse prodRes = productMapper.toFoProdAndListIngResponse(p.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("find Product And ListIngredient By Product ID");
        res.setRes(prodRes);
        return res;
    }

    public MessageResponse findProductAndListCategoryById(String prodId) throws BaseException {
        /// validate
        Optional<Product> p =  productService.findProductById(prodId);
        if(p.isEmpty()) throw ProductException.findProductFail();
        ForProdAndListCategoryResponse prodRes = productMapper.toForProdAndListCategoryResponse(p.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("find Product And ListCategory By Product ID");
        res.setRes(prodRes);
        return res;
    }

    public MessageResponse findProductById(String prodId) throws BaseException {
        /// validate
        Optional<Product> p =  productService.findProductById(prodId);
        if(p.isEmpty()) throw ProductException.findProductFail();
        ForProductOnlyResponse prodRes = productMapper.toProductOnlyResponse(p.get());
        MessageResponse res = new MessageResponse();
        res.setMessage("find Product By ID");
        res.setRes(prodRes);
        return res;
    }
    ////////////////////////////////////////////////////////////////

    public MessageResponse deleteProduct(String prodId) throws BaseException {
        Boolean product =  productService.deleteProduct(prodId);
        MessageResponse res = new MessageResponse();
        res.setMessage("delete product complete");
        res.setRes(product);
        return res;
    }


}
