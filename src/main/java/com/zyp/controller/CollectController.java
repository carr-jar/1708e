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
import com.zyp.bean.Collect;
import com.zyp.bean.User;
import com.zyp.common.CmsContant;
import com.zyp.service.CollectService;

@Controller
@RequestMapping("collect")
public class CollectController {
	@Autowired
	CollectService service;
	@RequestMapping("collects")//收藏夹列表展示
	public String collects(Model m,@RequestParam(defaultValue = "1")int pageNum,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		PageHelper.startPage(pageNum, 5);
		List<Collect> clist = service.clist(user.getId());
		PageInfo<Collect> pageInfo = new PageInfo<Collect>(clist);
		m.addAttribute("p", pageInfo);
		m.addAttribute("clist", clist);
		return "home/collects";
	}
	@RequestMapping("toadd")
	public String toadd() {
		return "home/addcol";
	}
	@ResponseBody
	@RequestMapping("add")//添加方法
	public Object add(Collect collect,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		Integer id = user.getId();
		collect.setUser_id(id);
		Boolean b= service.add(collect);
		if(!b) {
			return false;
		}else {
			return true;
		}
	}
	@ResponseBody
	@RequestMapping("del")//删除方法
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
}
