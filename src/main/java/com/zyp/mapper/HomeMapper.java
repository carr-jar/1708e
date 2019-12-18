package com.zyp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zyp.bean.Article;
import com.zyp.bean.Channel;
import com.zyp.bean.Slide;

public interface HomeMapper {
	@Select("select id,name from cms_channel")
	List<Channel> getChannels();

	@Select("select * from cms_slide")
	List<Slide> getSlide();
	
	List<Article> list(int id);
	
	List<Article> list2();
}
