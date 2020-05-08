package com.example.sys.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "用户详情id",example = "123")
    @TableId(value = "userinfo_id", type = IdType.AUTO)
    private Integer userinfoId;

    /**
     * 昵称
     */
    private String userinfoName;

    /**
     * 性别
     */
    private String userinfoSex;

    /**
     * 生日
     */
    private String userinfoBirthday;

    /**
     * 星座
     */
    private String userinfoStar;

    /**
     * 简介
     */
    private String userinfoLabel;

    /**
     * 所属高校
     */
    private String userinfoCollege;

    /**
     * 外键
     */
    @ApiModelProperty(value = "用户id",example = "123")
    private Integer userId;

    /**
     * 用户头像
     */
    private String userinfoImageUrl;


}
