package com.SLotus.dsms.service.impl;

import com.SLotus.dsms.entity.TDrugFlow;
import com.SLotus.dsms.entity.TDrugInventory;
import com.SLotus.dsms.exception.BusinessException;
import com.SLotus.dsms.mapper.TDrugFlowMapper;
import com.SLotus.dsms.mapper.TDrugInventoryMapper;
import com.SLotus.dsms.service.ITDrugInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 药品库存表 服务实现类
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@Service
public class TDrugInventoryServiceImpl extends ServiceImpl<TDrugInventoryMapper, TDrugInventory> implements ITDrugInventoryService {

    @Autowired
    private TDrugFlowMapper tDrugFlowMapper;

    /**
     * 出库
     *
     * @param drugID 药品ID
     * @param outNum 出库数量
     * @return boolean 是否成功 true 成功 false 失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public boolean outDrugInventory(String drugID, Integer outNum) throws BusinessException {
        //入参校验
        if (drugID == null || drugID.isEmpty() || outNum == null || outNum <= 0) {
            throw new BusinessException("入参错误");
        }
        //查询库存
        TDrugInventory tDrugInventory = baseMapper.selectByIdForUpdate(drugID);
        if (tDrugInventory == null) {
            throw new BusinessException("库存不存在");
        }
        //判断库存是否充足
        if (tDrugInventory.getAmount() < outNum) {
            throw new BusinessException("库存不足");
        }
        //更新库存
        tDrugInventory.setAmount(tDrugInventory.getAmount() - outNum);
        //写入流水
        TDrugFlow tDrugFlow = new TDrugFlow();
        tDrugFlow.setDrugId(drugID);
        tDrugFlow.setAmount(Long.valueOf(outNum));
        tDrugFlow.setLocationCode(tDrugInventory.getLocationCode());
        tDrugFlow.setOperate("出库");
        tDrugFlow.setSerialNo("S" + (int) (Math.random() * 1000000));
        tDrugFlow.setPrescriptionNo("P" + (int) (Math.random() * 1000000));
        tDrugFlowMapper.insert(tDrugFlow);
        return true;
    }

    /**
     * 入库
     *
     * @param drugID 药品ID
     * @param inNum  入库数量
     * @return boolean 是否成功 true 成功 false 失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public boolean inDrugInventory(String drugID, Integer inNum) throws BusinessException {
        //入参校验
        if (drugID == null || drugID.isEmpty() || inNum == null || inNum <= 0) {
            throw new BusinessException("入参错误");
        }
        //查询库存
        TDrugInventory tDrugInventory = baseMapper.selectByIdForUpdate(drugID);
        //如果不存在则新建
        if (tDrugInventory == null) {
            tDrugInventory = new TDrugInventory();
            tDrugInventory.setDrugID(drugID);
            tDrugInventory.setAmount(0L);
            //TODO：库存位置暂时采取随机生成的方式
            tDrugInventory.setLocationCode("L" + (int) (Math.random() * 1000000));
        }
        //更新库存
        tDrugInventory.setAmount(tDrugInventory.getAmount() + inNum);
        baseMapper.updateById(tDrugInventory);
        //写入流水
        TDrugFlow tDrugFlow = new TDrugFlow();
        tDrugFlow.setDrugId(drugID);
        tDrugFlow.setAmount(Long.valueOf(inNum));
        tDrugFlow.setLocationCode(tDrugInventory.getLocationCode());
        tDrugFlow.setOperate("入库");
        tDrugFlow.setSerialNo("S" + (int) (Math.random() * 1000000));
        tDrugFlow.setPrescriptionNo("P" + (int) (Math.random() * 1000000));
        tDrugFlowMapper.insert(tDrugFlow);
        return true;
    }
}
