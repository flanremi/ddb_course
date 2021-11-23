package com.ddbs.mapper;

import com.ddbs.pojo.DO.Product;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
public class ProductMapperTest {

    @Autowired
    private ProductMapper pdtMapper;

    @Test
    public void testMapper() throws Exception {
//        Assert.assertEquals(Double.valueOf(5.00), pdmMapper.getPriceById(1));
        List<Product> pdtLst = pdtMapper.getProductByWarehouse(10001);
        pdtLst.forEach((e) -> System.out.println(e.getName()));

        List<Product> pdtLst2 = pdtMapper.getAllProducts();
        pdtLst2.forEach((e) -> System.out.println(e.getName()));

        Product p = pdtMapper.getProductByWarehouseAndCode(10001,123456);
        Integer ori_num = p.getNum();

//        System.out.println(p.getPid());
        if(pdtMapper.popOutProduct(p.getPid(),8)){
            Integer cur_num = pdtMapper.getProductByWarehouseAndCode(10001,123456).getNum();

            Assert.assertEquals(Integer.valueOf(ori_num),Integer.valueOf(cur_num + 8));
        };
    }



}