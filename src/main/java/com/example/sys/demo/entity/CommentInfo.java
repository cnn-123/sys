package com.example.sys.demo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CommentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "comment_info_id", type = IdType.AUTO)
    @ApiModelProperty(value = "二级评论id",example = "123")
    private Integer commentInfoId;

    /**
     * 回复者昵称
     */
    @TableField(exist = false)
    private String userinfoName;

    /**
     * 回复内容
     */
    private String commentInfo;

    /**
     * 回复时间
     */
    private LocalDateTime commentInfoTime;

    /**
     * 回复者ID
     */
    @ApiModelProperty(value = "回复者ID",example = "123")
    private Integer userId;

    /**
     * 帖子一级评论id
     */
    @ApiModelProperty(value = "帖子一级评论id",example = "123")
    private Integer commentId;


}
