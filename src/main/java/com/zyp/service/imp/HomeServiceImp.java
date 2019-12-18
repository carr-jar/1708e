package com.zyp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.bean.Article;
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
	public List<Article> list(int id) {
		// TODO Auto-generated method stub
		if(id==-1) {
			return mapper.list2();
		}
		return mapper.list(id);
	}
}
