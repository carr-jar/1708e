package com.zyp.service;

import java.util.List;

import com.zyp.bean.Article;

public interface RedisArticleService {
	void saveArticle(Article article);
}
