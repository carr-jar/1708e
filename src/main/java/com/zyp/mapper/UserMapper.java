package com.zyp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zyp.bean.User;

public interface UserMapper {
	@Insert("INSERT INTO cms_user(username,password,locked,create_time,score,role) VALUES(#{username},#{password},0,now(),0,0)")
	void regist(User user);

	@Select("select id,username,password from cms_user where username=#{username}")
	User getUsername(String username);

	@Select("SELECT id,username,password,nickname,birthday,"
			+ "gender,locked,create_time createTime,update_time "
			+ "updateTime,url,role FROM cms_user WHERE username=#{username} "
			+ " AND password = #{password}LIMIT 1")
	User login(User user);

}
