package com.xifeng.cloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("biz-service")
public interface HelloService {
	@RequestMapping("/hello")
	String hello();
}
