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
import com.zyp.bean.Category;
import com.zyp.bean.Channel;
import com.zyp.bean.Slide;
import com.zyp.bean.User;
import com.zyp.common.CmsContant;
import com.zyp.service.HomeService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	private HomeService service;
	@RequestMapping("index")
	public String index(Model m,
								HttpServletRequest request,
								@RequestParam(defaultValue="1")  int pageNum,
								@RequestParam(defaultValue="1")  int lastpage
			) throws InterruptedException {
		Thread  t1 =  new Thread() {
			public void run() {
		// 获取所有的栏目
		List<Channel> channels = service.getChannels();
		m.addAttribute("channels", channels);
			};
		};

		Thread  t2 =  new Thread() {
			public void run() {
				// 获取热门文章
				PageHelper.startPage(pageNum, 8);
				List<Article> hotlist =service.hotList();
				PageInfo<Article> pageInfo = new PageInfo<Article>(hotlist);
				m.addAttribute("hotlist", hotlist);
				m.addAttribute("p", pageInfo);
			};
		};
		
		Thread  t3 =  new Thread() {
			public void run() {
		// 获取最新文章
				PageHelper.startPage(lastpage, 5);
				List<Article> lastlist =service.lastList();
				PageInfo<Article> pageInfo = new PageInfo<Article>(lastlist);
				m.addAttribute("lastlist", lastlist);
				m.addAttribute("p1", pageInfo);
			};
		};
		
		Thread  t4 =  new Thread() {
			public void run() {
				// 轮播图
				List<Slide> slides = service.getSlide();
				m.addAttribute("slides", slides);
			};
		};
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if(user==null) {
			m.addAttribute("islogin", "登录");
		}else {
			m.addAttribute("islogin", "退出登录");
		}
		//参数回传
		m.addAttribute("pageNum", pageNum);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		return "/home/index";
	}
	/**
	 * 
	 * @param channleId  栏目的id
	 * @param catId 分类的id
	 * @param pageNum 页码
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("channel")
	public String channel(Model m,
			HttpServletRequest request,
			int channelId,
			@RequestParam(defaultValue="0") int catId,
			@RequestParam(defaultValue="1")  int pageNum,
			@RequestParam(defaultValue="1")  int lastpage
			) throws InterruptedException {
		
		Thread  t1 =  new Thread() {
			public void run() {
					// 获取所有的栏目
					List<Channel> channels = service.getChannels();
					m.addAttribute("channels", channels);
			};
		};
		
		Thread  t2 =  new Thread() {
			public void run() {
				// 当前栏目下  当前分类下的文章
				PageHelper.startPage(pageNum, 8);
				List<Article> channelList =service.getArticles(channelId,catId);
				PageInfo<Article> pageInfo = new PageInfo<Article>(channelList);
				m.addAttribute("channelList", channelList);
				m.addAttribute("p", pageInfo);
			};
		};
		
		Thread  t3 =  new Thread() {
			public void run() {
				// 获取最新文章
				PageHelper.startPage(lastpage, 5);
				List<Article> lastlist =service.lastList();
				PageInfo<Article> pageInfo = new PageInfo<Article>(lastlist);
				request.getSession().setAttribute("lastlist", lastlist);
				m.addAttribute("p1", pageInfo);
			};
		};
		
		Thread  t4 =  new Thread() {
			public void run() {
				// 轮播图
				List<Slide> slides = service.getSlide();
				m.addAttribute("slides", slides);
			};
		};
		
		Thread  t5 =  new Thread() {
			public void run() {
				// 获取当前栏目下的所有的分类 catId
				List<Category> categoris= service.getCategoriesByChannelId(channelId);
				m.addAttribute("categoris", categoris);
				System.err.println("categoris is " + categoris);
			};
		};
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		
		// 参数回传
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("catId", catId);
		m.addAttribute("channelId", channelId);
		
		return "/home/channel";
	}
	@RequestMapping("detail")
	public String detail(Model m,int id) {
		Article article=service.detail(id);
		m.addAttribute("article", article);
		return "/home/detail";
	}
}
