package com.ddbs.mapper;

import com.ddbs.pojo.DO.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
//    Double getPriceById(Integer pid);
    List<Product> getProductByWarehouse(Integer warehouse);
    List<Product> getAllProducts();

}
