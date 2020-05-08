package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.entity.Post;
import com.example.sys.demo.mapper.PostMapper;
import com.example.sys.demo.service.IPostService;
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
 * @since 2020-02-11
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public void savePost(Post post) {
        postMapper.insert(post);
    }

    @Override
    public Post selectPost(Integer postId) {
        return postMapper.selectById(postId);
    }

    @Override
    public List<Post> selectPostList(Integer userId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>();
        queryWrapper.eq("user_id",userId);
        List<Post> postList = postMapper.selectList(queryWrapper);
        return postList;
    }


}
