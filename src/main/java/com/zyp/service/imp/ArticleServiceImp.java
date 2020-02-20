package com.zyp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.bean.Article;
import com.zyp.bean.Category;
import com.zyp.bean.Channel;
import com.zyp.bean.Compain;
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
		String title = article.getTitle();
		System.out.println(title+"已导入完毕");
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

	@Override
	public int addcompain(Compain compain) {
		// TODO Auto-generated method stub
		int i = mapper.addcompain(compain);
		mapper.increaseComplainCnt(compain.getArticle_id());
		return 0;
	}

	@Override
	public List<Compain> compainList(String complaintype,int cnt1,int cnt2,String order) {
		// TODO Auto-generated method stub
		return mapper.compainList(complaintype,cnt1,cnt2,order);
	}

	@Override
	public Compain getcompain(int id) {
		// TODO Auto-generated method stub
		return mapper.getcompain(id);
	}

	@Override
	public void addlook(String value) {
		// TODO Auto-generated method stub
		mapper.addlook(value);
	}
}
