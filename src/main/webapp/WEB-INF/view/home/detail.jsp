<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	</nav>
	<div class="container">
		<div class="row justify-content-center" >
			<h3>${article.title}</h3>
		</div>
		<div class="row justify-content-center">
			<h5>
			作者：${article.user.username} &nbsp;&nbsp;&nbsp;
			栏目：${article.channel.name}  &nbsp;&nbsp;&nbsp;
			分类：${article.category.name}&nbsp;&nbsp;&nbsp;
			发表时间：<fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd"/> 
			</h5>
			<a href="/comment/tocomplain?articleId=${article.id}">投诉</a>
			
		</div>
		<div style="margin-top:30px">
			${article.content}
		</div>
		<div>
			<nav aria-label="...">
					  <ul class="pagination">
					    <li class="page-item ">
					      <a class="page-link" href="/home/detail?id=${artile.id-1<0?'1':'artile.id-1' }" tabindex="-1" >上一篇</a>
					    </li>
					    <li class="page-item">
					      <a class="page-link" href="/home/detail?id=${artile.id+1}">下一篇</a>
					    </li>
					  </ul>
			</nav>
		</div>
	</div>
		
	<!-- 发布评论 -->
	<div>
		<div style="margin-left:100px">
			<textarea rows="5" cols="160" id="commentText">
				
			</textarea>
			<input type="button" class="btn btn-primary" onclick="addComment()" value="发表评论">
		</div>
		<div id="comment" style="margin-left:100px">
			
		</div>
	</div>
	<script type="text/javascript">
	
		function gopage(page){
			showComment(page)
		}
		function showComment(page){
			$("#comment").load("/comment/comments?id=${article.id }&pageNum="+page);
		}
		
		$(document).ready(function(){
			// 显示第一页的评论
			showComment(1)
		})
		
		function addComment(){
			alert($("#commentText").val());
			
			  $.post("/comment/postcomment",
					{articleId:'${article.id}',content:$("#commentText").val()},
				function(msg){
					if(msg.code==1){
						alert('发布成功')
						/* $("#commentText").val(""); */
						history.go(0);
					}else{
						alert(msg.error);
						location="/user/tologin.do";
					}
					
				},
				"json")  
		}
	</script>
</body>
</html>