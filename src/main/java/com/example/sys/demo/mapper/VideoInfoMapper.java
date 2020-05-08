package com.example.sys.demo.mapper;

import com.example.sys.demo.VO.VideoVO;
import com.example.sys.demo.entity.VideoInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-14
 */
public interface VideoInfoMapper extends BaseMapper<VideoInfo> {

    /**查找视频详情数据*/
    @Select("select video_info.* ,userinfo.userinfo_name, userinfo.userinfo_college,userinfo.userinfo_image_url from video_info,userinfo where video_info.user_id = userinfo.user_id")
    List<VideoVO> selectAll();

}
