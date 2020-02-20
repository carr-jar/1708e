package com.zyp.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;
import com.zyp.bean.Slide;
import com.zyp.bean.User;
import com.zyp.common.CmsContant;
import com.zyp.service.ArticleService;
import com.zyp.service.HomeService;
import com.zyp.util.HLUtils;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	private HomeService service;
	@Autowired
	private ArticleService articleService;
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	KafkaTemplate kafkaTemplate;
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
			@SuppressWarnings("unchecked")
			public void run() {
				// 获取热门文章
				List<Article> range = redisTemplate.opsForList().range("hot_articles", 0, -1);
				if(range==null || range.size()==0) {
					List<Article> hotlist =service.hotList();
					redisTemplate.opsForList().leftPushAll("hot_articles", hotlist.toArray());
				}
				List<Article> range2 = redisTemplate.opsForList().range("hot_articles", (pageNum-1)*8, (pageNum-1)*8+7);
				m.addAttribute("hotlist", range2);
			};
		};
		
		Thread  t3 =  new Thread() {
			@SuppressWarnings("unchecked")
			public void run() {
		// 获取最新文章
				List<Article> range = redisTemplate.opsForList().range("new_articles", 0, -1);
				if(range==null || range.size()==0) {
					range =service.lastList();
					redisTemplate.opsForList().leftPushAll("new_articles", range.toArray());
				}
				PageInfo<Article> page = (PageInfo<Article>) HLUtils.getPage(range, 6, pageNum);
				List<Article> range2 = redisTemplate.opsForList().range("new_articles", (pageNum-1)*6, (pageNum-1)*6+4);
				m.addAttribute("lastlist", range2);
				m.addAttribute("p1", page);
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
				List<Article> alist = service.cntList();
				m.addAttribute("alist", alist);
			};
		};
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if(user==null) {
			m.addAttribute("islogin", "登录");
			m.addAttribute("url", "/user/tologin.do");
		}else {
			m.addAttribute("islogin", "退出登录");
			m.addAttribute("url", "/user/loginout");
		}
		//参数回传
		m.addAttribute("pageNum", pageNum);
		
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
	
	@Autowired
	ThreadPoolTaskExecutor executor;
	@RequestMapping("detail")
	public String detail(Model m,int id,HttpServletRequest request) throws InterruptedException {
		String jsonString = JSON.toJSONString(id);
		kafkaTemplate.send("test2", "look=="+jsonString);
//		String addr = request.getRemoteAddr();
//		String key = "Hits_"+id+"_"+addr;
		User user =(User) request.getSession().getAttribute(CmsContant.USER_KEY);
		m.addAttribute("user", user);
		Article article=service.detail(id);
		m.addAttribute("article", article);
//		Boolean hasKey = redisTemplate.hasKey(key);
//		if(!hasKey) {
//			executor.execute(new Runnable() {
//				
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					article.setHits(article.getHits()+1);
//					service.addHit(id);
//					redisTemplate.opsForValue().set(key, "", 5, TimeUnit.MINUTES);
//				}
//			});
//		}
		
		return "/home/detail";
	}
	@RequestMapping("es")
	public String findByEs(String title,Model m,@RequestParam(defaultValue = "1")int pageNum) throws ServletException, IOException{
		List<Article> esList = service.findByEs(title);
		m.addAttribute("title", title);
		long l1 = System.currentTimeMillis();
		PageInfo<Article> findByHighLight = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, 3, new String[] {"title"}, "id", title);
		long l2 = System.currentTimeMillis();
		m.addAttribute("p", findByHighLight);
		List<Article> list = findByHighLight.getList();
		m.addAttribute("list", list);
		return "/home/es";
	}
}
