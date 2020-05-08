package com.example.sys.demo.service;

import com.example.sys.demo.entity.Topic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-09
 */
public interface ITopicService extends IService<Topic> {
    /**查找每周话题数据*/
    List<Topic> topicList();

}
