package org.msy.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 11612
 * @date 2023/3/25
 */
@SpringBootApplication
@ComponentScan("org.msy")
public class ServiceTestApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTestApp.class, args);
    }

}
