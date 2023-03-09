package com.SLotus.dsms.controller;


import com.SLotus.dsms.domain.ResultDto;
import com.SLotus.dsms.entity.TUserInfo;
import com.SLotus.dsms.service.ITUserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/t-user-info")
@Api(tags = "用户相关接口")
public class TUserInfoController {
    @Autowired
    private ITUserInfoService tUserInfoService;

    //登录
    @GetMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public ResultDto<String> login(@RequestParam @ApiParam("用户ID") String username,
                                   @RequestParam @ApiParam("密码") String password) {
        QueryWrapper<TUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_ID", username);
        queryWrapper.eq("USER_PASSWORD", password);
        TUserInfo tUserInfo = tUserInfoService.getOne(queryWrapper);
        if (tUserInfo == null) {
            return new ResultDto<String>(403, "用户名或密码错误");
        }
        //生成Token
        String token = username + LocalDateTime.now();
        TUserInfo tUserInfo2 = new TUserInfo();
        tUserInfo2.setUserID(username);
        tUserInfo2.setUserToken(token);
        tUserInfoService.updateById(tUserInfo2);
        return new ResultDto<String>(token);
    }

    //注册
    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "注册")
    public ResultDto<String> register(@RequestBody @ApiParam("用户信息") TUserInfo tUserInfo) {
        //清除敏感信息
        tUserInfo.setIsEnable("1");
        tUserInfo.setCreatedTime(LocalDateTime.now());
        tUserInfo.setUpdatedTime(LocalDateTime.now());
        QueryWrapper<TUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_ID", tUserInfo.getUserID());
        TUserInfo tUserInfo1 = tUserInfoService.getOne(queryWrapper);
        //判断用户名是否存在
        if (tUserInfo1 != null) {
            return new ResultDto<String>(403, "用户名已存在");
        }
        //保存Token
        tUserInfoService.save(tUserInfo);
        return new ResultDto<String>("注册成功");
    }

    //修改密码
    @PutMapping("/updatePassword")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public ResultDto<String> updatePassword(@RequestParam @ApiParam("用户ID") String username,
                                            @RequestParam @ApiParam("旧密码") String oldPassword,
                                            @RequestParam @ApiParam("新密码") String newPassword) {
        QueryWrapper<TUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_ID", username);
        queryWrapper.eq("USER_PASSWORD", oldPassword);
        TUserInfo tUserInfo = tUserInfoService.getOne(queryWrapper);
        if (tUserInfo == null) {
            return new ResultDto<String>(403, "用户名或密码错误");
        }
        tUserInfo.setUserPassword(newPassword);
        tUserInfoService.updateById(tUserInfo);
        return new ResultDto<String>("修改成功");
    }

    //修改用户信息
    @PutMapping("/updateUserInfo")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public ResultDto<String> updateUserInfo(@RequestBody @ApiParam("用户信息") TUserInfo tUserInfo) {
        tUserInfoService.updateById(tUserInfo);
        return new ResultDto<String>("修改成功");
    }
}
