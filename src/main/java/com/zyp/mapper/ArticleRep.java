package com.zyp.mapper;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zyp.bean.Article;

public interface ArticleRep extends ElasticsearchRepository<Article, Integer>{
	List<Article> findByTitle(String content);
}
