package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.entity.Users;
import com.example.sys.demo.mapper.UsersMapper;
import com.example.sys.demo.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-02-06
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public boolean queryUserNumberIsExist(String userNumber) {
            Map<String,Object> columnMap = new HashMap<>();
            columnMap.put("user_number",userNumber);
            List<Users> userNumberIsExist = usersMapper.selectByMap(columnMap);
            return userNumberIsExist.size() == 0 ? false : true;

//        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
//        queryWrapper.eq("user_number",userNumber);
//        Users userNumberIsExist = usersMapper.selectOne(queryWrapper);
//        System.out.println(userNumberIsExist);
//        return userNumberIsExist == null ? false : true;
    }

    @Override
    public int saveUser(Users users) {
        return usersMapper.insert(users);
    }

    @Override
    public Users queryUserLogin(String userNumber, String userPassword) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.eq("user_number",userNumber);
        queryWrapper.eq("user_password",userPassword);
        Users userResult = usersMapper.selectOne(queryWrapper);
        return userResult;
    }

    @Override
    public Users queryUserPasswordIsTrue(String userNumber, String userPassWord) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.eq("user_number",userNumber);
        Users userResult = usersMapper.selectOne(queryWrapper);
        return userResult;
    }

    @Override
    public void removePassword(String userNumber,String userPassword) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.eq("user_number",userNumber);
        Users userResult = usersMapper.selectOne(queryWrapper);
        userResult.setUserPassword(userPassword);
        int userRemove = usersMapper.updateById(userResult);
    }


}

