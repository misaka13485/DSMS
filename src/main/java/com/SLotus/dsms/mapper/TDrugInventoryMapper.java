package com.SLotus.dsms.mapper;

import com.SLotus.dsms.entity.TDrugInventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 药品库存表 Mapper 接口
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
public interface TDrugInventoryMapper extends BaseMapper<TDrugInventory> {

    /**
     * 根据ID获取库存并锁定
     */
    TDrugInventory selectByIdForUpdate(String id);

}
