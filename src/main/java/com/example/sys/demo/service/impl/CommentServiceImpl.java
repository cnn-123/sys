package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.entity.Comment;
import com.example.sys.demo.mapper.CommentMapper;
import com.example.sys.demo.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectCommentList(Integer foreignId,Integer commentType) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>();
        queryWrapper.eq("foreign_id",foreignId);
        queryWrapper.eq("comment_type",commentType);
        List<Comment> commentList = commentMapper.selectList(queryWrapper);
        return commentList;
    }

    @Override
    public void saveComment(Comment comment) {
        commentMapper.insert(comment);
    }


}
