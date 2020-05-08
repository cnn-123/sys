package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.entity.*;
import com.example.sys.demo.mapper.*;
import com.example.sys.demo.service.ICollegeService;
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
 * @since 2020-02-09
 */
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements ICollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<College> findMyCollege(Integer userId) {
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<Userinfo>();
        queryWrapper.eq("user_id",userId);
        Userinfo userInfoList = userinfoMapper.selectOne(queryWrapper);
        QueryWrapper<College> collegeQueryWrapper = new QueryWrapper<College>();
        collegeQueryWrapper.eq("college_name",userInfoList.getUserinfoCollege());
        List<College> collegeResult = collegeMapper.selectList(collegeQueryWrapper);
        return collegeResult;
    }

    @Override
    public List<College> findHotCollege() {
        List<College> collegeResult = collegeMapper.selectList(null);
        return collegeResult;
    }

    @Override
    public List<Post> postList(Integer postCollegeId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>();
        queryWrapper.eq("post_college_id",postCollegeId);
        List<Post> postList = postMapper.selectList(queryWrapper);
        return postList;
    }

    @Override
    public List<Category> categoryList() {
        List<Category> categoryList = categoryMapper.selectList(null);
        return categoryList;
    }

    @Override
    public List<College> selectCollegeList() {
        List<College> collegeList = collegeMapper.collegeList();
        return collegeList;
    }


}
