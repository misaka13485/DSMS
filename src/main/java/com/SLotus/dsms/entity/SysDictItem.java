package com.SLotus.dsms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SYS_DICT_ITEM")
@ApiModel(value = "SysDictItem对象", description = "")
public class SysDictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字段组")
    private String dictKey;

    @ApiModelProperty(value = "码值")
    @TableId(value = "KEY_", type = IdType.NONE)
    private String key;

    @ApiModelProperty(value = "含义")
    private String keyValue;


}
