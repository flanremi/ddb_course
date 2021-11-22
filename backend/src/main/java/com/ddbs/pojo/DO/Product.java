package com.ddbs.pojo.DO;

import lombok.Data;

@Data
public class Product {
    private Integer pid;// 自增pk
    private String name;
    private Integer warehouse; //仓库id
    private Double price;
    private Integer num; //库存
    private Integer code; //条码
    private String img;//展示图url
}


