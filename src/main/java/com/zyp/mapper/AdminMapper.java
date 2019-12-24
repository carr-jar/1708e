package com.zyp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zyp.bean.Article;

public interface AdminMapper {
	
	List<Article> list(@Param("status")int status);

	Article getById(int id);

	@Select("SELECT id,title,channel_id,category_id,status ,hot "
			+ " FROM cms_article WHERE id = #{value} ")
	Article getInfoById(int id);

	@Update("UPDATE cms_article SET status=#{status} WHERE id=#{id}")
	int setCheckStatus(@Param("id")int id, @Param("status")int status);

	@Update("UPDATE cms_article SET hot=#{hot} WHERE id=#{id}")
	int setCheckHot(@Param("id")int id, @Param("hot")int hot);
	
}
