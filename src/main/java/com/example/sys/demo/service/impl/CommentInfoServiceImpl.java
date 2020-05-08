package com.example.sys.demo.service.impl;

import com.example.sys.demo.entity.CommentInfo;
import com.example.sys.demo.mapper.CommentInfoMapper;
import com.example.sys.demo.service.ICommentInfoService;
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
 * @since 2020-02-18
 */
@Service
public class CommentInfoServiceImpl extends ServiceImpl<CommentInfoMapper, CommentInfo> implements ICommentInfoService {

    @Autowired
    private CommentInfoMapper commentInfoMapper;

    @Override
    public List<CommentInfo> selectAll(Integer commentId) {
        List<CommentInfo> commentInfoList = commentInfoMapper.selectAll(commentId);
        return commentInfoList;
    }

    @Override
    public void saveSecondComment(CommentInfo commentInfo) {
        commentInfoMapper.insert(commentInfo);
    }
}
