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
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "collection_id", type = IdType.AUTO)
    @ApiModelProperty(value = "收藏id",example = "123")
    private Integer collectionId;

    /**
     * 收藏时间
     */
    private LocalDateTime collectionTime;

    /**
     * 收藏者id
     */
    @ApiModelProperty(value = "收藏者id",example = "123")
    private Integer userId;

    /**
     * 收藏视频id
     */
    @ApiModelProperty(value = "收藏视频id",example = "123")
    private Integer videoId;


}
