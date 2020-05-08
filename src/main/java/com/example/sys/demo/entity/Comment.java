package com.example.sys.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

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
 * @since 2020-02-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_id", type = IdType.AUTO)
    @ApiModelProperty(value = "评论id",example = "123")
    private Integer commentId;

    /**
     * 评论内容
     */
    private String commentTopic;

    /**
     * 评论时间
     */
    private LocalDateTime commentTime;

    /**
     * //0为视频，1为帖子，2为话题
     */
    @ApiModelProperty(value = "评论种类",example = "123")
    private Integer commentType;

    /**
     * 评论者ID
     */
    @ApiModelProperty(value = "评论者ID",example = "123")
    private Integer userId;

    /**
     * 统一为视频、帖子、话题ID
     */
    @ApiModelProperty(value = "外键id",example = "123")
    private Integer foreignId;

}
