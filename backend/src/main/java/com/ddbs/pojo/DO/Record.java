package com.ddbs.pojo.DO;

import lombok.Data;

@Data
public class Record {
    private String id;// 自增pk
    private String type; //记录类型 0出库，1入库，2人事变动
    private String uid; //记录发起人
    private String data;
    private String desc;  //记录描述，随意弄
}
