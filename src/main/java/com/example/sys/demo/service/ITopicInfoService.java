package com.example.sys.demo.service;

import com.example.sys.demo.entity.TopicInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-18
 */
public interface ITopicInfoService extends IService<TopicInfo> {

    List<TopicInfo> selectAllTopicInfo(Integer topicId);

    void saveTopicInfo(TopicInfo topicInfo);

    TopicInfo selectInfoList(Integer topicId);

}
