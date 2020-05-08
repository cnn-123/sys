package com.example.sys.demo.controller;

import com.example.sys.demo.common.JSONResult;
import com.example.sys.demo.entity.*;
import com.example.sys.demo.service.impl.*;
import com.example.sys.demo.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-06
 */
@Api(value = "用户注册登录操作")
@RestController
@RequestMapping("/demo/users")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    /**
     * 用户注册
     */
    @ApiOperation(value = "用户注册",notes = "用户注册接口")
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody Users users) throws Exception {
        //1.判断用户名和密码不为空
        if (StringUtils.isBlank(users.getUserNumber()) || StringUtils.isBlank(users.getUserPassword())) {
            return JSONResult.errorMsg("用户名和密码不能为空");
        }
        //2.判断用户名是否重复
        boolean userNumberIsExist = usersService.queryUserNumberIsExist(users.getUserNumber());
        //3.保存用户注册信息
        if (!userNumberIsExist) {
            users.setUserNumber(users.getUserNumber());
            users.setUserPassword(MD5Utils.getMD5Str(users.getUserPassword()));
            users.setUserTime(LocalDateTime.now());
            usersService.saveUser(users);
        } else {
            return JSONResult.errorMsg("用户名重复");
        }
        return JSONResult.ok();
    }
//    public ResultJson regist(@RequestBody User user){
//        if (StringUtils.isAnyBlank(user.getUserPassword(),user.getUserNumber())) {
//            return ResultJson.failure(ResultCode.BAD_REQUEST);
//        }
//        boolean userNumberIsExist = usersService.queryUserNumberIsExist(user.getUserNumber());
//        if (!userNumberIsExist){
//            UserDetail userDetail = new UserDetail(user.getUserNumber(),user.getUserPassword(), Role.builder().id(1L).build());
//            return ResultJson.ok(usersService.register(userDetail));
//        }else {
//            return ResultJson.failure(ResultCode.BAD_REQUEST,"用户名已存在");
//        }
//    }

    /**
     * 用户登录
     * @return
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录",notes = "用户登录接口")
    public JSONResult login(@Valid @RequestBody Users users) throws Exception {
        //1.判断用户名和密码是否为空
        if (StringUtils.isBlank(users.getUserNumber()) || StringUtils.isBlank(users.getUserPassword())) {
            return JSONResult.errorMsg("用户名和密码不能为空");
        }
        //2.判断用户名是否存在
        Users userResult = usersService.queryUserLogin(users.getUserNumber(), MD5Utils.getMD5Str(users.getUserPassword()));
        //3.返回
        if (userResult != null) {
            userResult.setUserPassword("");
            return JSONResult.ok(userResult);
        } else {
            return JSONResult.errorMsg("用户名或密码错误");
        }
//        final ResponseUserToken response = usersService.login(user.getUserNumber(),user.getUserPassword());
//        return ResultJson.ok(response);
    }

    /**
     * 修改密码
     */
    @PostMapping(value = "/remove")
    @ApiOperation(value = "修改密码",notes = "修改密码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNumber", value = "用户账号", required = true,
                    dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "oldPassword", value = "新密码", required = true,
                    dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "确认新密码", required = true,
                    dataType = "String", paramType = "query"),
    })
    public JSONResult remove(String userNumber, String oldPassword, String newPassword) {
        //1.判断用户名、原密码、新密码不能为空
        if (StringUtils.isBlank(userNumber) || StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            return JSONResult.errorMsg("用户名、原密码、新密码不能为空");
        }
        //2.判断用户名是否正确
        Users userResult = usersService.queryUserPasswordIsTrue(userNumber, oldPassword);
        boolean userNumberIsExist = usersService.queryUserNumberIsExist(userNumber);
        //3.保存信息
        if (!userNumberIsExist) {
            return JSONResult.errorMsg("用户名错误");
        } else if(oldPassword.equals(newPassword)){
            usersService.removePassword(userNumber, newPassword);
        }else {
            return JSONResult.errorMsg("两次密码输入不相同");
        }
//        if (oldPassword.equals(userResult.getUserPassword())) {
//            usersService.removePassword(userNumber, newPassword);
//        } else {
//            return JSONResult.errorMsg("原密码错误");
//
//        }
        return JSONResult.ok();
    }

}
