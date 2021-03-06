package com.ddbs.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "system-params")
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
