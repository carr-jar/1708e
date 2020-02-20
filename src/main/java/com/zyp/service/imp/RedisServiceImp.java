package com.zyp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import com.zyp.bean.Article;
import com.zyp.service.RedisArticleService;
@Service
public class RedisServiceImp implements RedisArticleService{
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	@Override
	public void saveArticle(Article article) {
//		// TODO Auto-generated method stub
		String key="article1";
		redisTemplate.opsForValue().set(key, article);
		kafkaTemplate.send("test2", "redis=="+key);
	}

	

}
