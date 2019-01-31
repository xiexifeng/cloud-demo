package com.xifeng.cloud.biz.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xifeng.cloud.biz.service.HelloService;

@RestController
public class HelloController {
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DiscoveryClient client;
	@Autowired
	HelloService helloService;
	@RequestMapping("/hello")
	public String index() {
		List<String> list = client.getServices();
		List<ServiceInstance> serviceList = client.getInstances("hello-client");
	    for(String str:list) {
	    	logger.info(str);
	    }
	    for(ServiceInstance instance:serviceList) {
	    	logger.info(instance.getUri().toString());
	    }
	    int sleepTime = new Random().nextInt(3000);
	    try {
			Thread.sleep(sleepTime);
			logger.info("sleepTime="+sleepTime);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
		return helloService.sayHello();
	}
}
