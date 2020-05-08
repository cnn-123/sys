package com.example.sys.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class CommentCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_category_id", type = IdType.AUTO)
    @ApiModelProperty(value = "评论种类id",example = "123")
    private Integer commentCategoryId;

    @ApiModelProperty(value = "评论类型",example = "123")
    private Integer commentCategoryType;

    private String commentCategoryName;


}
