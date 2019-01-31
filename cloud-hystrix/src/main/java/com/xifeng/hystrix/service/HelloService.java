package com.xifeng.hystrix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "helloFallback",commandKey="helloKey")
	public String hello() {
		long start = System.currentTimeMillis();
		String retStr = restTemplate.getForEntity("http://BIZ-SERVICE/hello", String.class).getBody();
		logger.info("cost time:"+(System.currentTimeMillis()-start));
		return retStr;
	}

	public String helloFallback() {
		return "error";
	}
}
