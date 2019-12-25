package com.zyp.service;

import java.util.List;

import javax.validation.Valid;

import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;
import com.zyp.bean.Compain;

public interface ArticleService {

	List<Article> list(int id);

	void del(int id);

	List<Channel> getChannels();

	List<Category> getCategoris(int id);

	int add(Article article);

	Article toUpdateArticle(int id);

	int update(Article article);

	int addcompain(Compain compain);

	List<Compain> compainList(String complaintype, int cnt1, int cnt2, String order);

	Compain getcompain(int id);

	

}
