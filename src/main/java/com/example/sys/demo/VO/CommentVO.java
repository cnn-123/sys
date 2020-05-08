package com.example.sys.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author 陈莉
 */
@Data
public class CommentVO {
    @JsonProperty("id")
    private Integer commentId;

    @JsonProperty("image")
    private String commentAuthorImage;

    @JsonProperty("name")
    private String commentAuthorName;

    @JsonProperty("comment")
    private String commentTopic;

    @JsonProperty("time")
    private LocalDateTime commentTime;

    @JsonProperty("show")
    private Integer show;
}
