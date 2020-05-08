package com.example.sys.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "topic_info_id", type = IdType.AUTO)
    @ApiModelProperty(value = "id",example = "123")
    private Integer topicInfoId;

    /**
     * 话题讨论者头像
     */
    @TableField(exist = false)
    private String userinfoImageUrl;

    /**
     * 话题讨论者名称
     */
    @TableField(exist = false)
    private String userinfoName;

    /**
     * 话题讨论内容
     */
    private String topicComment;

    /**
     * 内容配置图片
     */
    private String topicInfoImageUrl;

    /**
     * 话题讨论时间
     */
    private LocalDateTime topicInfoTime;

    @ApiModelProperty(value = "用户id",example = "123")
    private Integer userId;

    @ApiModelProperty(value = "话题id",example = "123")
    private Integer topicId;

    @ApiModelProperty(value = "收藏id",example = "123")
    private Integer collected;
}
