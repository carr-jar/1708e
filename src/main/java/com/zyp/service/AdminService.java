package com.zyp.service;

import java.util.List;

import com.zyp.bean.Article;

public interface AdminService {

	List<Article> list(int status);

	Article getById(int id);

	Article getInfoById(int id);

	int setCheckStatus(int id, int status);

	int setCheckHot(int id, int hot);

}
