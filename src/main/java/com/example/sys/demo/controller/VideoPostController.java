package com.example.sys.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.sys.demo.VO.PostVO;
import com.example.sys.demo.VO.VideoVO;
import com.example.sys.demo.VO.WorkVO;
import com.example.sys.demo.common.FetchVideoCover;
import com.example.sys.demo.common.JSONResult;
import com.example.sys.demo.entity.Post;
import com.example.sys.demo.entity.Userinfo;
import com.example.sys.demo.entity.VideoInfo;
import com.example.sys.demo.service.impl.PostServiceImpl;
import com.example.sys.demo.service.impl.UserinfoServiceImpl;
import com.example.sys.demo.service.impl.VideoInfoServiceImpl;
import io.swagger.annotations.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈莉
 */
@RequestMapping(value = "/demo")
@Api(value = "用户上传视频发校帖")
@RestController
public class VideoPostController {
    @Autowired
    private UserinfoServiceImpl userinfoService;

    @Autowired
    private VideoInfoServiceImpl videoInfoService;

    @Autowired
    private PostServiceImpl postService;

    /**
     * 用户上传视频
     */
    @ApiOperation(value = "用户上传视频",notes = "用户上传视频接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,
                    dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "videoLabel",value = "视频描述",required = false,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "height",value = "视频高度",required = true,
                    dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name = "width",value = "视频宽度",required = true,
                    dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name = "duration",value = "视频时长",required = true,
                    dataType = "Double",paramType = "query"),
    })
    @PostMapping(value = "/upVideo",headers = "content-type=multipart/form-data")
    public JSONResult upVideo(Integer userId, String videoLabel,Double height,
                              Double width,Double duration,
                              @ApiParam(value = "视频",required = true) @RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        //文件保存的命名空间
        String fileSpace = "C:/u-get";
        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";
        //视频封面保存至数据库路径
        String coverPathDB = "/" + userId + "/cover";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                //视频封面最终保存路径
                String[] fileNameList = fileName.split("\\.");
                String fileNamePreFix = fileNameList[fileNameList.length-2];
//                String fileNamePreFix = fileName.split("\\.")[0];
                if (StringUtils.isNotBlank(fileName)) {
                    //文件上传的最终保存路径
                    String finalVideoPath = fileSpace + uploadPathDB + "/" + fileName;
                    String finalCoverParh = fileSpace + coverPathDB + "/" + fileName;
                    //设置数据库的保存路径
                    uploadPathDB += ("/" + fileName);
                    coverPathDB = coverPathDB + "/" + fileNamePreFix + ".jpg";

                    File outFile = new File(finalVideoPath);
                    File coverFile = new File(finalCoverParh);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    if (coverFile.getParentFile() != null || !coverFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        coverFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
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

        //对视频进行截图
        FetchVideoCover videoInfo = new FetchVideoCover("C:\\ffmpeg\\bin\\ffmpeg.exe");
        videoInfo.getCover(fileSpace + uploadPathDB,fileSpace+ coverPathDB);

        //保存视频进行到数据库
        VideoInfo video = new VideoInfo();
        video.setDuration(duration);
        video.setHeight(height);
        video.setVideoLabel(videoLabel);
        video.setVideoUpTime(LocalDateTime.now());
        video.setUserId(userId);
        video.setWidth(width);
        video.setVideoUrl("http://101.200.188.240/u"+uploadPathDB);
        video.setVideoImageUrl("http://101.200.188.240/u"+coverPathDB);
        videoInfoService.saveVideo(video);
        return JSONResult.ok();
    }

    /**
     * 保存帖子信息
     */
    @PostMapping(value = "/upPostImage")
    @ApiOperation(value = "用户发校帖",notes = "用户发校帖接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,
                    dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "postType",value = "帖子类型",required = true,
                    dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "postMessage",value = "帖子类容",required = true,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "postCollegeId",value = "帖子所属高校id",required = true,
                    dataType = "int",paramType = "query")
    })
    public JSONResult upPostImage(Integer userId, Integer postType,String postMessage, Integer postCollegeId,
                                  @ApiParam(value = "校帖配图",required = false) @RequestParam("file") MultipartFile[] files) throws IOException {
        //文件保存的命名空间
        String fileSpace = "C:/u-get";
        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/post";

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
        //保存帖子信息至数据库
        Userinfo userinfo = userinfoService.selectList(userId);
        Post post = new Post();
        post.setUserId(userId);
        post.setPostImage("http://101.200.188.240/u"+uploadPathDB);
        post.setPostCollegeId(postCollegeId);
        post.setPostType(postType);
        post.setPostMessage(postMessage);
        post.setPostTime(LocalDateTime.now());
        postService.savePost(post);
        return JSONResult.ok(post);
    }


    /**用户作品数据请求*/
    @PostMapping("/getWorks")
    public JSONResult getWorks(Integer userId){
        List<VideoVO> videoVOList = videoInfoService.select();
        List<VideoVO> videoVOS = new ArrayList<>();
        for (VideoVO videoVO : videoVOList){
            if (videoVO.getUserId().equals(userId)){
                videoVOS.add(videoVO);
            }
        }
        List<Post> postList = postService.selectPostList(userId);
        List<PostVO> postVOS = new ArrayList<>();
        for (Post post : postList){
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(post,postVO);
            postVO.setPostAuthor(userinfoService.selectList(userId).getUserinfoName());
            postVO.setPostAuthorImage(userinfoService.selectList(userId).getUserinfoImageUrl());
            postVOS.add(postVO);
        }
        WorkVO workVO = new WorkVO();
        workVO.setVideoVOS(videoVOS);
        workVO.setPostVOS(postVOS);
        return JSONResult.ok(workVO);
    }
}
