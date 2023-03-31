package org.msy.hospital.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.msy.hospital.model.user.UserInfo;
import org.msy.hospital.vo.user.LoginVo;

import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/31
 */
public interface UserInfoService extends IService<UserInfo> {
    Map<String,Object> login(LoginVo loginVo) ;
}
