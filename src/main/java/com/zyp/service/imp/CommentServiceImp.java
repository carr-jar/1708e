package com.zyp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyp.bean.Comment;
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
}
