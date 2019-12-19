package com.zyp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyp.bean.Comment;
import com.zyp.bean.User;
import com.zyp.common.CmsContant;
import com.zyp.common.CmsError;
import com.zyp.common.CmsMessage;
import com.zyp.service.CommentService;

@Controller
@RequestMapping("comment")
public class CommentController {
	@Autowired
	private CommentService service;
	@RequestMapping("comments")
	public String comments(Model m,int id,@RequestParam(defaultValue="1")  int pageNum,HttpServletRequest request) {
		PageHelper.startPage(pageNum, 5);
		List<Comment> clist = service.getComments(id);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(clist);
		User loginUser  = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		request.getSession().setAttribute("userUrl", loginUser.getUrl());
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
	
}
