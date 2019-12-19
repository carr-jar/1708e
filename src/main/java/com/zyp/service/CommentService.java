package com.zyp.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zyp.bean.Comment;

public interface CommentService {

	List<Comment> getComments(int id);

	int addComment(Comment comment);


	
}
