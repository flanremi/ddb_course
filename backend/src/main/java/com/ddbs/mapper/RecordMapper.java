package com.ddbs.mapper;

import com.ddbs.pojo.DO.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RecordMapper {
    List<Record> getRecordListByUidAndDesc(@Param("uid") Integer uid,
                                          @Param("desc") String desc);

    Boolean insertOneRecord(Map<String, Object> map);
}
