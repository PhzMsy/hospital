package org.msy.hospital.msm.service;

/**
 * @author 11612
 * @date 2023/3/31
 */
public interface MsmService {
    // 发送手机验证码
    boolean send(String phone, String code);
}
