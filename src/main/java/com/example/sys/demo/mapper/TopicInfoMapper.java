package com.example.sys.demo.mapper;

import com.example.sys.demo.entity.TopicInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-18
 */
public interface TopicInfoMapper extends BaseMapper<TopicInfo> {

    /**话题详情页面数据加载*/
    @Select("select topic_info.*,userinfo.userinfo_name,userinfo.userinfo_image_url from topic_info,userinfo " +
            "where topic_info.user_id = userinfo.user_id and topic_info.topic_id = #{topicId}")
    List<TopicInfo> selectAllTopicInfo(Integer topicId);

}
