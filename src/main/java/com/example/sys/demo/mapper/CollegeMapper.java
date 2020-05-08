package com.example.sys.demo.mapper;

import com.example.sys.demo.entity.College;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-09
 */
public interface CollegeMapper extends BaseMapper<College> {

    /**随机抽取三条高校数据*/
    @Select("select * from college order by rand() limit 3")
    List<College> collegeList();

}
