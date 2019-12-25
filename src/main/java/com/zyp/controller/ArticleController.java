package com.zyp.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;
import com.zyp.bean.Compain;
import com.zyp.bean.User;
import com.zyp.cms.utils.FileUtils;
import com.zyp.cms.utils.HtmlUtils;
import com.zyp.cms.utils.StringUtils;
import com.zyp.common.CmsContant;
import com.zyp.service.ArticleService;

@Controller
@RequestMapping("article")
public class ArticleController {
	@Autowired
	private ArticleService service;
	
	@Value("${upload.path}")
	String picRootPath;
	
	@Value("${pic.path}")
	String picUrl;
	
	@RequestMapping("list")
	public String list(Model m,@RequestParam(defaultValue = "1")int pageNum,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		PageHelper.startPage(pageNum, 8);
		List<Article> list=service.list(user.getId());
		PageInfo<Article> pageInfo = new PageInfo<Article>(list);
		m.addAttribute("list", list);
		m.addAttribute("p", pageInfo);
		return "/user/articles/list";
	}
	@ResponseBody
	@RequestMapping("del.do")
	public Object del(int id) {
		try {
			service.del(id);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@RequestMapping("postArticle")
	public String postArticle(Model m) {
		List<Channel> channels=service.getChannels();
		m.addAttribute("channels", channels);
		return "/user/articles/post";
	}
	@ResponseBody
	@RequestMapping("getCategoris")
	public Object getCategoris(int cid) {
		List<Category> categoris =service.getCategoris(cid);
		return categoris;
	}
	@RequestMapping("postArticles")
	@ResponseBody
	public boolean postArticle(HttpServletRequest request, Article article, 
			MultipartFile file
			) {
		
		
		String picUrl;
		try {
			// 处理上传文件
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//当前用户是文章的作者
		User loginUser = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		article.setUser_id(loginUser.getId());
		System.out.println(article);
		return service.add(article)>0;
	}
	@RequestMapping("updateArticle")
	public String updateArticle(Model m,int id,HttpServletRequest request) {
		//获取栏目
		List<Channel> channels=service.getChannels();
		m.addAttribute("channels", channels);
		//获取文章
		Article article=service.toUpdateArticle(id);
		System.out.println(article);
		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if(loginUser.getId()!=article.getUser_id()) {
			//抛出异常
		}
		m.addAttribute("picture", picUrl+article.getPicture());
		m.addAttribute("article", article);
		m.addAttribute("content1", HtmlUtils.htmlspecialchars(article.getContent()));
		
		return "/user/articles/update";
	}
	@RequestMapping("updateArticles")
	@ResponseBody
	public boolean updateArticles(HttpServletRequest request, Article article, 
			MultipartFile file
			) {
		String picUrl;
		try {
			// 处理上传文件
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//当前用户是文章的作者
		User loginUser = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		article.setUser_id(loginUser.getId());
		System.out.println(article);
		return service.update(article)>0;
	}
	
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {
		// 判断目标目录时间否存在
		//picRootPath + ""
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String subPath = sdf.format(new Date());
		//图片存放的路径
		File path= new File(picRootPath+"/" + subPath);
		//路径不存在则创建
		if(!path.exists())
			path.mkdirs();
		
		//计算新的文件名称
		String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
		
		//随机生成文件名
		String fileName = UUID.randomUUID().toString() + suffixName;
		//文件另存
		file.transferTo(new File(picRootPath+"/" + subPath + "/" + fileName));
		return  subPath + "/" + fileName;
	}
	@RequestMapping("tocompain")
	public String tocompain(int articleId,Model m,HttpServletRequest request) {
		User user =(User) request.getSession().getAttribute(CmsContant.USER_KEY);
		Article article = service.toUpdateArticle(articleId);
		Compain compain = new Compain();
		m.addAttribute("article", article);
		m.addAttribute("compain", compain);
		return "/home/compain";
	}
	@RequestMapping("compain")
	public String compain(@Valid@ModelAttribute("compain") Compain compain,BindingResult result,HttpServletRequest request) {
		if(!StringUtils.isHttpUrl(compain.getUrlip())) {
			result.rejectValue("urlip", "", "格式不正确");
		}
		if(result.hasErrors()) {
			return "home/compain";
		}
		User user =(User) request.getSession().getAttribute(CmsContant.USER_KEY);
		compain.setUser_id(user.getId());
		int i=service.addcompain(compain);
		return "redirect:/home/detail?id="+compain.getArticle_id();
	}
	
	@RequestMapping("compains")
	public String compains(@RequestParam(defaultValue = "1")int pageNum,Model m,String complaintype,@RequestParam(defaultValue = "0")int cnt1,@RequestParam(defaultValue = "0")int cnt2,@RequestParam(defaultValue = "desc")String order) {
		PageHelper.startPage(pageNum, 5);
		List<Compain> cclist = service.compainList(complaintype,cnt1,cnt2,order);
		PageInfo<Compain> pageInfo = new PageInfo<Compain>(cclist);
		m.addAttribute("cclist", cclist);
		m.addAttribute("p", pageInfo);
		m.addAttribute("complaintype", complaintype);
		m.addAttribute("cnt1", cnt2);
		m.addAttribute("cnt1", cnt2);
		return "/admin/article/compainlist";
	}
	@RequestMapping("compainss")
	public String compainss(int id,Model m) {
		Compain compain = service.getcompain(id);
		m.addAttribute("compain", compain);
		return "/admin/article/xqlist";
	}
}
