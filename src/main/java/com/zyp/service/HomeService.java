package com.zyp.service;

import java.util.List;

import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;
import com.zyp.bean.Slide;

public interface HomeService {

	List<Channel> getChannels();

	List<Slide> getSlide();

	List<Article> hotList();

	List<Article> lastList();

	List<Article> getArticles(int channelId, int catId);

	List<Category> getCategoriesByChannelId(int channelId);

	Article detail(int id);
	
}
