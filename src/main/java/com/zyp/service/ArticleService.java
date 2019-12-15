package com.zyp.service;

import java.util.List;

import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;

public interface ArticleService {

	List<Article> list(int id);

	void del(int id);

	List<Channel> getChannels();

	List<Category> getCategoris(int id);

	int add(Article article);

	Article toUpdateArticle(int id);

	int update(Article article);

	

}
