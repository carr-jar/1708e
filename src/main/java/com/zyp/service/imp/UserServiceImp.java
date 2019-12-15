package com.zyp.service.imp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.bean.User;
import com.zyp.common.CmsUtils;
import com.zyp.mapper.UserMapper;
import com.zyp.service.UserService;
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserMapper mapper;

	@Override
	public void regist(User user) {
		// TODO Auto-generated method stub
		String encry = CmsUtils.encry(user.getPassword(), user.getUsername());
		user.setPassword(encry);
		mapper.regist(user);
	}

	@Override
	public User getUsername(String username) {
		// TODO Auto-generated method stub
		return mapper.getUsername(username);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		String encry = CmsUtils.encry(user.getPassword(), user.getUsername());
		user.setPassword(encry);
		
		return mapper.login(user);
	}
}
