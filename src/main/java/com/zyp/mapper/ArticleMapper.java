package com.zyp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;

public interface ArticleMapper {

	List<Article> list(int id);

	@Update("update cms_article set deleted=1 where id=#{id}")
	void del(int id);

	@Select("select id,name from cms_channel")
	List<Channel> getChannels();

	@Select("select id,name from cms_category where channel_id=#{id}")
	List<Category> getCategoris(int id);

	@Insert("INSERT INTO cms_article(title,content,picture,channel_id,category_id,user_id,hits,hot,status,deleted,created,updated,commentCnt,articleType)"
			+ " VALUES(#{title},#{content},#{picture},#{channel_id},#{category_id},#{user_id},0,0,0,0,now(),now(),0,#{articleType})")
	int add(Article article);

	Article toUpdateArticle(int id);

	@Update("UPDATE cms_article SET title=#{title},content=#{content},picture=#{picture},channel_id=#{channel_id},"
			+ "category_id=#{category_id},status=0,"
			+ "updated=now() WHERE id=#{id} ")
	int update(Article article);
	
}
