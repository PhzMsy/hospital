package org.msy.hospital.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 11612
 * @date 2023/3/29
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("org.msy")
@EnableDiscoveryClient
public class ServerGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerGatewayApplication.class, args);
    }
}
