package com.zyp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyp.bean.Article;
import com.zyp.bean.Channel;
import com.zyp.bean.Slide;
import com.zyp.service.HomeService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	private HomeService service;
	@RequestMapping("index")
	public String index(Model m) {
		List<Channel> channels=service.getChannels();
		List<Slide> slides=service.getSlide();
		m.addAttribute("channels", channels);
		m.addAttribute("slides", slides);
		return "/home/index";
	}
	@RequestMapping("list")
	public String list(Model m,@RequestParam(defaultValue = "1")int pageNum,HttpServletRequest request,int id) {
		PageHelper.startPage(pageNum, 8);
		List<Article> list=service.list(id);
		PageInfo<Article> pageInfo = new PageInfo<Article>(list);
		m.addAttribute("list", list);
		m.addAttribute("p", pageInfo);
		return "/home/list";
	}
}
