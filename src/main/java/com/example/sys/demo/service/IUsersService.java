package com.example.sys.demo.service;

import com.example.sys.demo.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-06
 */
public interface IUsersService extends IService<Users> {
    /**判断用户是否重复*/
    boolean queryUserNumberIsExist(String userNumber);

    /**保存用户信息（用户注册）*/
    int saveUser(Users users);

    /**判断用户是否存在*/
    Users queryUserLogin(String userNumber,String userPassword);

    /**判断原密码是否正确*/
    Users queryUserPasswordIsTrue(String userNumber,String userPassWord);

    /**修改密码*/
    void removePassword(String userNumber, String userPassword);

}
