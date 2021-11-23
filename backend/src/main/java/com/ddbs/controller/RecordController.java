package com.ddbs.controller;

import com.ddbs.pojo.DO.Record;
import com.ddbs.service.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecordController {
    @Autowired
    RecordService recordService;

    @GetMapping("/get_record")
    public ResponseEntity<List<Record> > getRecord(@RequestParam("uid") Integer uid,
                                                   @RequestParam("desc") String desc) {
        return new ResponseEntity<>(recordService.getRecordListByIdAndDesc(uid, desc), HttpStatus.OK);
    }
}
