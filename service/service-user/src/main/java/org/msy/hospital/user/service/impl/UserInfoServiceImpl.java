package org.msy.hospital.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.msy.hospital.common.exception.YyghException;
import org.msy.hospital.common.helper.JwtHelper;
import org.msy.hospital.common.result.ResultCodeEnum;
import org.msy.hospital.model.user.UserInfo;
import org.msy.hospital.user.mapper.UserInfoMapper;
import org.msy.hospital.user.service.UserInfoService;
import org.msy.hospital.vo.user.LoginVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/31
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Map<String, Object> login(LoginVo loginVo) {
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();
// 参数校验
        if(!StringUtils.hasLength(phone) || !StringUtils.hasLength(code)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        // 校验验证码
        String mobleCode = redisTemplate.opsForValue().get(phone);
        if(!code.equals(mobleCode)) {
            throw new YyghException(ResultCodeEnum.CODE_ERROR);
        }
//todo 校验手机 验证码
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        UserInfo userInfo = baseMapper.selectOne(wrapper);
        if(null == userInfo) {
// 如果没有注册，则根据手机号注册
            userInfo = new UserInfo();
            userInfo.setName("");
            userInfo.setPhone(phone);
            userInfo.setStatus(1);
            this.save(userInfo);
        }
// 校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new YyghException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }
//todo 记录登录
        Map<String,Object> map = new HashMap<>();
        String name = userInfo.getName();
        if(!StringUtils.hasLength(name)) {
            name = userInfo.getNickName();
        }
        if(!StringUtils.hasLength(name)) {
            name = userInfo.getPhone();
        }
        String token = JwtHelper.createToken(userInfo.getId(), name);
        map.put("name",name);
        map.put("token",token);
        return map;
    }
}
