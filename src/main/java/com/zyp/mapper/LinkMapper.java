package com.zyp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zyp.bean.Link;

public interface LinkMapper {
	@Select("select * from cms_link")
	List<Link> lList();

}
