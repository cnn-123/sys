package com.example.sys.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.demo.VO.VideoVO;
import com.example.sys.demo.entity.VideoInfo;
import com.example.sys.demo.mapper.VideoInfoMapper;
import com.example.sys.demo.service.IVideoInfoService;
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
 * @since 2020-02-14
 */
@Service
public class VideoInfoServiceImpl extends ServiceImpl<VideoInfoMapper, VideoInfo> implements IVideoInfoService {

    @Autowired
    private VideoInfoMapper videoInfoMapper;
    @Override
    public void saveVideo(VideoInfo video) {
        videoInfoMapper.insert(video);
    }

    @Override
    public List<VideoInfo> selectAll() {
        List<VideoInfo> videoInfo = videoInfoMapper.selectList(null);
        return videoInfo;
    }

    @Override
    public VideoInfo selectOne(Integer videoId) {
        QueryWrapper<VideoInfo> queryWrapper = new QueryWrapper<VideoInfo>();
        queryWrapper.eq("video_id",videoId);
        VideoInfo videoInfo = videoInfoMapper.selectOne(queryWrapper);
        return videoInfo;
    }

    @Override
    public List<VideoVO> select() {
        List<VideoVO> videoVOList = videoInfoMapper.selectAll();
        return videoVOList;
    }
}
