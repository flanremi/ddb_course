package com.ddbs.pojo.DO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Staff {
    private Integer uid; // 自增pk
    private String name;
    private Integer warehouse;  //仓库id
    private Integer wage;
    private String password;
    private String username;  //做下hash
    private Integer level;  //员工等级
}

