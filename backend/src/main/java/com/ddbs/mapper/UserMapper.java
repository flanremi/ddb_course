package com.ddbs.mapper;

import com.ddbs.pojo.DO.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    Staff getStaffById(Integer uid);
    Staff getStaffByToken(@Param("username") String username, @Param("password") String password);
    List<Staff> getStaffsBelowLevel(Integer level);
}
