package org.msy.hospital.user.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 11612
 * @date 2023/3/31
 */
@Configuration
@MapperScan("org.msy.hospital.user.mapper")
public class UserConfig {
}
