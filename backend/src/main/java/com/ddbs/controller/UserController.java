package com.ddbs.controller;

import com.ddbs.mapper.UserMapper;
import com.ddbs.pojo.DO.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/login")
    public ResponseEntity<Staff> login(@RequestParam("username") String name,
                                       @RequestParam("password") String pwd){
        return new ResponseEntity<Staff>(userMapper.getStaffByToken(name, pwd), HttpStatus.OK);
    }

    @GetMapping("/staff_manager")
    public ResponseEntity<List<Integer>> manageStaff(@RequestParam("uid") Integer id){
        int level = userMapper.getStaffById(id).getLevel();
        return new ResponseEntity<>(userMapper.getUidsBelowLevel(level), HttpStatus.OK);
    }

}
