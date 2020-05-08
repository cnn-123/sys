package com.example.sys.demo.service.impl;

import com.example.sys.demo.entity.TopicInfo;
import com.example.sys.demo.mapper.TopicInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicInfoServiceImplTest {

    @Autowired
    private TopicInfoMapper topicInfoMapper;

    @Test
    public void selectAllTopicInfo() {
        List<TopicInfo> topicInfoList = topicInfoMapper.selectAllTopicInfo(1);
        System.out.println(topicInfoList);
    }

    @Test
    public void saveTopicInfo() {
    }

    @Test
    public void selectInfoList() {
    }
}
