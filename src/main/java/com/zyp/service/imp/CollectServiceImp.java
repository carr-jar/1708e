package com.zyp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.bean.Collect;
import com.zyp.cms.utils.StringUtils;
import com.zyp.mapper.CollectMapper;
import com.zyp.service.CollectService;
@Service
public class CollectServiceImp implements CollectService{
	@Autowired
	CollectMapper mapper;
	@Override
	public List<Collect> clist(int id) {
		// TODO Auto-generated method stub
		return mapper.clist(id);
	}
	@Override
	public Boolean add(Collect collect) {
		// TODO Auto-generated method stub
		String url = collect.getUrl();
		boolean httpUrl = StringUtils.isHttpUrl(url);
		if(httpUrl) {
			mapper.add(collect);
			return true;
		}else {
			System.out.println("url错误");
			return false;
		}
	}
	@Override
	public void del(int id) {
		// TODO Auto-generated method stub
		mapper.del(id);
	}

}
