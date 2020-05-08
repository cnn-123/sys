package com.example.sys.demo.service.impl;

import com.example.sys.demo.entity.Topic;
import com.example.sys.demo.mapper.TopicMapper;
import com.example.sys.demo.service.ITopicService;
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
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {

    @Autowired
    private TopicMapper topicMapper;
    @Override
    public List<Topic> topicList() {
        List<Topic> topicList = topicMapper.selectList(null);
        return topicList;
    }
}
