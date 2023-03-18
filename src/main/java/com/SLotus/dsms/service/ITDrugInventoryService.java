package com.SLotus.dsms.service;

import com.SLotus.dsms.entity.TDrugInventory;
import com.SLotus.dsms.exception.BusinessException;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 药品库存表 服务类
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
public interface ITDrugInventoryService extends IService<TDrugInventory> {

    boolean outDrugInventory(String drugID, Integer outNum) throws BusinessException;

    boolean inDrugInventory(String drugID, Integer inNum) throws BusinessException;
}
