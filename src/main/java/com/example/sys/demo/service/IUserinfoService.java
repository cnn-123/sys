package com.example.sys.demo.service;

import com.example.sys.demo.entity.Userinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-08
 */
public interface IUserinfoService extends IService<Userinfo> {
    /**判断用户是否为第一次完善详情信息*/
    Userinfo userInfoIdIsExist(Integer userId);
    /**保存用户详情信息*/
    void saveUserInfo(Userinfo userinfo);

    /**更新用户详情信息*/
    void updateUserInfo(Userinfo userinfo);

    /**查找用户详情ID*/
    Userinfo selectList(Integer userId);

    /**头像上传*/
//    void upFaceImage(Integer userId,String url);
}
