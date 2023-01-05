package com.example.cafebackend.api;

import com.example.cafebackend.controller.TypeController;
import com.example.cafebackend.model.request.ProdAddRequest;
import com.example.cafebackend.model.request.ProdTypeRequest;
import com.example.cafebackend.table.Additional;
import com.example.cafebackend.table.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/type")
public class TypeApi {

    private final TypeController typeController;

    public TypeApi(TypeController typeController) {
        this.typeController = typeController;
    }


    //////////////////////////////////////////////////////////

//    @PostMapping("/createType")
//    public ResponseEntity<Type> createCate(@RequestBody Type request) {
//        Type res = typeController.createType(request);
//        return ResponseEntity.ok(res);
//    }

    @PostMapping("/getAllType")
    public ResponseEntity<List<Type>> getAllType() {
        List<Type> res = typeController.getAllType();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getTypeById")
    public ResponseEntity<Type> getTypeById(@RequestBody Type request) {
        Type res = typeController.getTypeById(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateType")
    public ResponseEntity<Type> updateType(@RequestBody Type request) {
        Type res = typeController.updateType(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteType")
    public ResponseEntity<String> deleteType(@RequestBody Type request) {
        String res = typeController.deleteType(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/addProdInType")
    public ResponseEntity<String> addProdInType(@RequestBody ProdTypeRequest request) {
        String res = typeController.addProductInType(request);
        return ResponseEntity.ok(res);
    }
}
