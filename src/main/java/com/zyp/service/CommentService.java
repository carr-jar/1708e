package com.zyp.service;

import java.util.List;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.zyp.bean.Article;
import com.zyp.bean.Comment;
import com.zyp.bean.Complain;

public interface CommentService {

	List<Comment> getComments(int id);

	int addComment(Comment comment);

	Article getById(int articleId);

	int addComplain(Complain complain);

	List<Complain> getComplains(int articleId);


	
}
