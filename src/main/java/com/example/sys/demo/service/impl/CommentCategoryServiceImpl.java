package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.entity.CommentCategory;
import com.example.sys.demo.mapper.CommentCategoryMapper;
import com.example.sys.demo.service.ICommentCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-12
 */
@Service
public class CommentCategoryServiceImpl extends ServiceImpl<CommentCategoryMapper, CommentCategory> implements ICommentCategoryService {

    @Autowired
    private CommentCategoryMapper commentCategoryMapper;

    @Override
    public CommentCategory selectCommentCategory(Integer commentType) {
        QueryWrapper<CommentCategory> queryWrapper = new QueryWrapper<CommentCategory>();
        queryWrapper.eq("comment_category_type",commentType);
        return commentCategoryMapper.selectOne(queryWrapper);
    }
}
