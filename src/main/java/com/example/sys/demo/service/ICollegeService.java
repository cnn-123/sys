package com.example.sys.demo.service;

import com.example.sys.demo.entity.Category;
import com.example.sys.demo.entity.College;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sys.demo.entity.Post;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-09
 */
public interface ICollegeService extends IService<College> {
    /**查找我的高校数据*/
    List<College> findMyCollege(Integer userId);

    /**查找热门高校数据*/
    List<College> findHotCollege();

    /**高校帖子数据*/
    List<Post> postList(Integer postCollegeId);

    /**帖子类目*/
    List<Category> categoryList();

    /**随机抽取三条高校数据*/
    List<College> selectCollegeList();

}
