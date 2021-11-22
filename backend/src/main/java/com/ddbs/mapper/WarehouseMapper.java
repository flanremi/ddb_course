package com.ddbs.mapper;

import com.ddbs.pojo.DO.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseMapper {
    List<Warehouse> getAllWarehouses();
}
