package com.example.sys.demo.VO;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 陈莉
 */
@Data
public class PostVO {
    /**
     * 帖子主键
     */
    private Integer postId;

    /**
     * 帖子发布者
     */
    private String postAuthor;

    /**
     * 帖子发布者头像
     */
    private String postAuthorImage;

    /**
     * 帖子内容
     */
    private String postMessage;

    /**
     * 帖子图片
     */
    private String postImage;

    /**
     * 发帖时间
     */
    private LocalDateTime postTime;

    /**
     * 帖子类型
     */
    private Integer postType;
}
