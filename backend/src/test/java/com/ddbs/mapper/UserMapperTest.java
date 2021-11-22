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

        Staff staff = usrMapper.getStaffByToken("A", "A");
        System.out.println(staff.getName());

        List<Integer> lst = usrMapper.getUidsBelowLevel(5);
        lst.forEach(System.out::println);

    }
}