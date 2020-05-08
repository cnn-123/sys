package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.entity.Userinfo;
import com.example.sys.demo.mapper.UserinfoMapper;
import com.example.sys.demo.service.IUserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-08
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public Userinfo userInfoIdIsExist(Integer userId) {
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<Userinfo>();
        queryWrapper.eq("user_id",userId);
        Userinfo userInfoResult = userinfoMapper.selectOne(queryWrapper);
        return userInfoResult;
    }

    @Override
    public void saveUserInfo(Userinfo userinfo) {
        userinfoMapper.insert(userinfo);
    }

    @Override
    public void updateUserInfo(Userinfo userinfo) {
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<Userinfo>();
        queryWrapper.eq("user_id",userinfo.getUserId());
        Userinfo userUpdate = userinfoMapper.selectOne(queryWrapper);
        userUpdate.setUserinfoName(userinfo.getUserinfoName());
        userUpdate.setUserinfoSex(userinfo.getUserinfoSex());
        userUpdate.setUserinfoStar(userinfo.getUserinfoStar());
        userUpdate.setUserinfoLabel(userinfo.getUserinfoLabel());
        userUpdate.setUserinfoCollege(userinfo.getUserinfoCollege());
        userUpdate.setUserinfoImageUrl(userinfo.getUserinfoImageUrl());
        userUpdate.setUserinfoBirthday(userinfo.getUserinfoBirthday());
        userUpdate.setUserId(userinfo.getUserId());
        userinfoMapper.updateById(userUpdate);
    }

    @Override
    public Userinfo selectList(Integer userId) {
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<Userinfo>();
        queryWrapper.eq("user_id",userId);
        Userinfo userList = userinfoMapper.selectOne(queryWrapper);
        return userList;
    }

}
