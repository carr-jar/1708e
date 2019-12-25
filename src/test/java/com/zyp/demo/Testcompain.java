package com.zyp.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.zyp.bean.Compain;
import com.zyp.bean.User;
import com.zyp.cms.utils.StringUtils;
import com.zyp.service.ArticleService;

@ContextConfiguration("classpath:spring-beans.xml")
@RunWith(SpringRunner.class)
public class Testcompain {
	@Autowired
	private ArticleService service;
	@Test
	public void testCompain() {
		String Urlip="https://www.toutiao.com/ch/news_tech/";
		Compain compain = new Compain();
		compain.setArticle_id(28);
		compain.setUser_id(68);
		compain.setComplaintype("A");
		if(StringUtils.isHttpUrl(Urlip)) {
			compain.setUrlip(Urlip);
		}
		int i = service.addcompain(compain);
	}
}
