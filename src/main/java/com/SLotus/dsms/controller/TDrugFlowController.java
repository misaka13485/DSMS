package com.SLotus.dsms.controller;


import com.SLotus.dsms.domain.ResultDto;
import com.SLotus.dsms.entity.TDrugFlow;
import com.SLotus.dsms.service.ITDrugFlowService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 药品出入库流水表 前端控制器
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/api/t-drug-flow")
@Api(tags = "药品出入库流水表", value = "药品出入库流水表")
@Slf4j
public class TDrugFlowController {

    @Autowired
    private ITDrugFlowService tDrugFlowService;

    /**
     * 查看流水(分页)
     */
    @GetMapping("/getDrugFlow")
    @ApiOperation(value = "查看流水(分页)", notes = "查看流水(分页)")
    public ResultDto<Page<TDrugFlow>> getDrugFlow(@RequestParam @ApiParam(name = "页码", example = "0") Integer pageNum,
                                                  @RequestParam @ApiParam(name = "每页数量", example = "0") Integer pageSize) {
        try {
            return new ResultDto<>(tDrugFlowService.page(new Page<>(pageNum, pageSize)));
        } catch (Exception e) {
            log.error("查询流水失败:{}", e.getMessage());
            return new ResultDto<>(e, "查询流水失败");
        }
    }

    /**
     * 导出流水（全量）
     */
    @GetMapping("/exportDrugFlow")
    @ApiOperation(value = "导出流水（全量）", notes = "导出流水（全量）")
    public ResultDto<List<TDrugFlow>> exportDrugFlow() {
        try {
            return new ResultDto<>(tDrugFlowService.list());
        } catch (Exception e) {
            log.error("导出流水失败:{}", e.getMessage());
            return new ResultDto<>(e, "导出流水失败");
        }
    }


}
