package com.example.sys.demo.service;

import com.example.sys.demo.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import org.hibernate.validator.constraints.UniqueElements;
import org.omg.PortableServer.POA;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-11
 */
public interface IPostService extends IService<Post> {

    /**发帖*/
    void savePost(Post post);

    /**查找发帖人详情*/
    Post selectPost(Integer postId);

    List<Post> selectPostList(Integer userId);

}
