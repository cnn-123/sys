package com.example.sys.demo.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 陈莉
 */
@Data
public class CollectionVO {

    /**
     * id
     */
    @JsonProperty("id")
    private Integer collectionId;

    /**
     * 用户头像
     */
    @JsonProperty("image")
    private String userinfoImageUrl;

    /**
     * 昵称
     */
    @JsonProperty("name")
    private String userinfoName;

    /**
     * 视频描述
     */
    @JsonProperty("label")
    private String videoLabel;

    /**
     * 视频地址
     */
    @JsonProperty("url")
    private String videoUrl;

    /**
     * 视频id
     */
    @JsonProperty("videoId")
    private Integer videoId;

    /**
     * 视频封面
     */
    @JsonProperty("videoImageUrl")
    private String videoImageUrl;
}
