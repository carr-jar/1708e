package com.zyp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;
import com.zyp.mapper.ArticleMapper;
import com.zyp.service.ArticleService;
@Service
public class ArticleServiceImp implements ArticleService{
	@Autowired
	private ArticleMapper mapper;

	@Override
	public List<Article> list(int id) {
		// TODO Auto-generated method stub
		return mapper.list(id);
	}

	@Override
	public void del(int id) {
		// TODO Auto-generated method stub
		mapper.del(id);
	}

	@Override
	public List<Channel> getChannels() {
		// TODO Auto-generated method stub
		return mapper.getChannels();
	}

	@Override
	public List<Category> getCategoris(int id) {
		// TODO Auto-generated method stub
		return mapper.getCategoris(id);
	}

	@Override
	public int add(Article article) {
		// TODO Auto-generated method stub
		return mapper.add(article);
	}

	@Override
	public Article toUpdateArticle(int id) {
		// TODO Auto-generated method stub
		return mapper.toUpdateArticle(id);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return mapper.update(article);
	}
}
