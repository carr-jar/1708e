package com.zyp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyp.bean.Link;
import com.zyp.service.LinkService;

@Controller
@RequestMapping("link")
public class LinkController {
	@Autowired
	private LinkService service;
	@ResponseBody
	@RequestMapping("lList")
	public Object lList(Model m) {
		List<Link> lList = service.lList();
		return lList;
	}
}
