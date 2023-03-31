package org.msy.hospital.user.controller;

import io.swagger.annotations.ApiOperation;
import org.msy.hospital.common.result.Result;
import org.msy.hospital.common.utils.IpUtil;
import org.msy.hospital.user.service.UserInfoService;
import org.msy.hospital.vo.user.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/31
 */
@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {
    @Autowired
    private UserInfoService userInfoServiceImpl;
    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo , HttpServletRequest
            request) {
        loginVo.setIp(IpUtil.getIpAddr(request));
        Map<String, Object> map = userInfoServiceImpl.login(loginVo);
        return Result.ok(map);
    }
}
