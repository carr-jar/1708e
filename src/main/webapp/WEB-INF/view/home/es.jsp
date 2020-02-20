<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">id</th>
      <th scope="col">标题</th>
      <th scope="col">栏目</th>
      <th scope="col">分类</th>
      <th scope="col">发布时间</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list }" var="l">
    <tr>
      <th scope="row">${l.id }</th>
      <td>${l.title }</td>
      <td>${l.channel.name }</td>
      <td>${l.category.name }</td>
      <td><fmt:formatDate value="${l.created }" pattern="yyyy年MM月dd日"/></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
<!-- 分页开始 -->
			<div class="row justify-content-center" style="margin-top:20px">
				<nav aria-label="Page navigation example" >
					  <ul class="pagination ">
					  
					    <li class="page-item">
					      <a class="page-link" href="/home/es?pageNum=${p.pageNum-1}&title=${title}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    
					    <c:forEach begin="1" end="${p.pages}" varStatus="index">
					    	<li class="page-item"><a class="page-link" href="/home/es?pageNum=${index.index}&title=${title}"> ${index.index}</a></li>
					    </c:forEach>
					    
					    <li class="page-item">
					      <a class="page-link" href="/home/es?pageNum=${p.pageNum+1}&title=${title}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    
					  </ul>
					</nav>
			</div>
</body>
</html>