package com.example.sys.demo.mapper;

import com.example.sys.demo.VO.CollectionVO;
import com.example.sys.demo.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
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
public interface CollectionMapper extends BaseMapper<Collection> {

    /**求用户收藏的视频数据*/
    @Select("select collection.collection_id, video_info.video_url,video_info.video_label,video_info.video_id,video_info.video_image_url," +
            "userinfo.userinfo_name,userinfo.userinfo_image_url from video_info,collection,userinfo where collection.user_id = userinfo.user_id " +
            "and collection.video_id = video_info.video_id "+"and collection.user_id =#{userId}")
    List<CollectionVO> selectAll(Integer userId);


}
