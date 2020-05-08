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
 * @since 2020-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * //帖子主键
     */
    @ApiModelProperty(value = "帖子主键",example = "123")
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    /**
     * //帖子内容
     */
    private String postMessage;

    /**
     * //帖子图片
     */
    private String postImage;

    /**
     * //发帖时间
     */
    private LocalDateTime postTime;

    /**
     * //帖子类型，0表示求助帖，1表示娱乐贴
     */
    @ApiModelProperty(value = "帖子类型",example = "123")
    private Integer postType;

    /**
     * //帖子所属高校
     */
    @ApiModelProperty(value = "帖子所属高校",example = "123")
    private Integer postCollegeId;

    /**
     * //帖子发布者ID
     */
    @ApiModelProperty(value = "帖子发布者ID",example = "123")
    private Integer userId;


}
