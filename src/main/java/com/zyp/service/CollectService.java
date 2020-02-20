package com.zyp.service;

import java.util.List;

import com.zyp.bean.Collect;

public interface CollectService {

	List<Collect> clist(int id);

	Boolean add(Collect collect);

	void del(int id);

}
