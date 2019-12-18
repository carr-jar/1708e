package com.zyp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.bean.Article;
import com.zyp.mapper.AdminMapper;
import com.zyp.service.AdminService;
@Service
public class AdminServiceImp implements AdminService{
	@Autowired
	private AdminMapper mapper;

	@Override
	public List<Article> list(int status) {
		// TODO Auto-generated method stub
		return mapper.list(status);
	}

	@Override
	public Article getById(int id) {
		// TODO Auto-generated method stub
		return mapper.getById(id);
	}

	@Override
	public Article getInfoById(int id) {
		// TODO Auto-generated method stub
		return mapper.getInfoById(id);
	}

	@Override
	public int setCheckStatus(int id, int status) {
		// TODO Auto-generated method stub
		return mapper.setCheckStatus(id,status);
	}

	@Override
	public int setCheckHot(int id, int hot) {
		// TODO Auto-generated method stub
		return mapper.setCheckHot(id,hot);
	}
}
