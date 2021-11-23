package com.ddbs.mapper;

import com.ddbs.pojo.DO.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
//    Double getPriceById(Integer pid);
    List<Product> getProductByWarehouse(Integer warehouse);
    List<Product> getAllProducts();
    Product getProductByWarehouseAndCode(@Param("warehouse") Integer warehouse,
                                         @Param("code") Integer code);
    Boolean popOutProduct(@Param("pid") Integer pid,
                          @Param("num") Integer num);

}
