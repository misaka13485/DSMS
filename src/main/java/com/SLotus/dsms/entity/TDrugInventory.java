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

/**
 * <p>
 * 药品库存表
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_DRUG_INVENTORY")
@ApiModel(value = "TDrugInventory对象", description = "药品库存表")
public class TDrugInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "位置编号")
    @TableId(value = "LOCATION_CODE", type = IdType.NONE)
    private String locationCode;

    @ApiModelProperty(value = "药品编号")
    @TableField("DRUG_ID")
    private String drugID;

    @ApiModelProperty(value = "库存数量")
    @TableField("AMOUNT")
    private Long amount;


}
