package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.entity.TopicInfo;
import com.example.sys.demo.mapper.TopicInfoMapper;
import com.example.sys.demo.service.ITopicInfoService;
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
public class TopicInfoServiceImpl extends ServiceImpl<TopicInfoMapper, TopicInfo> implements ITopicInfoService {

    @Autowired
    private TopicInfoMapper topicInfoMapper;

    @Override
    public List<TopicInfo> selectAllTopicInfo(Integer topicId) {
        List<TopicInfo> topicInfoList = topicInfoMapper.selectAllTopicInfo(topicId);
        return topicInfoList;
    }

    @Override
    public void saveTopicInfo(TopicInfo topicInfo) {
        topicInfoMapper.insert(topicInfo);
    }

    @Override
    public TopicInfo selectInfoList(Integer topicInfoId) {
        QueryWrapper<TopicInfo> queryWrapper = new QueryWrapper<TopicInfo>();
        queryWrapper.eq("topic_info_id",topicInfoId);
        TopicInfo topicInfo = topicInfoMapper.selectOne(queryWrapper);
        return topicInfo;
    }


}
