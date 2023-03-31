package org.msy.hospital.msm.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 11612
 * @date 2023/3/31
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    // 从yml中读取配置的regionId
    @Value("${aliyun.sms.regionId}")
    private String regionId;
    // 从yml中读取配置的accessKeyId
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    // 从yml中读取配置的secret
    @Value("${aliyun.sms.secret}")
    private String secret;
    public static String RESION_ID;
    public static String ACCESS_KEY_ID;
    public static String SECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        RESION_ID = regionId;
        ACCESS_KEY_ID = accessKeyId;
        SECRET = secret;
    }
}
