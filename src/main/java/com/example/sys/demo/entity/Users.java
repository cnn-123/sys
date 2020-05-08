package com.example.sys.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户id",example = "123")
    private Integer userId;

    /**
     * 用户注册账号，手机号码
     */
    private String userNumber;

    /**
     * 账号密码
     */
    private String userPassword;

    /**
     * 注册时间
     */
    private LocalDateTime userTime;

    /**
     * 用户身份
     */
    private String userRole;

    @Override
    public String toString(){
        return "User{" +
                "userId=" + userId +
                ", userNumber='" + userNumber + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }


}
