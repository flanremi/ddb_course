package com.ddbs.util;

import java.util.ArrayList;
import java.util.List;


public class CompanyStaff {

    List<Integer> ids;
    public CompanyStaff(){
        ids = new ArrayList<>();
        ids.add(10003);
    }

    public Boolean testCompanyStaff(Integer id){
        return this.ids.contains(id);
    }
}
