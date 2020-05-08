package com.example.sys.demo.service;

import com.example.sys.demo.entity.CommentCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-12
 */
public interface ICommentCategoryService extends IService<CommentCategory> {

    CommentCategory selectCommentCategory(Integer commentType);

}
