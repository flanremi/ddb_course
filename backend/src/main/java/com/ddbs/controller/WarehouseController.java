package com.ddbs.controller;

import com.ddbs.mapper.ProductMapper;
import com.ddbs.mapper.WarehouseMapper;
import com.ddbs.pojo.DO.Product;
import com.ddbs.pojo.DO.Staff;
import com.ddbs.pojo.DO.Warehouse;
import com.ddbs.util.CompanyStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WarehouseController {
    @Autowired
    WarehouseMapper warehouseMapper;

    @GetMapping("/get_all_warehouse")
    public ResponseEntity<List<Warehouse>> getALlWarehouses() {
        return new ResponseEntity<>(warehouseMapper.getAllWarehouses(), HttpStatus.OK);
    }
}
