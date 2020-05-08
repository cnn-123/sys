package com.example.sys.demo.service;

import com.example.sys.demo.entity.CommentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-18
 */
public interface ICommentInfoService extends IService<CommentInfo> {

    /**查找帖子二级评论*/
    List<CommentInfo> selectAll(Integer commentId);

    /**保存二级评论至数据库*/
    void saveSecondComment(CommentInfo commentInfo);

}
