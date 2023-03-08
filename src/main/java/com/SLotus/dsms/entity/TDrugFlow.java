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
import java.time.LocalDateTime;

/**
 * <p>
 * 药品出入库流水表
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_DRUG_FLOW")
@ApiModel(value = "TDrugFlow对象", description = "药品出入库流水表")
public class TDrugFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序列号")
    @TableId(value = "SERIAL_NO", type = IdType.NONE)
    private String serialNo;

    @ApiModelProperty(value = "药品编号")
    private String drugId;

    @ApiModelProperty(value = "位置编号")
    private String locationCode;

    @ApiModelProperty(value = "处方标号")
    private String prescriptionNo;

    @ApiModelProperty(value = "出入库数量")
    private Long amount;

    @ApiModelProperty(value = "操作")
    private String operate;

    @ApiModelProperty(value = "操作人")
    private String operateUser;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime createdTime;


}
