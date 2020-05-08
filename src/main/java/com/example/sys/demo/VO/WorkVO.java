package com.example.sys.demo.VO;

import lombok.Data;

import java.util.List;

/**
 * @author 陈莉
 */
@Data
public class WorkVO {
    private List<VideoVO> videoVOS;

    private List<PostVO> postVOS;
}
