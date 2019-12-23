package com.zyp.service.imp;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zyp.bean.Article;
import com.zyp.bean.Comment;
import com.zyp.bean.Complain;
import com.zyp.mapper.CommentMapper;
import com.zyp.service.CommentService;
@Service
public class CommentServiceImp implements CommentService{
	@Autowired
	private CommentMapper mapper;

	@Override
	public List<Comment> getComments(int id) {
		// TODO Auto-generated method stub
		return mapper.getComments(id);
	}

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		mapper.updateCommentCnt(comment.getArticleId());
		return mapper.addComment(comment);
	}

	@Override
	public Article getById(int articleId) {
		// TODO Auto-generated method stub
		return mapper.getById(articleId);
	}

	@Override
	public int addComplain(Complain complain) {
		// TODO Auto-generated method stub
		int i =mapper.addComplain(complain);
		if(i>0) {
			mapper.increaseComplainCnt(complain.getArticleId());
		}
		return 0;
	}

	@Override
	public List<Complain> getComplains(int articleId) {
		// TODO Auto-generated method stub
		return mapper.getComplains(articleId);
	}
}
