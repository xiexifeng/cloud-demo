package com.xifeng.cloud.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xifeng.cloud.feign.service.HelloService;

@RestController
public class HelloController {
	@Autowired
	HelloService helloService;

	@RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
		return helloService.hello();
	}
}
