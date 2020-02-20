package com.zyp.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.cms.utils.FileUtilIO;
import com.zyp.service.ArticleService;
import com.zyp.service.RedisArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestSendArticle {
	@Autowired
	KafkaTemplate kafkaTemplate;
	@Autowired
	RedisArticleService articleService;
	@Test
	public void testSend() throws IOException {
		File file = new File("D:\\month1");
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			int commentCnt=(int) (Math.random()*(10-1)+1);
			int channel_id=(int) (Math.random()*(8-1)+1);
			String title = file2.getName().replace(".txt", "");
			String content = FileUtilIO.readFile(file2, "UTF-8");
			Article article = new Article();
			article.setTitle(title);
			article.setContent(content);
			article.setPicture("D:/pic");
			article.setChannel_id(channel_id);
			article.setUser_id(68);
			article.setCommentCnt(commentCnt);
			article.setArticleType(0);
			articleService.saveArticle(article);
		}
	}
}
