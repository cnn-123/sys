package com.example.sys.demo.controller;


import com.example.sys.demo.VO.CommentVO;
import com.example.sys.demo.common.JSONResult;
import com.example.sys.demo.entity.Comment;
import com.example.sys.demo.entity.CommentInfo;
import com.example.sys.demo.entity.Post;
import com.example.sys.demo.entity.Userinfo;
import com.example.sys.demo.service.impl.CommentInfoServiceImpl;
import com.example.sys.demo.service.impl.CommentServiceImpl;
import com.example.sys.demo.service.impl.PostServiceImpl;
import com.example.sys.demo.service.impl.UserinfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-12
 */
@Api(value = "视频、帖子、话题评论相关业务")
@RestController
@RequestMapping("/demo/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private CommentInfoServiceImpl commentInfoService;

    @Autowired
    private UserinfoServiceImpl userinfoService;

    /**
     * 帖子一级评论、视频评论、话题详情评论
     */
    @ApiOperation(value = "用户评论",notes = "用户评论接口")
    @PostMapping(value = "/replyComment")
    public JSONResult replyPost(@RequestBody Comment comment) {
        comment.setCommentTime(LocalDateTime.now());
        commentService.saveComment(comment);
        CommentVO param = new CommentVO();
        BeanUtils.copyProperties(comment,param);
        param.setCommentAuthorName(userinfoService.selectList(comment.getUserId()).getUserinfoName());
        param.setCommentAuthorImage(userinfoService.selectList(comment.getUserId()).getUserinfoImageUrl());
        return JSONResult.ok(param);
    }

    /**请求帖子二级评论数据*/
    @ApiOperation(value = "请求帖子二级评论",notes = "请求帖子二级评论接口")
    @ApiImplicitParam(name = "commentId",value = "评论id",dataType = "int",
                        paramType = "query",required = true)
    @PostMapping("/getSecond")
    public JSONResult getSecond(Integer commentId){
        List<CommentInfo> commentInfoList = commentInfoService.selectAll(commentId);
        return  JSONResult.ok(commentInfoList);
    }

    /**发布帖子二级评论并保存至数据库*/
    @ApiOperation(value = "用户发表帖子二级评论",notes = "用户发表帖子二级评论接口")
    @PostMapping("/saveSecond")
    public JSONResult saveSecond(@RequestBody CommentInfo commentInfo){
        commentInfo.setCommentInfoTime(LocalDateTime.now());
        commentInfoService.saveSecondComment(commentInfo);
        CommentInfo info = new CommentInfo();
        BeanUtils.copyProperties(commentInfo,info);
        String userInfoName = userinfoService.selectList(commentInfo.getUserId()).getUserinfoName();
        info.setUserinfoName(userInfoName);
        return JSONResult.ok(info);
    }


}
