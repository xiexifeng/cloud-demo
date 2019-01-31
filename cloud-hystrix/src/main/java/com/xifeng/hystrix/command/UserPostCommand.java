package com.xifeng.hystrix.command;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.xifeng.hystrix.model.User;

public class UserPostCommand extends HystrixCommand<User>{
	private RestTemplate restTemplate;
	private User user;
	public UserPostCommand(RestTemplate restTemplate,User user) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet")));
		this.restTemplate = restTemplate;
		this.user = user;
	}
	@Override
	protected User run() throws Exception {
		User r=restTemplate.postForObject("http://HELLO-CLIENT/users",user,User.class);
		UserGetCommand.flushCache(r.getId());
		return r;
	}
}
