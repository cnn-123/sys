package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.entity.Users;
import com.example.sys.demo.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceImplTest {
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void queryUserNumberIsExist() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.eq("user_number","17851146034");
        Users users = usersMapper.selectOne(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void saveUser() {
    }

    @Test
    public void queryUserLogin() {
    }

    @Test
    public void queryUserPasswordIsTrue() {
    }

    @Test
    public void removePassword() {
    }
}
