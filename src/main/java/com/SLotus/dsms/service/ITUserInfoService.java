package com.SLotus.dsms.service;

import com.SLotus.dsms.entity.TUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
public interface ITUserInfoService extends IService<TUserInfo> {

    //验证Token
    boolean verifyToken(String userID, String token);

}
