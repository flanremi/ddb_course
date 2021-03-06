package com.ddbs.mapper;

import com.ddbs.pojo.DO.Staff;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
public class UserMapperTest {
    @Autowired
    private UserMapper usrMapper;

    @Test
    public void testMapper() throws Exception {

        Staff staff0 = usrMapper.getStaffById(1);
        System.out.println(staff0.getName());

        Staff staff = usrMapper.getStaffByToken("FAA", "F123");
        System.out.println(staff.getName());

        List<Staff> lst = usrMapper.getStaffsBelowLevel(5);
        lst.forEach(e-> System.out.println(e.getName()));

    }
}