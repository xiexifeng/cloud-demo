package com.xifeng.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xifeng.hystrix.service.HelloService;

@RestController
public class HelloController {
		
	@Autowired
	HelloService helloService;

	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
		return helloService.hello();
	}
}
