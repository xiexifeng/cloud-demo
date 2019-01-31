package com.xifeng.hystrix.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.xifeng.hystrix.model.User;

public class UserGetCommand extends HystrixCommand<User> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");
	private RestTemplate restTemplate;
	private Long id;

	protected UserGetCommand(RestTemplate restTemplate, Long id) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet")).andCommandKey(GETTER_KEY));
		this.restTemplate = restTemplate;
		this.id = id;
	}

	@Override
	protected User run() throws Exception {
		long start = System.currentTimeMillis();
		User ret = restTemplate.getForEntity("http://HELLO-CLIENT/users/{1}", User.class, id).getBody();
		logger.info("cost time:" + (System.currentTimeMillis() - start));
		return ret;
	}

	@Override
	protected String getCacheKey() {
		return String.valueOf(id);
	}

	public static void flushCache(Long id) {
		HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance())
				.clear(String.valueOf(id));
	}

}
