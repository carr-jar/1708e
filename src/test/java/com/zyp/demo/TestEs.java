package com.zyp.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zyp.bean.Article;
import com.zyp.mapper.ArticleRep;
import com.zyp.service.HomeService;

@ContextConfiguration("classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEs {
	@Autowired
	HomeService service;
	@Autowired
	ArticleRep articleRep;
	@Test
	public void testEs() {
		List<Article> hotList = service.hotList();
		articleRep.saveAll(hotList);
	}
}
