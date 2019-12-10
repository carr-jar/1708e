package com.zyp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.mapper.UserMapper;
import com.zyp.service.UserService;
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserMapper mapper;
}
