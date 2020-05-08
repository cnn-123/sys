package com.example.sys.demo.service;

import com.example.sys.demo.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.hibernate.validator.constraints.Mod10Check;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-12
 */
public interface ICommentService extends IService<Comment> {

    /**查找评论数据*/
    List<Comment> selectCommentList(Integer foreignId,Integer commentType);

    /**保存评论数据*/
    void saveComment(Comment comment);

}
