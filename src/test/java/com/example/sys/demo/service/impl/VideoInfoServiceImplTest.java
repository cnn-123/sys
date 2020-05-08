package com.example.sys.demo.service.impl;

import com.example.sys.demo.entity.VideoInfo;
import com.example.sys.demo.mapper.VideoInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoInfoServiceImplTest {
    @Autowired
    private VideoInfoMapper videoInfoMapper;

    @Test
    public void selectAll() {
        List<VideoInfo> videoInfo = videoInfoMapper.selectList(null);
        System.out.println(videoInfo.size());
    }
}
