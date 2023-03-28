package org.msy.hospital.dict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 11612
 * @date 2023/3/23
 */
@SpringBootApplication
@ComponentScan("org.msy")
@EnableDiscoveryClient
public class ServiceDictMainApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDictMainApp.class,args);
    }
}
