package com.ddbs.mapper;

import com.ddbs.pojo.DO.Record;
import com.ddbs.service.interfaces.RecordService;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
public class RecordMapperTest {
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    RecordService recordService;

    @Test
    public void testMapper() throws Exception {
//        recordService.saveOneRecord(2, "出架",2);
        recordService.saveOneRecord(1, "下架",1);

        List<Record> lst = recordMapper.getRecordListByUidAndDesc(1, "下架");
        lst.forEach(e-> Assert.assertEquals(Integer.valueOf(10001), Integer.valueOf(e.getWarehouse())));



    }

}