package com.zyp.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.mapper.ArticleRep;
import com.zyp.service.ArticleService;

public class MsgListener implements MessageListener<String, String>{
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	ArticleService service;
	@Autowired
	ArticleRep articleRep;
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		// TODO Auto-generated method stub
		String value = data.value();
		if(value.startsWith("article")) {
			List<Integer> list = new ArrayList<Integer>();
			int max=0;
			int min=999;
			String[] split = value.split("==");
			String article2 = split[1];
			Article article = JSON.parseObject(article2, Article.class);
			List<Category> categoris = service.getCategoris(article.getChannel_id());
			for(Category category : categoris){
				Integer id = category.getId();
				list.add(id);
			}
			for(int i : list) {
				if(i>max) {
					max=i;
				}
				if(i<min) {
					min=i;
				}
			}
			int Category_id=(int) (Math.random()*(max-min)+min);
			article.setCategory_id(Category_id);
			service.add(article);
		}else if(value.startsWith("save")) {
			int indexOf = value.indexOf("*");
			String substring = value.substring(indexOf+1);
			Article article = JSON.parseObject(substring, Article.class);
			articleRep.save(article);
			System.err.println("添加成功");
		}else if(value.startsWith("del")) {
			int indexOf = value.indexOf("*");
			String substring = value.substring(indexOf+1);
			Article article = JSON.parseObject(substring, Article.class);
			articleRep.delete(article);
		}else if(value.startsWith("look")) {
			String[] split = value.split("==");
			service.addlook(split[1]);
		}else if(value.startsWith("redis")){
			List<Integer> list = new ArrayList<Integer>();
			int max=0;
			int min=999;
			String[] split = value.split("==");
			String article2 = split[1];
			Article article = JSON.parseObject(article2, Article.class);
			List<Category> categoris = service.getCategoris(article.getChannel_id());
			for(Category category : categoris){
				Integer id = category.getId();
				list.add(id);
			}
			for(int i : list) {
				if(i>max) {
					max=i;
				}
				if(i<min) {
					min=i;
				}
			}
			int Category_id=(int) (Math.random()*(max-min)+min);
			article.setCategory_id(Category_id);
			service.add(article);
		}
	}

}
