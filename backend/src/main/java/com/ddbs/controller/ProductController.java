package com.ddbs.controller;

import com.ddbs.mapper.ProductMapper;
import com.ddbs.mapper.UserMapper;
import com.ddbs.pojo.DO.Product;
import com.ddbs.pojo.DO.Staff;
import com.ddbs.service.interfaces.RecordService;
import com.ddbs.util.CompanyStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RecordService recordService;

    @GetMapping("/get_warehouse")
    public ResponseEntity<List<Product>> checkWarehouse(@RequestParam("uid") Integer uid){

        Staff stf = userMapper.getStaffById(uid);
        if(new CompanyStaff().testCompanyStaff(stf.getWarehouse())){
            return new ResponseEntity<List<Product> >(productMapper.getAllProducts(), HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Product> >(productMapper.getProductByWarehouse(stf.getWarehouse()), HttpStatus.OK);
        }
    }


    @GetMapping("/pop_product")
    public ResponseEntity<Boolean> popOutProduct(@RequestParam("uid") Integer uid,
                                                  @RequestParam("warehouse") Integer warehouse,
                                                  @RequestParam("code") Integer code,
                                                  @RequestParam("num") Integer num){

        if(userMapper.getStaffById(uid).getWarehouse().equals(warehouse)){
            // 在product表里找指定的warehouse和code对应的元组，
            // 把它的num属性减掉参数num个，如果不够减返回失败。
            Product p = productMapper.getProductByWarehouseAndCode(warehouse, code);
            if(p.getNum()-num<0){
                return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
            }else {
                // 出库
                productMapper.popOutProduct(p.getPid(), num);
                // 记录
                recordService.saveOneRecord(uid,"出库", 2);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
    }

}
