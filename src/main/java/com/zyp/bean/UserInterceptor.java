package com.zyp.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.zyp.common.CmsContant;

public class UserInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		User loginUser = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		if(loginUser == null)
		{
			response.sendRedirect("/user/tologin.do");
			return false;
		}
		
		//放行 
		return true;
	}
}
