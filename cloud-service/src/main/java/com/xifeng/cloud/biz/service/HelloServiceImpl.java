package com.xifeng.cloud.biz.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello() {
		return "hello world!";
	}

}
