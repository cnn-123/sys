package com.example.sys.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 视频ID
     */
    @ApiModelProperty(value = "视频ID",example = "123")
    @TableId(value = "video_id", type = IdType.AUTO)
    private Integer videoId;

    /**
     * 视频封面地址
     */
    private String videoImageUrl;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 视频描述
     */
    private String videoLabel;

    /**
     * 视频高度
     */
    private LocalDateTime videoUpTime;


    /**
     * 视频高度
     */
    private Double height;

    /**
     * 视频宽度
     */
    private Double width;

    /**
     * 视频时长
     */
    private Double duration;

    @ApiModelProperty(value = "用户id",example = "123")
    private Integer userId;


}
