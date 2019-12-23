package com.zyp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyp.bean.User;
import com.zyp.cms.utils.StringUtils;
import com.zyp.common.CmsContant;
import com.zyp.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService service;
	@RequestMapping("home.do")
	public String home() {
		return "user/home";
	}
	@RequestMapping("toregist.do")
	public String toregist(Model m) {
		User user = new User();
		m.addAttribute("user", user);
		return "user/regist";
	}
	@RequestMapping("regist.do")
	public String regist(@Valid @ModelAttribute("user")User user,BindingResult result) {
		System.out.println(user);
		if(result.hasErrors()) {
			return "user/regist";
		}
		if(StringUtils.isNumber(user.getPassword())) {
			result.rejectValue("password", "", "不能全为数字");
			return "user/regist";
		}
		service.regist(user);
		return "redirect:tologin.do";
	}
	@RequestMapping("checkname.do")
	@ResponseBody
	public boolean checkUserName(String username) {
		User user=service.getUsername(username);
		return user==null;
	}
	@RequestMapping("tologin.do")
	public String tologin(Model m) {
		
		return "user/login";
	}
	@RequestMapping("login.do")
	public String login(User user,Model m,HttpServletRequest request,HttpServletResponse response) {
		
		String pwd =  new String(user.getPassword());
		//保存用户的用户名和密码
		Cookie cookieUserName = new Cookie("username", user.getUsername());
		cookieUserName.setPath("/");
		cookieUserName.setMaxAge(10*24*3600);
		response.addCookie(cookieUserName);
		Cookie cookieUserPwd = new Cookie("userpwd", pwd);
		cookieUserPwd.setPath("/");
		cookieUserPwd.setMaxAge(10*24*3600);
		response.addCookie(cookieUserPwd);
		User loginuser=service.login(user);
		
		if(loginuser==null) {
			m.addAttribute("error", "用户名或密码错误");
			return "user/login";
		}
		m.addAttribute("user", user);
		
		request.getSession().setAttribute(CmsContant.USER_KEY, loginuser);
		request.getSession().setAttribute("userRole", loginuser.getRole());
		request.getSession().setAttribute("adminrole", CmsContant.USER_ROLE_ADMIN);
		
		
		return "redirect:/home/index";
	}
	@RequestMapping("loginout")
	public String loginout(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute(CmsContant.USER_KEY);
		Cookie cookieUserName = new Cookie("username", "");
		cookieUserName.setPath("/");
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
		Cookie cookieUserPwd = new Cookie("userpwd", "");
		cookieUserPwd.setPath("/");
		cookieUserPwd.setMaxAge(0);
		response.addCookie(cookieUserPwd);
		return "redirect:/home/index";
	}
}
