package com.ddbs.service.interfaces;

import com.ddbs.pojo.DO.Record;

import java.util.List;

public interface RecordService {
    List<Record> getRecordListByIdAndDesc(Integer uid, String desc);

    Boolean saveOneRecord(Integer uid, String desc, Integer type);


}
