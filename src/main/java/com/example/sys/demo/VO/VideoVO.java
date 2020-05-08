package com.example.sys.demo.VO;

import lombok.Data;

/**
 * @author 陈莉
 */
@Data
public class VideoVO {
    /**视频主键*/
    private Integer videoId;

    /**
     * 视频上传者姓名
     */
    private String userinfoName;

    private String userinfoImageUrl;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 视频封面地址
     */
    private String videoImageUrl;

    /**
     * 视频描述
     */
    private String videoLabel;

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

    /**
     * 视频发布者高校
     */
    private String userinfoCollege;

    /**外键*/
    private Integer userId;
}
