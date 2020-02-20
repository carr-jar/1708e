package com.zyp.col;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zyp.bean.Collect;
import com.zyp.service.CollectService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestCol {
	@Autowired
	CollectService service;
	@Test
	public void testList() {//测试收藏夹列表
		int id=68;
		List<Collect> clist = service.clist(id);
		for (Collect collect : clist) {
			System.out.println(collect);
		}
	}
	@Test
	public void testAdd() {//测试添加功能
		Collect c = new Collect();
		c.setName("月考");
		c.setUrl("http://sohu.com/");
		c.setUser_id(68);
		Boolean add = service.add(c);
	}
	@Test
	public void testDel() {//测试删除功能
		int id=15;
		service.del(id);
	}
}
