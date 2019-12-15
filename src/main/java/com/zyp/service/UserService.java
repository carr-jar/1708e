package com.zyp.service;



import com.zyp.bean.User;

public interface UserService {

	void regist(User user);

	User getUsername(String username);

	User login(User user);
	
}
