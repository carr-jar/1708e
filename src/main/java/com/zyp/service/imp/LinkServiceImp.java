package com.zyp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.bean.Link;
import com.zyp.mapper.LinkMapper;
import com.zyp.service.LinkService;
@Service
public class LinkServiceImp implements LinkService{
	@Autowired
	private LinkMapper mapper;

	@Override
	public List<Link> lList() {
		// TODO Auto-generated method stub
		return mapper.lList();
	}
}
