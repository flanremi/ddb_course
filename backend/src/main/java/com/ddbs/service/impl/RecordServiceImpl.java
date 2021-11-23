package com.ddbs.service.impl;

import com.ddbs.mapper.RecordMapper;
import com.ddbs.mapper.UserMapper;
import com.ddbs.pojo.DO.Record;
import com.ddbs.pojo.DO.Staff;
import com.ddbs.service.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public List<Record> getRecordListByIdAndDesc(Integer uid, String desc) {
        return recordMapper.getRecordListByUidAndDesc(uid, desc);
    }

    @Override
    public Boolean saveOneRecord(Integer uid, String desc, Integer type) {
        Staff stf = userMapper.getStaffById(uid);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("uid",uid);
        params.put("date",new Timestamp(new Date().getTime()));
        params.put("desc",desc);
        params.put("warehouse",stf.getWarehouse());

        System.out.println(params.get("date"));

        return recordMapper.insertOneRecord(params);
    }
}
