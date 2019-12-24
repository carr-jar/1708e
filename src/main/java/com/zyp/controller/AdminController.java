package com.zyp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyp.bean.Article;
import com.zyp.common.CmsError;
import com.zyp.common.CmsMessage;
import com.zyp.service.AdminService;

@RequestMapping("admin")
@Controller
public class AdminController {
	@Autowired
	private AdminService service;
	@RequestMapping("index.do")
	public String index() {
		return "admin/index";
	}
	@RequestMapping("article")
	public String article(Model m,@RequestParam(defaultValue = "-1")int status,@RequestParam(defaultValue = "1")int pageNum) {
		PageHelper.startPage(pageNum, 8);
		List<Article> list=service.list(status);
		PageInfo<Article> pageInfo = new PageInfo<Article>(list);
		m.addAttribute("list", list);
		m.addAttribute("p", pageInfo);
		m.addAttribute("status", status);
		return "/admin/article/list";
	}
	@ResponseBody
	@RequestMapping("getDetail")
	public CmsMessage getDetail(int id) {
		if(id<=0) {
			
		}
		// 获取文章详情
		Article article = service.getById(id);
		// 不存在
		if(article==null)
			return new CmsMessage(CmsError.NOT_EXIST, "文章不存在",null);
		// 返回数据
		return new CmsMessage(CmsError.SUCCESS,"",article); 
	}
	@ResponseBody
	@RequestMapping("setArticleStatus")
	public CmsMessage setArticleStatus(int id,int status) {
		//校验数据
		if(status!=1 && status!=2) {
			return new CmsMessage(CmsError.NOT_VALIDATED_ARGURMENT, "status参数不合法", null);
		}
		if(id<0) {
			return new CmsMessage(CmsError.NOT_VALIDATED_ARGURMENT, "id参数不合法", null);
		}
		Article article=service.getInfoById(id);
		if(article==null) {
			return new CmsMessage(CmsError.NOT_EXIST, "数据不存在", null);
		}
		if(article.getStatus()==status) {
			return new CmsMessage(CmsError.NEEDNT_UPDATE, "数据无需更改", "null");
		}
		//修改数据
		int result=service.setCheckStatus(id,status);
		if(result<1) {
			return new CmsMessage(CmsError.FAILED_UPDATE_DB, "设置失败", null);
		}
		return new CmsMessage(CmsError.SUCCESS, "成功", null);
	}
	@ResponseBody
	@RequestMapping("setArticleHot")
	public CmsMessage  setArticleHot(int id,int hot) {
		//校验数据
		if(hot!=1 && hot!=0) {
			return new CmsMessage(CmsError.NOT_VALIDATED_ARGURMENT, "hot参数不合法", null);
		}
		if(id<0) {
			return new CmsMessage(CmsError.NOT_VALIDATED_ARGURMENT, "id参数不合法", null);
		}
		Article article=service.getInfoById(id);
		if(article==null) {
			return new CmsMessage(CmsError.NOT_EXIST, "数据不存在", null);
		}
		if(article.getHot()==hot) {
			return new CmsMessage(CmsError.NEEDNT_UPDATE, "数据无需更改", "null");
		}
		//修改数据
		int result=service.setCheckHot(id,hot);
		if(result<1) {
			return new CmsMessage(CmsError.FAILED_UPDATE_DB, "设置失败", null);
		}
		return new CmsMessage(CmsError.SUCCESS, "成功", null);
	}
}
