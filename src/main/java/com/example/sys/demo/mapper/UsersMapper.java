package com.example.sys.demo.mapper;

import com.example.sys.demo.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-06
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}
