package com.SLotus.dsms.controller;


import com.SLotus.dsms.domain.ResultDto;
import com.SLotus.dsms.entity.TDrugInfo;
import com.SLotus.dsms.service.ITDrugInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 药品信息表 前端控制器
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/api/t-drug-info")
@Slf4j
@Api(tags = "药品信息表", value = "药品信息表")
public class TDrugInfoController {

    @Autowired
    private ITDrugInfoService tDrugInfoService;

    //查询药品信息
    @GetMapping("/getDrugInfo")
    @ApiOperation(value = "查询药品信息", notes = "查询药品信息")
    public ResultDto<TDrugInfo> getDrugInfo(@RequestParam @ApiParam(name = "药品ID") String drugID) {
        try {
            return new ResultDto<>(tDrugInfoService.getById(drugID));
        } catch (Exception e) {
            log.error("查询药品信息失败:{}", e.getMessage());
            return new ResultDto<>(e, "查询药品信息失败");
        }
    }

    /**
     * 列表展示(全量)
     */
    @GetMapping("/list")
    @ApiOperation(value = "列表展示(全量)", notes = "列表展示(全量)")
    public ResultDto<List<TDrugInfo>> list() {
        try {
            return new ResultDto<>(tDrugInfoService.list());
        } catch (Exception e) {
            log.error("查询药品信息失败:{}", e.getMessage());
            return new ResultDto<>(e, "查询药品信息失败");
        }
    }

    //列表展示(分页)
    @GetMapping("/page")
    @ApiOperation(value = "列表展示(分页)", notes = "列表展示(分页)")
    public ResultDto<List<TDrugInfo>> page(@ApiParam(name = "页数", example = "0") Integer pageNum, @ApiParam(name = "每页数量", example = "0") Integer pageSize) {
        try {
            return new ResultDto<>(tDrugInfoService.page(new Page<>(pageNum, pageSize)).getRecords());
        } catch (Exception e) {
            log.error("查询药品信息失败:{}", e.getMessage());
            return new ResultDto<>(e, "查询药品信息失败");
        }
    }

    //新增药品信息
    @PostMapping("/addDrugInfo")
    @ApiOperation(value = "新增药品信息", notes = "新增药品信息")
    public ResultDto<?> addDrugInfo(@RequestBody TDrugInfo tDrugInfo) {
        //入参校验
        if (tDrugInfo == null) {
            return new ResultDto<>(new IllegalArgumentException("入参不能为空"), "入参不能为空");
        }
        //生成主键
        tDrugInfo.setDrugID("D" + System.currentTimeMillis());

        try {
            if (tDrugInfoService.save(tDrugInfo)) {
                return new ResultDto<>(200, "新增药品信息成功");
            } else {
                return new ResultDto<>(new IllegalArgumentException("新增药品信息失败"), "新增药品信息失败");
            }
        } catch (Exception e) {
            log.error("新增药品信息失败:{}", e.getMessage());
            return new ResultDto<>(e, "新增药品信息失败");
        }
    }

    //修改药品信息
    @PostMapping("/updateDrugInfo")
    public ResultDto<?> updateDrugInfo(@RequestBody TDrugInfo tDrugInfo) {
        //入参校验
        if (tDrugInfo == null) {
            return new ResultDto<>(new IllegalArgumentException("入参不能为空"), "入参不能为空");
        }
        //主键校验
        if (tDrugInfo.getDrugID() == null) {
            return new ResultDto<>(new IllegalArgumentException("主键不能为空"), "主键不能为空");
        }

        try {
            if (tDrugInfoService.updateById(tDrugInfo)) {
                return new ResultDto<>(200, "修改药品信息成功");
            } else {
                return new ResultDto<>(new IllegalArgumentException("修改药品信息失败"), "修改药品信息失败");
            }
        } catch (Exception e) {
            log.error("修改药品信息失败:{}", e.getMessage());
            return new ResultDto<>(e, "修改药品信息失败");
        }
    }

    //删除药品信息
    @DeleteMapping("/deleteDrugInfo")
    @ApiOperation(value = "删除药品信息", notes = "删除药品信息")
    public ResultDto<?> deleteDrugInfo(@RequestParam @ApiParam(name = "药品ID") String drugID) {
        //入参校验
        if (drugID == null) {
            return new ResultDto<>(new IllegalArgumentException("入参不能为空"), "入参不能为空");
        }

        try {
            if (tDrugInfoService.removeById(drugID)) {
                return new ResultDto<>(200, "删除药品信息成功");
            } else {
                return new ResultDto<>(new IllegalArgumentException("删除药品信息失败"), "删除药品信息失败");
            }
        } catch (Exception e) {
            log.error("删除药品信息失败:{}", e.getMessage());
            return new ResultDto<>(e, "删除药品信息失败");
        }
    }


}
