package com.SLotus.dsms.controller;


import com.SLotus.dsms.domain.ResultDto;
import com.SLotus.dsms.entity.TDrugInventory;
import com.SLotus.dsms.exception.BusinessException;
import com.SLotus.dsms.service.ITDrugInventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 药品库存表 前端控制器
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/api/t-drug-inventory")
@Slf4j
@Api(tags = "药品库存表", value = "药品库存表")
public class TDrugInventoryController {
    @Autowired
    private ITDrugInventoryService tDrugInventoryService;

    /**
     * 查询库存（全量）
     *
     * @return ResultDto 返回结果
     */
    @GetMapping("/getDrugInventory")
    @ApiOperation(value = "查询库存（全量）", notes = "查询库存（全量）")
    public ResultDto<List<TDrugInventory>> getDrugInventory() {
        try {
            return new ResultDto<>(tDrugInventoryService.list());
        } catch (Exception e) {
            log.error("查询库存失败:{}", e.getMessage());
            return new ResultDto<>(e, "查询库存失败");
        }
    }

    /**
     * 查询库存（单个）
     *
     * @param drugID 药品ID
     * @return ResultDto 返回结果
     */
    @GetMapping("/getDrugInventoryById")
    @ApiOperation(value = "查询库存（单个）", notes = "查询库存（单个）")
    public ResultDto<TDrugInventory> getDrugInventoryById(@RequestParam @ApiParam(name = "药品ID") String drugID) {
        try {
            return new ResultDto<>(tDrugInventoryService.getById(drugID));
        } catch (Exception e) {
            log.error("查询库存失败:{}", e.getMessage());
            return new ResultDto<>(e, "查询库存失败");
        }
    }

    /**
     * 出库
     *
     * @param drugID 药品ID
     * @param outNum 出库数量
     * @return ResultDto 返回结果
     */
    @PostMapping("/outDrugInventory")
    @ApiOperation(value = "出库", notes = "出库")
    public ResultDto<?> outDrugInventory(@RequestParam @ApiParam(name = "药品ID") String drugID,
                                         @RequestParam @ApiParam(name = "出库数量", example = "0") Integer outNum) {
        try {
            boolean outDrugInventory = tDrugInventoryService.outDrugInventory(drugID, outNum);
            if (outDrugInventory) {
                return new ResultDto<>(200, "出库成功");
            } else {
                return new ResultDto<>(500, "出库失败");
            }
        } catch (BusinessException e) {
            log.error("出库失败:{}", e.getMessage());
            return new ResultDto<>(e, "出库失败");
        } catch (Exception e) {
            log.error("意外的失败，原因为:{}", e.getMessage());
            return new ResultDto<>(e, "意外的失败");
        }
    }

    /**
     * 入库
     *
     * @param drugID 药品ID
     * @param inNum  入库数量
     * @return ResultDto 返回结果
     */
    @PostMapping("/inDrugInventory")
    @ApiOperation(value = "入库", notes = "入库")
    public ResultDto<?> inDrugInventory(@RequestParam @ApiParam(name = "药品ID") String drugID,
                                        @RequestParam @ApiParam(name = "入库数量", example = "0") Integer inNum) {
        try {
            boolean inDrugInventory = tDrugInventoryService.inDrugInventory(drugID, inNum);
            if (inDrugInventory) {
                return new ResultDto<>(200, "入库成功");
            } else {
                return new ResultDto<>(500, "入库失败");
            }
        } catch (BusinessException e) {
            log.error("入库失败:{}", e.getMessage());
            return new ResultDto<>(e, "入库失败");
        } catch (Exception e) {
            log.error("意外的失败，原因为:{}", e.getMessage());
            return new ResultDto<>(e, "意外的失败");
        }
    }


}
