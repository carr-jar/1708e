package com.zyp.service;

import java.util.List;

import com.zyp.bean.Article;
import com.zyp.bean.Channel;
import com.zyp.bean.Slide;

public interface HomeService {

	List<Channel> getChannels();

	List<Slide> getSlide();

	List<Article> list(int id);
	
}
