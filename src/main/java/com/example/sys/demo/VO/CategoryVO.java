package com.example.sys.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 陈莉
 */
@Data
public class CategoryVO {

    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    private List<PostVO> posts;
}
