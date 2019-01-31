package com.xifeng.cloud.ribbon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/hello")
	public String index() {
		long start = System.currentTimeMillis();
		String retStr = restTemplate.getForEntity("http://BIZ-SERVICE/hello", String.class).getBody();
		logger.info("cost time:"+(System.currentTimeMillis()-start));
		return retStr;
	}
}
