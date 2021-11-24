package com.ddbs.pojo.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private Integer id;// 自增pk
    private Integer type; //记录类型 0出库，1入库，2人事变动
    private Integer uid; //记录发起人
    private Timestamp date;
    private String descp;  //记录描述，随意弄
    private Integer warehouse;
}
