package com.zyp.es;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zyp.bean.Article;
import com.zyp.mapper.ArticleRep;
import com.zyp.service.HomeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ImportMysqlToEs {
	@Autowired
	HomeService homeService;
	@Autowired
	ArticleRep articleRep;
	@Test
	public void importMysqlToEs() {
		List<Article> hotList = homeService.hotList();
		articleRep.saveAll(hotList);
	}
	@Test
	public void select() {
		List<Article> findByTitle = articleRep.findByTitle("测试集合");
		for (Article article : findByTitle) {
			System.out.println(article);
		}
	}
	@Test
	public void del() {
		articleRep.deleteById(41);
	}
	@Test
	public void update() {
		
	}
}
