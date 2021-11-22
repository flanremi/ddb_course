package com.ddbs.controller;

import com.ddbs.mapper.ProductMapper;
import com.ddbs.pojo.DO.Product;
import com.ddbs.util.CompanyStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductMapper productMapper;

    @GetMapping("/get_warehouse")
    public ResponseEntity<List<Product>> checkWarehouse(@RequestParam("uid") Integer warehouse){

        if(new CompanyStaff().testCompanyStaff(warehouse)){
            return new ResponseEntity<List<Product> >(productMapper.getAllProducts(), HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Product> >(productMapper.getProductByWarehouse(warehouse), HttpStatus.OK);
        }
    }
}
