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
 * @since 2020-02-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "topic_id", type = IdType.AUTO)
    @ApiModelProperty(value = "id",example = "123")
    private Integer topicId;

    /**
     * 话题名称
     */
    private String topicName;

    /**
     * 话题关注量
     */
    private String topicNumber;

    /**
     * 话题图片
     */
    private String topicImageUrl;


}
