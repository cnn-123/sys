package com.example.sys.demo.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.sys.demo.common.JSONResult;
import com.example.sys.demo.entity.Userinfo;
import com.example.sys.demo.service.impl.UserinfoServiceImpl;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-08
 */
@RestController
@Api(value = "用户完善详情信息")
@RequestMapping("/demo/userinfo")
public class UserinfoController {
    @Autowired
    private UserinfoServiceImpl userinfoService;
    /**页面加载*/
    @ApiOperation(value = "请求详情页面数据",notes = "请求详情页面数据的接口")
    @PostMapping(value = "/onLoad")
    @ApiImplicitParam(name = "userId", value = "用户Id", required = true,
            dataType = "int", paramType = "query")
    public JSONResult onload(Integer userId){
        return JSONResult.ok(userinfoService.selectList(userId));
    }


    /**保存用户详情信息*/
    @ApiOperation(value = "用户上传详情信息",notes = "用户上传详情信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,
                    dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "userinfoName",value = "用户昵称",required = false,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userinfoSex",value = "用户性别",required = false,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userinfoBirthday",value = "用户生日",required = false,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userinfoStar",value = "用户星座",required = false,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userinfoLabel",value = "用户签名",required = false,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userinfoCollege",value = "用户所属高校",required = true,
                    dataType = "String",paramType = "query")
    })
    @PostMapping(value = "/upFace")
    public JSONResult upFace(Integer userId, String userinfoName, String userinfoSex, String userinfoBirthday,
                             String userinfoStar, String userinfoLabel, String userinfoCollege,
                             @ApiParam(value = "头像",required = true) @RequestParam("file") MultipartFile[] files) throws IOException {
        //文件保存的命名空间
        String fileSpace = "C:/u-get";
        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/face";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length > 0) {
                String fileName = files[0].getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    //文件上传的最终保存路径
                    String finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
                    //设置数据库的保存路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }else{
                return JSONResult.errorMsg("上传出错...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传出错...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        //1.判断用户是否为第一次完善信息
        Userinfo userInfoResult = userinfoService.userInfoIdIsExist(userId);

        //2.若是，则保存用户详情信息
        if (userInfoResult == null){
            Userinfo userinfo = new Userinfo();
            String userinfoUrl = "http://101.200.188.240/u"+ uploadPathDB;
            userinfo.setUserinfoImageUrl(userinfoUrl);
            userinfo.setUserinfoBirthday(userinfoBirthday);
            userinfo.setUserId(userId);
            userinfo.setUserinfoCollege(userinfoCollege);
            userinfo.setUserinfoLabel(userinfoLabel);
            userinfo.setUserinfoStar(userinfoStar);
            userinfo.setUserinfoSex(userinfoSex);
            userinfo.setUserinfoName(userinfoName);
            userinfoService.saveUserInfo(userinfo);
        }//3.若否，则更新用户详情信息
        else {
            Userinfo userinfo = new Userinfo();
            if (files[0].equals(userInfoResult.getUserinfoImageUrl())){
                userinfo.setUserinfoImageUrl(userInfoResult.getUserinfoImageUrl());
            }else {
                String userinfoUrl = "http://101.200.188.240/u"+ uploadPathDB;
                userinfo.setUserinfoImageUrl(userinfoUrl);
            }
            userinfo.setUserinfoBirthday(userinfoBirthday);
            userinfo.setUserId(userId);
            userinfo.setUserinfoCollege(userinfoCollege);
            userinfo.setUserinfoLabel(userinfoLabel);
            userinfo.setUserinfoStar(userinfoStar);
            userinfo.setUserinfoSex(userinfoSex);
            userinfo.setUserinfoName(userinfoName);
            userinfoService.updateUserInfo(userinfo);
        }

        return JSONResult.ok();
    }
}
