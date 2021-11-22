package com.ddbs.mapper;

import com.ddbs.pojo.DO.Warehouse;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
public class WarehouseMapperTest {

    @Autowired
    WarehouseMapper warehouseMapper;

    @Test
    public void testMapper() throws Exception{
        List<Warehouse> lst = warehouseMapper.getAllWarehouses();
        lst.forEach(e-> System.out.println(e.getAddress()));
    }

}