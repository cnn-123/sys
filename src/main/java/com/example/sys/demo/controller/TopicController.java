package com.example.sys.demo.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.sys.demo.common.JSONResult;
import com.example.sys.demo.entity.Topic;
import com.example.sys.demo.entity.TopicInfo;
import com.example.sys.demo.service.impl.CommentServiceImpl;
import com.example.sys.demo.service.impl.TopicInfoServiceImpl;
import com.example.sys.demo.service.impl.TopicServiceImpl;
import com.example.sys.demo.service.impl.UserinfoServiceImpl;
import io.swagger.annotations.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-09
 */
@Api(value = "话题数据请求")
@RestController
@RequestMapping("/demo/topic")
public class TopicController {
    @Autowired
    private TopicServiceImpl topicService;

    @Autowired
    private TopicInfoServiceImpl topicInfoService;

    @Autowired
    private UserinfoServiceImpl userinfoService;

    /**返回每周话题数据*/
    @ApiOperation(value = "每周话题数据请求",notes = "每周话题数据请求接口")
    @PostMapping("/returnList")
    public List<Topic> returnList(){
        return topicService.topicList();
    }

    /**话题讨论页面数据加载*/
    @ApiOperation(value = "单个话题详情数据请求",notes = "单个话题详情数据请求接口")
    @ApiImplicitParam(name = "topicId",value = "话题id",dataType = "int",
                        paramType = "query",required = true)
    @PostMapping("/getTopicInfo")
    public JSONResult getTopicInfo(Integer topicId){
        List<TopicInfo> infoList = topicInfoService.selectAllTopicInfo(topicId);
        return JSONResult.ok(infoList);
    }

    /**发布话题讨论详情，保存到数据库*/
    @ApiOperation(value = "用户发表话题详情讨论",notes = "用户发表话题详情讨论接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",dataType = "int",
                    paramType = "query",required = true),
            @ApiImplicitParam(name = "topicComment",value = "讨论内容",dataType = "String",
                    paramType = "query",required = true),
            @ApiImplicitParam(name = "topicId",value = "话题id",dataType = "int",
                    paramType = "query",required = true)
    })
    @PostMapping("/saveTopicInfo")
    public JSONResult saveTopicInfo(Integer userId, String topicComment,Integer topicId,
                                   @ApiParam(value = "话题讨论详情配图",required = false) @RequestParam("file") MultipartFile[] files) throws IOException {
        //文件保存的命名空间
        String fileSpace = "C:/u-get";
        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/topic";

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

        TopicInfo topicInfo = new TopicInfo();
        topicInfo.setTopicInfoTime(LocalDateTime.now());
        topicInfo.setTopicComment(topicComment);
        topicInfo.setTopicId(topicId);
        topicInfo.setUserId(userId);
        topicInfo.setTopicInfoImageUrl("http://101.200.188.240/u"+uploadPathDB);
        topicInfoService.saveTopicInfo(topicInfo);
        return JSONResult.ok();
    }

    /**话题讨论评论详情页面数据*/
    @PostMapping("/getDetail")
    public JSONResult getDetail(Integer topicInfoId){
        TopicInfo topicInfo = topicInfoService.selectInfoList(topicInfoId);
        topicInfo.setUserinfoName(userinfoService.selectList(topicInfo.getUserId()).getUserinfoName());
        topicInfo.setUserinfoImageUrl(userinfoService.selectList(topicInfo.getUserId()).getUserinfoImageUrl());
        return JSONResult.ok(topicInfo);
    }
}
