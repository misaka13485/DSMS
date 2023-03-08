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
 * 药品信息表
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_DRUG_INFO")
@ApiModel(value = "TDrugInfo对象", description = "药品信息表")
public class TDrugInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "药品编号")
    @TableId(value = "DRUG_ID", type = IdType.NONE)
    private String drugID;

    @ApiModelProperty(value = "药品名称")
    @TableField("DRUG_NAME")
    private String drugName;

    @ApiModelProperty(value = "是否处方药")
    @TableField("IS_OTC")
    private String isOTC;

    @ApiModelProperty(value = "药品分类")
    @TableField("DRUG_CLASSIFY")
    private String drugClassify;

    @ApiModelProperty(value = "功能主治")
    @TableField("DRUG_FUNCTION")
    private String drugFunction;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;


}
