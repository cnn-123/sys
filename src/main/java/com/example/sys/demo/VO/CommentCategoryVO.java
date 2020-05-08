package com.example.sys.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 陈莉
 */
@Data
public class CommentCategoryVO {

    @JsonProperty("type")
    private Integer commentType;

    @JsonProperty("name")
    private String categoryName;

    private List<CommentVO> commentVOList;
}
