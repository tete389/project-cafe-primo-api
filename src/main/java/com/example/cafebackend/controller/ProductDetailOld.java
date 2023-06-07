//package com.example.cafebackend.controller;
//
//import com.example.cafebackend.exception.BaseException;
//import com.example.cafebackend.exception.CategoryException;
//import com.example.cafebackend.exception.FileException;
//import com.example.cafebackend.exception.ProductException;
//import com.example.cafebackend.mapper.CategoryMapper;
//import com.example.cafebackend.mapper.ProductMapper;
//import com.example.cafebackend.model.response.ForCategoryResponse;
//import com.example.cafebackend.model.response.Employee.TypeEmpResponse;
//import com.example.cafebackend.model.response.MessageResponse;
//import com.example.cafebackend.model.response.StartDataResponse;
//import com.example.cafebackend.service.*;
//import com.example.cafebackend.table.*;
//import com.example.cafebackend.util.FileUtil;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@AllArgsConstructor
//@Service
//public class ProductDetailOld {
//
//    private ProductDetailService productDetailService;
//
//    private ProductService productService;
//
//    private CategoryService categoryService;
//
//    private MaterialService materialService;
//
//    private TypeService typeService;
//
//    private AddOnService addOnService;
//
//    private IngredientService ingredientService;
//
//    private ProductMapper productMapper;
//
//    private CategoryMapper categoryMapper;
//
//    public static String uploadDirectory = System.getProperty("user.dir");
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse createProductDetail(String prodId, String prodForm, Double prodPrice) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodId) || prodId.isEmpty()) throw ProductException.createDetailFailProductRequestNull();
//        if(Objects.isNull(prodForm) || prodForm.isEmpty()) throw ProductException.createDetailFailRequestFormNull();
//        if(Objects.isNull(prodPrice)) throw ProductException.createDetailFailPriceRequestNull();
//        /// verify
//        Optional<Product> product = productService.findProductById(prodId);
//        if(product.isEmpty()) throw ProductException.createDetailFailProductNull();
//        Product prod = product.get();
//        String prodName = prod.getProdTitle() + prodForm;
//        ProductDetail productDetail = productDetailService.createProductDetail(prod, prodForm, prodName, prodPrice);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("add product detail success");
//        res.setRes(productDetail);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//    public MessageResponse saveDescriptionProductDetail(String prodDetailId, String description) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodDetailId) || prodDetailId.isEmpty()) throw ProductException.findDetailFailRequestDetailNull();
//        if(Objects.isNull(description) || description.isEmpty()) description = "NoData";
//        /// verify
//        Optional<ProductDetail> productDetail = productDetailService.findProductDetailById(prodDetailId);
//        if(productDetail.isEmpty()) throw ProductException.saveDescriptionFailProductDetailNull();
//        ProductDetail prodDetail = productDetailService.saveDescriptionProductDetail(productDetail.get(), description);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("save description product detail success");
//        res.setRes(prodDetail);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//    public MessageResponse setBonusPointProductDetail(String prodDetailId, Double point) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodDetailId) || prodDetailId.isEmpty()) throw ProductException.findDetailFailRequestDetailNull();
//        if(Objects.isNull(point)) point = 0.0;
//        /// verify
//        Optional<ProductDetail> productDetail = productDetailService.findProductDetailById(prodDetailId);
//        if(productDetail.isEmpty()) throw ProductException.saveBonusPointFailProductDetailNull();
//        ProductDetail prodDetail = productDetailService.setBonusPointProductDetail(productDetail.get(), point);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set bonus point success");
//        res.setRes(prodDetail);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//    public MessageResponse setForSaleProductDetail(String prodDetailId, Boolean forSale) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodDetailId) || prodDetailId.isEmpty()) throw ProductException.findDetailFailRequestDetailNull();
//        if(Objects.isNull(forSale)) throw ProductException.setForSaleFailRequestNull();
//        /// verify
//        Optional<ProductDetail> productDetail = productDetailService.findProductDetailById(prodDetailId);
//        if(productDetail.isEmpty()) throw ProductException.setForSaleFailProductDetailNull();
//        ProductDetail prodDetail = productDetailService.setForSaleProductDetail(productDetail.get(), forSale);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set isForSale success");
//        res.setRes(prodDetail);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//    public MessageResponse setEnableProductDetail(String prodDetailId, Boolean enable) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodDetailId) || prodDetailId.isEmpty()) throw ProductException.findDetailFailRequestDetailNull();
//        if(Objects.isNull(enable)) throw ProductException.setEnableFailRequestNull();
//        /// verify
//        Optional<ProductDetail> productDetail = productDetailService.findProductDetailById(prodDetailId);
//        if(productDetail.isEmpty()) throw ProductException.setEnableFailProductDetailNull();
//        ProductDetail prodDetail = productDetailService.setEnableProductDetail(productDetail.get(), enable);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set isEnable success");
//        res.setRes(prodDetail);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//    public MessageResponse setPriceProductDetail(String prodDetailId, Double newPrice) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodDetailId) || prodDetailId.isEmpty()) throw ProductException.findDetailFailRequestDetailNull();
//        if(Objects.isNull(newPrice)) throw ProductException.setNewPriceFailRequestPriceNull();
//        /// verify
//        Optional<ProductDetail> productDetail = productDetailService.findProductDetailById(prodDetailId);
//        if(productDetail.isEmpty()) throw ProductException.setNewPriceProductDetailNull();
//        ProductDetail prodDetail = productDetailService.setNewPriceProductDetail(productDetail.get(), newPrice);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set price success");
//        res.setRes(prodDetail);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//    public MessageResponse uploadImageProductDetail(String prodDetailId, MultipartFile image) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodDetailId) || prodDetailId.isEmpty()) throw ProductException.findDetailFailRequestDetailNull();
//        if(image == null) throw FileException.fileNull();
//        if(image.getSize() > 1048576 * 5 ) throw FileException.fileMaxSize();
//        String contentType = image.getContentType();
//        if (contentType == null) throw FileException.createFail();
//        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
//        if(!supportTypes.contains(contentType)) throw FileException.updateFailTypes();
//
//        // TODO * upload to sever
//
//        /// verify
//        Optional<ProductDetail> productDetail = productDetailService.findProductDetailById(prodDetailId);
//        if(productDetail.isEmpty()) throw ProductException.setNewPriceProductDetailNull();
//        //ProductDetail prodDetail = productDetailService.setNewPriceProductDetail(productDetail.get(), newPrice);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set price success");
//        //res.setRes(prodDetail);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//
//    public MessageResponse addInfoCategory(String prodDetailId, String cateId) throws BaseException{
//        /// validate
//        if(Objects.isNull(prodDetailId) || prodDetailId.isEmpty()) throw ProductException.findDetailFailRequestDetailNull();
//        if(Objects.isNull(cateId)|| cateId.isEmpty()) throw CategoryException.addInfoFailRequestCateNull();
//        /// verify
//        Optional<ProductDetail> productDetail = productDetailService.findProductDetailById(prodDetailId);
//        if(productDetail.isEmpty()) throw ProductException.addInfoFailDetailNull();
//        Optional<Category> category = categoryService.findById(cateId);
//        if(category.isEmpty()) throw CategoryException.addInfoFailCategoryNull();
//
//        ProductDetail prodDetail = productDetailService.setNewPriceProductDetail(productDetail.get(), newPrice);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("set price success");
//        res.setRes(prodDetail);
//        return res;
//    }
//    ////////////////////////////////////////////////
//
//
//    public MessageResponse getAllProd(){
//        List<ProductDetail> prodList = productDetailService.findAllProd();
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get All Products");
//        res.setRes(prodList);
//        return res;
//    }
//
//
//    ////////////////////////////////////////////////
//
//
//    public MessageResponse getProdDetailById(ProductDetail request) throws BaseException {
//        Optional<ProductDetail> pd =  productDetailService.findById(request.getProdDetailId());
//        /// validate
//        if(Objects.isNull(pd) || pd.isEmpty()){
//            throw ProductException.findFail();
//        }
//        ///
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get Products detail By ID");
//        res.setRes(pd.get());
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//
//    public MessageResponse getStartData()  {
//        List<Category> cateList = categoryService.findAllCate();
//        List<ForCategoryResponse> cateResList = new ArrayList<>();
//        for (Category cate : cateList){
//            Integer prodCount = productDetailService.findCountProdOfCategory(cate.getCateId());
//            ForCategoryResponse cateRes = categoryMapper.toCategoryEmpResponse(cate, prodCount);
//            cateResList.add(cateRes);
//        }
//        StartDataResponse typeResponse = new StartDataResponse();
//        typeResponse.setCategory(cateResList);
//
//
//        List<Type> typeList = typeService.findAllType();
//        List<TypeEmpResponse> typeResList = new ArrayList<>();
//        for (Type type : typeList){
//            //Integer prodCount = type.getProduct().size();
//            Integer prodCount = productDetailService.findCountProdOfType(type.getTypeId());
//            TypeEmpResponse typeRes = typeMapper.toTypeEmpResponse(type, prodCount);
//            typeResList.add(typeRes);
//        }
//
//        typeResponse.setType(typeResList);
//
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get ProdCount Start data");
//        //res.setRes(cateResponse);
//        res.setRes(typeResponse);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//
//    public MessageResponse getProdToSearch()  {
//        List<ProductDetail> prodList = productDetailService.findAllProdDetail();
//        List<ProductDetailResponse> prodResList = new ArrayList<>();
//        for (ProductDetail prod : prodList){
//            ProductDetailResponse prodRes = productMapper.toProductResponse(prod);
//            prodResList.add(prodRes);
//        }
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get ProdCount To Search");
//        res.setRes(prodResList);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//
//    public MessageResponse getProdByIdToSell(ProductDetail request) throws BaseException {
//        Optional<ProductDetail> prod = productDetailService.findById(request.getProdId());
//        if(prod.isEmpty()){
//            throw ProductException.findFail();
//        }
//        ProductCustomerResponse prodRes = productMapper.toProductCustomerResponse(prod.get());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get ProdCount ById To sell");
//        res.setRes(prodRes);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse getVerifyNameProd(ProductDetail request)  {
//        List<ProductDetail> prodList = productDetailService.findByProdNameVerify(request.getProdName());
//        MessageResponse res = new MessageResponse();
//        if(prodList.isEmpty()){
//            res.setMessage("Product Name unExists");
//            res.setRes(prodList);
//            return res;
//        }
//
//        List<ProductEmployeeResponse> resCus = new ArrayList<>();
//        for (ProductDetail prod : prodList) {
//            ProductEmployeeResponse prodRes = productMapper.toProductEmployeeResponse(prod);
//            resCus.add(prodRes);
//        }
//        res.setMessage("Product Name Exists");
//        res.setRes(resCus);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse createProdHaveImage(MultipartFile image, String prodName, String prodPrice , String prodType, String prodSweet, List<String> prodCate) throws BaseException {
//
//        /// validate
//        if(image == null){
//            throw FileException.fileNull();
//        }
//        //System.out.println(image);
//        if(image.getSize() > 1048576 * 5 ){
//            throw FileException.fileMaxSize();
//        }
//        String contentType = image.getContentType();
//        if (contentType == null){
//            throw FileException.createFail();
//        }
//        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
//        if(!supportTypes.contains(contentType)){
//            throw FileException.updateFailTypes();
//        }
//
//        if(Objects.isNull(prodName) ||  prodName.isEmpty()){
//            throw ProductException.createFail();
//        }
//        if(Objects.isNull(prodPrice)){
//            throw ProductException.createFail();
//        }
//        if(Objects.isNull(prodSweet) ||  prodSweet.isEmpty()){
//            throw ProductException.createFail();
//        }
//
//        double productPrice = Double.parseDouble(prodPrice);
//        String imgPath = manageImage(image);
//        ProductDetail productDetail;
//
//        /// check type
//        if(Objects.isNull(prodType)){
//            List<Type> typeAll =  typeService.findAllType();
//            Type typeOther = typeAll.get((typeAll.size()-1));
//            if(Objects.isNull(typeOther)){
//                throw ProductException.findFail();
//            }
//
//            productDetail = productDetailService.createProduct(prodName, imgPath, productPrice, typeOther);
//        }else {
//            Optional<Type> type =  typeService.findById(prodType);
//            if(Objects.isNull(type) || type.isEmpty()){
//                throw ProductException.createFail();
//            }
//            productDetail = productDetailService.createProduct(prodName, imgPath, productPrice, type.get());
//        }
//
//        /// check category
//        if(!(Objects.isNull(prodCate) ||  prodCate.isEmpty())){
//
//            for (String cate : prodCate) {
//                Optional<Category> category =  categoryService.findByName(cate);
//                if(Objects.isNull(category) ||  category.isEmpty()){
//                    throw ProductException.findFail();
//                }
//                productDetail = productDetailService.updateProductAddCate(productDetail, category.get());
//            }
//        }
//        if(prodSweet.equals("have")){
//            List<Sweetness> sweet = sweetnessService.findAllSweetness();
//            productDetail = productDetailService.updateProductAddSweet(productDetail, sweet);
//        }
//
//        MessageResponse res = new MessageResponse();
//        res.setMessage("create product complete");
//        res.setRes(productDetail);
//        return res;
//    }
//
//
//
//
//    public MessageResponse createProdImageLess(ProdRequest request) throws BaseException {
//        String requestProdName = request.getProdName();
//        String requestProdPrice = String.valueOf(request.getProdPrice());
//        String requestProdSweet = request.getProdSweet();
//        String requestProdType = request.getProdType();
//        List<String> requestProdCate = request.getProdCate();
//
//        /// validate
//        if(Objects.isNull(requestProdName) ||  requestProdName.isEmpty()){
//            throw ProductException.createFail();
//        }
//        if(Objects.isNull(requestProdPrice) || requestProdPrice.isEmpty()){
//            throw ProductException.createFail();
//        }
//        double productPrice = Double.parseDouble(requestProdPrice);
//
//        if(Objects.isNull(requestProdSweet) ||  requestProdSweet.isEmpty()){
//            throw ProductException.createFail();
//        }
//
//        String imgPath = "NoData";
//        ProductDetail productDetail;
//        /// check type
//        if(Objects.isNull(requestProdType) || requestProdType.isEmpty()){
//            List<Type> typeAll =  typeService.findAllType();
//            Type typeOther = typeAll.get((typeAll.size()-1));
//            if(Objects.isNull(typeOther)){
//                throw ProductException.findFail();
//            }
//            productDetail = productDetailService.createProduct(requestProdName, imgPath, productPrice, typeOther);
//        }else {
//            Optional<Type> type =  typeService.findById(requestProdType);
//            if(Objects.isNull(type) || type.isEmpty()){
//                throw ProductException.createFail();
//            }
//            productDetail = productDetailService.createProduct(requestProdName, imgPath, productPrice, type.get());
//        }
//
//        /// check category
//        if(!(Objects.isNull(requestProdCate) ||  requestProdCate.isEmpty())){
//            for (String cate : requestProdCate) {
//                Optional<Category> category =  categoryService.findByName(cate);
//                if(Objects.isNull(category) ||  category.isEmpty()){
//                    throw ProductException.findFail();
//                }
//                productDetail = productDetailService.updateProductAddCate(productDetail, category.get());
//            }
//        }
//        if(requestProdSweet.equals("have")){
//            List<Sweetness> sweet = sweetnessService.findAllSweetness();
//            productDetail = productDetailService.updateProductAddSweet(productDetail, sweet);
//        }
//
//        MessageResponse res = new MessageResponse();
//        res.setMessage("create product complete");
//        res.setRes(productDetail);
//        return res;
//    }
//
//    ////////////////////////////////////////////////
//
//
//    public MessageResponse upLoadImageProd(MultipartFile image, String prodId) throws BaseException {
//        /// validate
//        if(Objects.isNull(image)){
//            throw FileException.fileNull();
//        }
//        if(image.getSize() > 1048576 * 5 ){
//            throw FileException.fileMaxSize();
//        }
//        String contentType = image.getContentType();
//        if (contentType == null){
//            throw FileException.updateFail();
//        }
//        if(Objects.isNull(prodId) ||  prodId.isEmpty()){
//            throw ProductException.updateFailDataNull();
//        }
//        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
//        if(!supportTypes.contains(contentType)){
//            throw FileException.updateFailTypes();
//        }
//
//        String imgPath = manageImage(image);
//        /// check product id
//        Optional<ProductDetail> prod = productDetailService.findById(prodId);
//        if(prod.isEmpty()){
//            throw ProductException.updateFailNotFound();
//        }
//        /// update product  with not type
//        ProductDetail productDetail = productDetailService.uploadImageProduct(prod.get(), imgPath);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("updLoad Image product complete");
//        res.setRes(productDetail);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//
//    public MessageResponse updatePriceProd(ProdRequest request) throws BaseException {
//        String requestProdId = String.valueOf(request.getProdId());
//        String requestProdPrice = String.valueOf(request.getProdPrice());
//
//        /// validate
//
//        if(Objects.isNull(requestProdId) ||  requestProdId.isEmpty()){
//            throw ProductException.updateFailDataNull();
//        }
//        if(Objects.isNull(requestProdPrice) ||  requestProdPrice.isEmpty()){
//            throw ProductException.updateFailDataNull();
//        }
//        /// check product id
//        Optional<ProductDetail> prod = productDetailService.findById(request.getProdId());
//        if(prod.isEmpty()){
//            throw ProductException.updateFailNotFound();
//        }
//        /// update product  with not type
//        ProductDetail productDetail = productDetailService.updatePriceProduct(prod.get(), request.getProdPrice());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("update price product complete");
//        res.setRes(productDetail);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse updateStatusProd(ProdRequest request) throws BaseException {
//        String requestProdId = String.valueOf(request.getProdId());
//        String requestProdName = request.getProdName();
//        String requestProdStatus = request.getProdStatus();
//
//        /// validate
//        if(Objects.isNull(requestProdStatus) ||  requestProdStatus.isEmpty()){
//            throw ProductException.createFail();
//        }
//
//        if(Objects.isNull(requestProdName) ||  requestProdName.isEmpty()){
//            if(Objects.isNull(requestProdId) ||  requestProdId.isEmpty()){
//                throw ProductException.updateFailNotFound();
//            }
//            Optional<ProductDetail> prod = productDetailService.findById(request.getProdId());
//            if(prod.isEmpty()){
//                throw ProductException.updateFailNotFound();
//            }
//            ProductDetail productDetail = productDetailService.updateDataStatusProduct(prod.get(), requestProdStatus);
//            MessageResponse res = new MessageResponse();
//            res.setMessage("update Status product complete");
//            res.setRes(productDetail);
//            return res;
//        }
//        List<ProductDetail> prodList = productDetailService.findProdByName(requestProdName);
//        if(prodList.isEmpty()){
//            throw ProductException.updateFailNotFound();
//        }
//        /// check
//        List<ProductDetail> List = new ArrayList<>();
//        for (ProductDetail prod : prodList){
//            ProductDetail productDetail = productDetailService.updateDataStatusProduct(prod, requestProdStatus);
//            List.add(productDetail);
//        }
//        MessageResponse res = new MessageResponse();
//        res.setMessage("update Status products all complete");
//        res.setRes(List);
//        return res;
//
//    }
//
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse updateAddInProd(ProdRequest request) throws BaseException {
//        String requestProdId = String.valueOf(request.getProdId());
//        String requestProdName = request.getProdName();
//        List<Option> requestAdd = request.getAddOn();
//
//        /// validate
//        if(Objects.isNull(requestAdd) ||  requestAdd.isEmpty()){
//            throw ProductException.updateFailDataNull();
//        }
//        if(Objects.isNull(requestProdName) ||  requestProdName.isEmpty()){
//            if(Objects.isNull(requestProdId) ||  requestProdId.isEmpty()){
//                throw ProductException.updateFailNotFound();
//            }
//            Optional<ProductDetail> prod = productDetailService.findById(request.getProdId());
//            if(prod.isEmpty()){
//                throw ProductException.updateFailNotFound();
//            }
//            List<Option> addList = new ArrayList<>();
//            for (Option option : requestAdd) {
//                Optional<Option> add = addOnService.findByName(option.getAddName());
//                if(add.isEmpty()){
//                    throw ProductException.updateFailNotFound();
//                }
//                addList.add(add.get());
//            }
//            ProductDetail productDetail = productDetailService.updateAddProduct(prod.get(), addList);
//            MessageResponse res = new MessageResponse();
//            res.setMessage("update Additional product complete");
//            res.setRes(productDetail);
//            return res;
//        }
//
//        List<ProductDetail> prodList = productDetailService.findProdByName(requestProdName);
//        if(prodList.isEmpty()){
//            throw ProductException.updateFailNotFound();
//        }
//        List<Option> addList = new ArrayList<>();
//        /// check
//        List<ProductDetail> List = new ArrayList<>();
//        for (ProductDetail prod : prodList){
//            for (Option add : requestAdd) {
//                Optional<Option> additional = addOnService.findByName(add.getAddName());
//                if(additional.isEmpty()){
//                    throw ProductException.updateFailNotFound();
//                }
//                addList.add(additional.get());
//            }
//            ProductDetail productDetail = productDetailService.updateAddProduct(prod, addList);
//            List.add(productDetail);
//        }
//        MessageResponse res = new MessageResponse();
//        res.setMessage("update Additional products all complete");
//        res.setRes(List);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse updateCateInProd(ProdRequest request) throws BaseException {
//        String requestProdId = String.valueOf(request.getProdId());
//        String requestProdName = request.getProdName();
//        List<Category> requestCategory = request.getCategory();
//
//        /// validate
//        if(Objects.isNull(requestCategory) ||  requestCategory.isEmpty()) throw ProductException.updateFailDataNull();
//        if(Objects.isNull(requestProdName) ||  requestProdName.isEmpty()){
//            if(Objects.isNull(requestProdId) ||  requestProdId.isEmpty()) throw ProductException.updateFailNotFound();
//            Optional<ProductDetail> prod = productDetailService.findById(request.getProdId());
//            if(prod.isEmpty()) throw ProductException.updateFailNotFound();
//            List<Category> cateList = new ArrayList<>();
//            for (Category category : requestCategory) {
//                Optional<Category> cate = categoryService.findByName(category.getCateName());
//                if(cate.isEmpty()) throw ProductException.updateFailNotFound();
//                cateList.add(cate.get());
//            }
//            ProductDetail productDetail = productDetailService.updateDataCategoryProduct(prod.get(), cateList);
//            MessageResponse res = new MessageResponse();
//            res.setMessage("update category product complete");
//            res.setRes(productDetail);
//            return res;
//        }
//
//        List<ProductDetail> prodList = productDetailService.findProdByName(requestProdName);
//        if(prodList.isEmpty()) throw ProductException.updateFailNotFound();
//        /// check
//        List<ProductDetail> List = new ArrayList<>();
//        for (ProductDetail prod : prodList){
//            List<Category> cateList = new ArrayList<>();
//            for (Category category : requestCategory) {
//                Optional<Category> cate = categoryService.findByName(category.getCateName());
//                if(cate.isEmpty()) throw ProductException.updateFailNotFound();
//                cateList.add(cate.get());
//            }
//            ProductDetail productDetail = productDetailService.updateDataCategoryProduct(prod, cateList);
//            List.add(productDetail);
//        }
//        MessageResponse res = new MessageResponse();
//        res.setMessage("update category products complete");
//        res.setRes(List);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse updateMateInProd(ProdRequest request) throws BaseException {
//        String requestProdId = String.valueOf(request.getProdId());
//        String requestProdName = request.getProdName();
//        List<Ingredient> requestIngredient = request.getIngredient();
//
//        /// validate
//        if(Objects.isNull(requestIngredient) ||  requestIngredient.isEmpty()){
//            throw ProductException.updateFailDataNull();
//        }
//
//        if(Objects.isNull(requestProdName) ||  requestProdName.isEmpty()){
//            if(Objects.isNull(requestProdId) ||  requestProdId.isEmpty()){
//                throw ProductException.updateFailNotFound();
//            }
//            Optional<ProductDetail> prod = productDetailService.findById(request.getProdId());
//            if(prod.isEmpty()){
//                throw ProductException.updateFailNotFound();
//            }
//            List<Ingredient> ingredientList = new ArrayList<>();
//            for (Ingredient ingredient : requestIngredient) {
//                Optional<Material> mate = materialService.findById(ingredient.getMaterial().getMateId());
//                if(mate.isEmpty()){
//                    throw ProductException.updateFailNotFound();
//                }
//                Ingredient ingProd = ingredientService.createIngredient(prod.get(), mate.get(), ingredient.getUseAmount());
//                ingredientList.add(ingProd);
//            }
//            ProductDetail productDetail = productDetailService.updateDataIngredientProduct(prod.get(), ingredientList);
//            MessageResponse res = new MessageResponse();
//            res.setMessage("update ingredient product complete");
//            res.setRes(productDetail);
//            return res;
//        }
//
//        List<ProductDetail> prodList = productDetailService.findProdByName(requestProdName);
//        if(prodList.isEmpty()){
//            throw ProductException.updateFailNotFound();
//        }
//        /// check
//        List<ProductDetail> List = new ArrayList<>();
//        for (ProductDetail prod : prodList){
//            List<Ingredient> ingredientList = new ArrayList<>();
//            for (Ingredient ingredient : requestIngredient) {
//                Optional<Material> mate = materialService.findById(ingredient.getMaterial().getMateId());
//                if(mate.isEmpty()){
//                    throw ProductException.updateFailNotFound();
//                }
//                Ingredient ingProd = ingredientService.createIngredient(prod, mate.get(), ingredient.getUseAmount());
//                ingredientList.add(ingProd);
//            }
//            ProductDetail productDetail = productDetailService.updateDataIngredientProduct(prod, ingredientList);
//            List.add(productDetail);
//        }
//        MessageResponse res = new MessageResponse();
//        res.setMessage("update ingredient products all complete");
//        res.setRes(List);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse deleteProd(ProductDetail request) throws BaseException {
//        /// check product id
//        Optional<ProductDetail> prod = productDetailService.findById(request.getProdId());
//        if(prod.isEmpty()){
//            throw ProductException.deleteFail();
//        }
//        productDetailService.deleteProductById(request.getProdId());
//        MessageResponse res = new MessageResponse();
//        res.setMessage("delete product complete");
//        res.setRes("delete");
//        return res;
//    }
//
//    ////////////////////////////////////////////////
//
////    public MessageResponse addTypeInToProd(ProdRequest request) throws BaseException {
////        if(request.getProdType().isEmpty()){
////            throw ProductException.updateFailDataNull();
////        }
////        String requestProdName = request.getProdName();
////        String requestProdStatus = request.getProdStatus();
////        /// validate
////        if(Objects.isNull(requestProdName) ||  requestProdName.isEmpty()){
////            throw ProductException.addTypeFail();
////        }
////        if(Objects.isNull(requestProdStatus) ||  requestProdStatus.isEmpty()){
////            throw ProductException.addTypeFail();
////        }
////        ///
////        for (TypeRequest type : request.getType()){
////            /// validate
////            Optional<Type> typeOptional = typeService.findByName(type.getTypeName());
////            if(Objects.isNull(typeOptional) ||  typeOptional.isEmpty()){
////                throw ProductException.addTypeFail();
////            }
////            if(String.valueOf(type.getTypePrice()).isEmpty() || Double.isNaN(type.getTypePrice())){
////                throw ProductException.addTypeFail();
////            }
////            if(String.valueOf(type.getTypeTimeProcess()).isEmpty() || Double.isNaN(type.getTypeTimeProcess())){
////                throw ProductException.addTypeFail();
////            }
////            if(Objects.isNull(type.getTypeImg()) ||  type.getTypeImg().isEmpty()){
////                throw ProductException.addTypeFail();
////            }
////            Product product = productService.createProductWithType(requestProdName, type.getTypeImg(), type.getTypePrice(), typeOptional.get());
////        }
////        List<Product> prodList = productService.findProdByName(requestProdName);
////        if(prodList.isEmpty()){
////            throw ProductException.addTypeFail();
////        }
////        MessageResponse res = new MessageResponse();
////        res.setMessage("add type product complete");
////        res.setRes(prodList);
////        return res;
////
////    }
////
////    ////////////////////////////////////////
////
////    public MessageResponse addCateInToProd(ProdCateRequest request) throws BaseException {
////
////        Optional<Product> prod = productService.findById(request.getProdId());
////        if(prod.isEmpty()){
////            throw ProductException.addCateFail();
////        }
////        Optional<Category> cate =  categoryService.findById(request.getCateId());
////        if(cate.isEmpty()){
////            throw ProductException.addCateFail();
////        }
////        Product product = productService.updateProductAddCate(prod.get(), cate.get());
////
////        MessageResponse res = new MessageResponse();
////        res.setMessage("add Category product complete");
////        res.setRes(product);
////        return res;
////    }
////
////    ////////////////////////////////////////////////
////
////    public MessageResponse addMateInToProd(ProdMateRequest request) throws BaseException {
////
////        List<Product> prodRes = new ArrayList<>();
////        List<Product> prodList = productService.findProdByName(request.getProdName());
////        if(prodList.isEmpty()){
////            throw ProductException.addMateFail();
////        }
////        for (Product prod : prodList ){
////            for (Material mateRequest : request.getMate()){
////                if(Objects.isNull(String.valueOf(mateRequest.getMateStock()))){
////                    throw ProductException.addMateFail();
////                }
////                Optional<Material> mate =  materialService.findById(mateRequest.getMateId());
////                /// validate
////                if(mate.isEmpty()){
////                    throw ProductException.addMateFail();
////                }
////
////                Ingredient ingProd = ingredientService.createIngredient(prod, mate.get(), mateRequest.getMateStock());
////                Product product = productService.updateProductAddMate(prod, ingProd);
////                prodRes.add(product);
////            }
////        }
////        MessageResponse res = new MessageResponse();
////        res.setMessage("add Material product complete");
////        res.setRes(prodRes);
////        return res;
////    }
////
////    ////////////////////////////////////////////////
////
//
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse getProdForCustomer(){
//        List<ProductDetail> prodCus = productDetailService.findAllProd();
//        List<ProductCustomerResponse> prodRes = productMapper.toListProductCustomerResponse(prodCus);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get All product to customer");
//        res.setRes(prodRes);
//        return res;
//    }
//
//    ////////////////////////////////////////////////
//
//
//    public MessageResponse getProdByCateId(Category request){
//        List<ProductDetail> prod =  productDetailService.findProdByCateId(request.getCateId());
//        List<ProductDetailResponse> prodRes =  productMapper.toListProductResponse(prod);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get All product to customer");
//        res.setRes(prodRes);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse getProdByMateId(Material request){
//        List<ProductDetail> prod =  productDetailService.findProdByMateId(request.getMateId());
//        List<ProductDetailResponse> prodRes =  productMapper.toListProductResponse(prod);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get All product to customer");
//        res.setRes(prodRes);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//    public MessageResponse getProdByTypeId(Type request){
//        List<ProductDetail> prod =  productDetailService.findProdByTypeId(request.getTypeId());
//        List<ProductDetailResponse> prodRes =  productMapper.toListProductResponse(prod);
//        MessageResponse res = new MessageResponse();
//        res.setMessage("get All product to customer");
//        res.setRes(prodRes);
//        return res;
//
//    }
//
//    ////////////////////////////////////////////////
//
//    ///
//    public String manageImage(MultipartFile image) {
//        String dir = FileUtil.folderPath;
//        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String tempName = UUID.randomUUID().toString().replaceAll("-", "");
//        String imgName = "/"+dir +"P"+tempName+"T"+timeStamp+".png";
//        //Path url  = Paths.get(dir);
//        //File fileToSave = new File(url + imgName);
//        System.out.println(Paths.get(uploadDirectory + imgName));
//        try {
//            byte[] bytes = image.getBytes();
//
//            Files.write(Paths.get(uploadDirectory + imgName), bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return imgName;
//    }
//
//}
