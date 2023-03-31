package org.msy.hospital.msm.controller;

import org.msy.hospital.common.result.Result;
import org.msy.hospital.common.utils.RandomUtil;
import org.msy.hospital.msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author 11612
 * @date 2023/3/31
 */
@RestController
@RequestMapping("/api/msm")
public class MsmController {
    @Autowired
    private MsmService msmServiceImpl;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    // 发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
// 从redis获取验证码，如果获取到，返回ok
// key 手机号， value 验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(StringUtils.hasLength(code)) {
            return Result.ok();
        }
        // 如果获取不到，生成验证码
        code = RandomUtil.getSixBitRandom();
        // 调用service方法，通过整合短信服务进行发送
        boolean isSend = msmServiceImpl.send(phone,code);
        // 生成验证码放到redis里面，设置有效时间
        if(isSend) {
            redisTemplate.opsForValue().set(phone,code,3, TimeUnit.MINUTES);
            System.out.println(code);
            return Result.ok();
        } else {
            return Result.fail().message("发送短信失败");
        }
    }

}
