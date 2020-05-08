package com.example.sys.demo.service;

import com.example.sys.demo.VO.VideoVO;
import com.example.sys.demo.entity.VideoInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-14
 */
public interface IVideoInfoService extends IService<VideoInfo> {

    /**保存视频信息*/
    void saveVideo(VideoInfo video);

    /**查找推荐视频*/
    List<VideoInfo> selectAll();

    VideoInfo selectOne(Integer videoId);

    List<VideoVO> select();

}
