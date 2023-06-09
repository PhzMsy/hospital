package org.msy.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ${USER}
 * @date ${DATE}
 */
@SpringBootApplication
@ComponentScan("org.msy")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.msy")
public class ServiceHospitalMainApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospitalMainApp.class, args);
    }
}
