package com.zyp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;
import com.zyp.bean.Slide;

public interface HomeMapper {
	@Select("select id,name from cms_channel")
	List<Channel> getChannels();

	@Select("select * from cms_slide")
	List<Slide> getSlide();
	
	List<Article> hostList();

	@Select("SELECT * FROM cms_article WHERE deleted=0 ORDER BY created DESC")
	List<Article> lastList();

	
	List<Article> getArticles(@Param("channelId")int channelId, @Param("catId")int catId);

	List<Category> getCategoriesByChannelId(int channelId);

	List<Article> getArt(int channelId);

	Article detail(int id);

	List<Article> cntList();

	@Update("update cms_article set hits=hits+1 where id=#{id}")
	void addHit(int id);

}
