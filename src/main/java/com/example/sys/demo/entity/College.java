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
public class College implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "college_id", type = IdType.AUTO)
    @ApiModelProperty(value = "大学id",example = "123")
    private Integer collegeId;

    /**
     * 高校名称
     */
    private String collegeName;

    /**
     * 高校图标地址
     */
    private String collegeImageUrl;


}
