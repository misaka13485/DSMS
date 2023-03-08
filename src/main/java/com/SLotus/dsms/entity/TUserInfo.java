package com.SLotus.dsms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_USER_INFO")
@ApiModel(value = "TUserInfo对象", description = "用户信息表")
public class TUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "USER_ID", type = IdType.NONE)
    private String userID;

    @ApiModelProperty(value = "用户名称")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    @TableField("USER_PASSWORD")
    private String userPassword;

    @ApiModelProperty(value = "Token")
    @TableField("USER_TOKEN")
    private String userToken;

    @ApiModelProperty(value = "所属部门")
    @TableField("USER_DEPT")
    private String userDept;

    @ApiModelProperty(value = "用户角色")
    @TableField("USER_ROLE")
    private String userRole;

    @ApiModelProperty(value = "启用标识")
    @TableField("IS_ENABLE")
    private String isEnable;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;


}
