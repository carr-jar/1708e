package com.zyp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.zyp.bean.Comment;
import com.zyp.bean.Complain;
import com.zyp.bean.User;
import com.zyp.cms.utils.StringUtils;
import com.zyp.common.CmsContant;
import com.zyp.common.CmsError;
import com.zyp.common.CmsMessage;
import com.zyp.service.CommentService;

@Controller
@RequestMapping("comment")
public class CommentController extends BaseController{
	@Autowired
	private CommentService service;
	@RequestMapping("comments")
	public String comments(Model m,int id,@RequestParam(defaultValue="1")  int pageNum,HttpServletRequest request) {
		PageHelper.startPage(pageNum, 5);
		List<Comment> clist = service.getComments(id);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(clist);
		User loginUser  = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		if(loginUser!=null) {
			request.getSession().setAttribute("userUrl", loginUser.getUrl());
		}
		m.addAttribute("clist", clist);
		m.addAttribute("p", pageInfo);
		return "home/comments";
	}
	
	@ResponseBody
	@RequestMapping("postcomment")
	public CmsMessage postcomment(HttpServletRequest request,int articleId,String content) {
		System.out.println(content);
		User loginUser  = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		
		if(loginUser==null) {
			return new CmsMessage(CmsError.NOT_LOGIN, "您尚未登录！", null);
		}
		
		Comment comment = new Comment();
		comment.setUserId(loginUser.getId());
		comment.setContent(content);
		comment.setArticleId(articleId);
		int result = service.addComment(comment);
		if(result > 0)
			return new CmsMessage(CmsError.SUCCESS, "成功", null);
		
		return new CmsMessage(CmsError.FAILED_UPDATE_DB, "异常原因失败，请与管理员联系", null);
	}
	
	@RequestMapping("tocomplain")
	public String complain(Model m,int articleId) {
		Article article= service.getById(articleId);
		m.addAttribute("article", article);
		m.addAttribute("complain", new Complain());
		return "home/complain";
				
	}
	
	@RequestMapping("complain")
	public String complain(HttpServletRequest request,
			@ModelAttribute("complain") @Valid Complain complain,
			MultipartFile file,
			BindingResult result) throws IllegalStateException, IOException {
		
		if(!StringUtils.isHttpUrl(complain.getSrcUrl())) {
			result.rejectValue("srcUrl", "", "不是合法的url地址");
		}
		if(result.hasErrors()) {
			Article article= service.getById(complain.getArticleId());
			request.setAttribute("article", article);
			return "home/complain";
		}
		
		User loginUser  =  (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		
		String picUrl = this.processFile(file);
		complain.setPicture(picUrl);
		
		
		//加上投诉人
		if(loginUser!=null)
			complain.setUserId(loginUser.getId());
		else
			complain.setUserId(0);
		
		int i =service.addComplain(complain);
		
		return "redirect:/home/detail?id="+complain.getArticleId();
				
	}
	
	@RequestMapping("complains")
	public String 	complains(HttpServletRequest request,int articleId,
			@RequestParam(defaultValue="1") int pageNum) {
		PageHelper.startPage(pageNum, 2);
		List<Complain> complainList=   service.getComplains(articleId);
		PageInfo<Complain> pageInfo = new PageInfo<Complain>(complainList);
		request.setAttribute("complainList", complainList);
		request.setAttribute("p", pageInfo);
		return "admin/article/complainslist";
	}
}
