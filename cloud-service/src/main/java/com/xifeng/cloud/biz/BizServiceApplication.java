package com.xifeng.cloud.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.xifeng.cloud.biz")
@SpringBootApplication
@EnableDiscoveryClient
public class BizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizServiceApplication.class, args);
	}

}
