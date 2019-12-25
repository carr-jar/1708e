package com.zyp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;
import com.zyp.bean.Slide;
import com.zyp.mapper.HomeMapper;
import com.zyp.service.HomeService;
@Service
public class HomeServiceImp implements HomeService{
	@Autowired
	private HomeMapper mapper;

	@Override
	public List<Channel> getChannels() {
		// TODO Auto-generated method stub
		return mapper.getChannels();
	}

	@Override
	public List<Slide> getSlide() {
		// TODO Auto-generated method stub
		return mapper.getSlide();
	}

	@Override
	public List<Article> hotList() {
		// TODO Auto-generated method stub
		return mapper.hostList();
	}

	@Override
	public List<Article> lastList() {
		// TODO Auto-generated method stub
		return mapper.lastList();
	}

	@Override
	public List<Article> getArticles(int channelId, int catId) {
		// TODO Auto-generated method stub
		if(catId==0) {
			return mapper.getArt(channelId);
		}
		return mapper.getArticles(channelId,catId);
	}

	@Override
	public List<Category> getCategoriesByChannelId(int channelId) {
		// TODO Auto-generated method stub
		return mapper.getCategoriesByChannelId(channelId);
	}

	@Override
	public Article detail(int id) {
		// TODO Auto-generated method stub
		return mapper.detail(id);
	}

	@Override
	public List<Article> cntList() {
		// TODO Auto-generated method stub
		return mapper.cntList();
	}
}
