package com.example.sys.demo.controller;


import com.example.sys.demo.VO.*;
import com.example.sys.demo.common.JSONResult;
import com.example.sys.demo.entity.*;
import com.example.sys.demo.service.impl.CollegeServiceImpl;
import com.example.sys.demo.service.impl.CommentCategoryServiceImpl;
import com.example.sys.demo.service.impl.CommentServiceImpl;
import com.example.sys.demo.service.impl.UserinfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.jni.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-09
 */
@Api(value = "用户高校业务操作")
@RestController
@RequestMapping("/demo/college")
public class CollegeController {
    @Autowired
    private CollegeServiceImpl collegeService;

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private CommentCategoryServiceImpl commentCategoryService;

    @Autowired
    private UserinfoServiceImpl userinfoService;

    /**我的高校数据*/
    @ApiOperation(value = "请求我的高校数据",notes = "请求我的高校数据接口")
    @ApiImplicitParam(name = "userId",value = "用户id",dataType = "int",
                        paramType = "query",required = true)
    @PostMapping(value = "/myCollege")
    public List<College> myCollege(Integer userId){
        return collegeService.findMyCollege(userId);
    }

    /**热门高校数据*/
    @ApiOperation(value = "请求热门高校数据",notes = "请求热门高校数据接口")
    @PostMapping(value = "/hotCollege")
    public List<College> hotCollege(){
        return collegeService.findHotCollege();
    }

    /**高校帖子数据*/
    @ApiOperation(value = "请求对应高校帖子数据",notes = "请求对应高校帖子数据接口")
    @ApiImplicitParam(name = "postCollegeId",value = "帖子所属高校id",dataType = "int",
                        paramType = "query",required = true)
    @PostMapping("/getPost")
    public ResultVO getPost(Integer postCollegeId){
        //1.根据高校ID查询帖子
        List<Post> postList = collegeService.postList(postCollegeId);

        //2.帖子类型查询
//        List<Integer> postTypeList = new ArrayList<>();
        //传统遍历
//        for (Post post : postList){
//            postTypeList.add(post.getPostType());
//        }
        //精简遍历
//        List<Integer> postTypeList = postList.stream()
//                .map(e -> e.getPostType())
//                .collect(Collectors.toList());
        List<Category> categoryList = collegeService.categoryList();

        //3.数据拼接
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category : categoryList){
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryName(category.getCategoryName());
            categoryVO.setCategoryType(category.getCategoryType());
            List<PostVO> postVOList = new ArrayList<>();
            for (Post post : postList){
                if (post.getPostType().equals(category.getCategoryType())){
                    PostVO postVO = new PostVO();
                    BeanUtils.copyProperties(post,postVO);
                    Userinfo userinfo = userinfoService.selectList(post.getUserId());
                    postVO.setPostAuthorImage(userinfo.getUserinfoImageUrl());
                    postVO.setPostAuthor(userinfo.getUserinfoName());
                   postVOList.add(postVO);
                }
            }
            categoryVO.setPosts(postVOList);
            categoryVOList.add(categoryVO);
        }
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(categoryVOList);
        return resultVO;
    }

    /**帖子、视频、话题评论详情数据*/
    @ApiOperation(value = "评论数据请求",notes = "评论数据请求接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "foreignId",value = "评论外键id(视频、帖子、话题id)",dataType = "int",
                    paramType = "query",required = true),
            @ApiImplicitParam(name = "commentType",value = "评论类型(视频、帖子、话题)",dataType = "int",
                    paramType = "query",required = true)
    })
    @PostMapping(value = "/postInfo")
    public ResultVO postInfo(Integer foreignId,Integer commentType){
        //根据外键ID和类型确定评论类型
        List<Comment> commentList = commentService.selectCommentList(foreignId, commentType);
        CommentCategory category = commentCategoryService.selectCommentCategory(commentType);

        //2.数据拼接
        CommentCategoryVO commentCategoryVO = new CommentCategoryVO();
        commentCategoryVO.setCategoryName(category.getCommentCategoryName());
        commentCategoryVO.setCommentType(category.getCommentCategoryType());
        List<CommentVO> commentVOList = new ArrayList<>();
        for (Comment comment : commentList){
            if(comment.getCommentType().equals(category.getCommentCategoryType())){
                CommentVO commentVO = new CommentVO();
                BeanUtils.copyProperties(comment,commentVO);
                Userinfo userinfo = userinfoService.selectList(comment.getUserId());
                commentVO.setCommentAuthorImage(userinfo.getUserinfoImageUrl());
                commentVO.setCommentAuthorName(userinfo.getUserinfoName());
                commentVO.setShow(0);
                commentVOList.add(commentVO);
            }
        }
        commentCategoryVO.setCommentVOList(commentVOList);
        ResultVO resultVO = new ResultVO();
        resultVO.setData(commentCategoryVO);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    /**随机抽取三条高校数据*/
    @PostMapping("/randCollege")
    public JSONResult randCollege(){
        List<College> collegeList = collegeService.selectCollegeList();
        return JSONResult.ok(collegeList);
    }
}


