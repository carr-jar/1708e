package com.zyp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zyp.bean.Collect;

public interface CollectMapper {
	@Select("select * from cms_collect where user_id=#{id} ORDER BY created DESC")
	List<Collect> clist(int id);

	@Insert("insert into cms_collect values(null,#{user_id},#{url},#{name},now())")
	void add(Collect collect);

	@Delete("delete from cms_collect where id=#{id}")
	void del(int id);
	
}
