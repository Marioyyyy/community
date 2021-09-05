package com.lxy.community;

import com.lxy.community.dto.ViewCountDTO;
import com.lxy.community.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.HashMap;
import java.util.Set;



@SpringBootTest
class CommunityApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	RedisUtil redisUtil;

	@Test
	void contextLoads() {

		String s = "100.ViewCount";
		Object o = redisUtil.get(s);
		System.out.println(o);
		Long number;
		System.out.println(redisUtil.get("4.viewCount").toString());
		number = Long.valueOf(redisUtil.get("4.viewCount").toString());
		System.out.println(number);

	}
}
