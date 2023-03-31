package org.msy.hospital.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 11612
 * @date 2023/3/31
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.msy")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.msy")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class,args);
    }
}
