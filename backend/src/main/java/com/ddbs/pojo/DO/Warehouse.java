package com.ddbs.pojo.DO;

import lombok.Data;

@Data
public class Warehouse {
    private Integer id;// 自增pk
    private String name;
    private String address; //地址，描述性字段，随意
    private Integer manager; //管理者uid
}

