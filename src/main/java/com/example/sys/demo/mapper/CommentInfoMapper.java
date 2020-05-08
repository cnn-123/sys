package com.example.sys.demo.mapper;

import com.example.sys.demo.entity.CommentInfo;
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
public interface CommentInfoMapper extends BaseMapper<CommentInfo> {

    /**查找帖子二级评论*/
    @Select("select comment_info.*,userinfo.userinfo_name from comment_info,userinfo where comment_info.user_id = userinfo.user_id and comment_info.comment_id = #{commentId}")
    List<CommentInfo> selectAll(Integer commentId);

}
