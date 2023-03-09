package com.SLotus.dsms.service.impl;


import com.SLotus.dsms.entity.TUserInfo;
import com.SLotus.dsms.mapper.TUserInfoMapper;
import com.SLotus.dsms.service.ITUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author SLotus
 * @since 2023-03-08
 */
@Service
public class TUserInfoServiceImpl extends ServiceImpl<TUserInfoMapper, TUserInfo> implements ITUserInfoService {

    /**
     * 验证Token
     *
     * @param userID 用户ID
     * @param token  Token
     * @return 是否匹配
     * @author SLotus
     * @date 2023-03-08
     */
    @Override
    public boolean verifyToken(String userID, String token) {
        TUserInfo tUserInfo = baseMapper.selectById(userID);
        //如果用户不存在，返回false
        if (tUserInfo == null) {
            return false;
        }
        //如果Token不匹配，返回false
        return tUserInfo.getUserToken().equals(token);
    }
}
