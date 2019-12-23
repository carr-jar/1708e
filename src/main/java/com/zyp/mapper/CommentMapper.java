package com.zyp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.PageInfo;
import com.zyp.bean.Article;
import com.zyp.bean.Comment;
import com.zyp.bean.Complain;

public interface CommentMapper {

	@Select("SELECT c.*,u.username userName,u.url userUrl FROM cms_comment  c "
			+ " LEFT JOIN cms_user as u ON u.id=c.userId "
			+ " WHERE articleId=#{value} ORDER BY c.created DESC")
	List<Comment> getComments(int id);

	@Insert("INSERT INTO cms_comment(articleId,userId,content,created)"
			+ " VALUES(#{articleId},#{userId},#{content},NOW())")
	int addComment(Comment comment);
	

	//增加文章的评论数量
	@Update("UPDATE cms_article SET commentCnt=commentCnt+1 WHERE id=#{value}")
	int updateCommentCnt(int id);

	Article getById(int articleId);

	@Insert("INSERT INTO cms_complain(article_id,user_id,complain_type,"
			+ "compain_option,src_url,picture,content,email,mobile,created)"
			+ "   VALUES(#{articleId},#{userId},"
			+ "#{complainType},#{compainOption},#{srcUrl},#{picture},#{content},#{email},#{mobile},now())")
	int addComplain(Complain complain);

	@Update("UPDATE cms_article SET complainCnt=complainCnt+1,status=if(complainCnt>10,2,status)  "
			+ " WHERE id=#{value}")
	void increaseComplainCnt(Integer articleId);
	
	List<Complain> getComplains(int articleId);
}
