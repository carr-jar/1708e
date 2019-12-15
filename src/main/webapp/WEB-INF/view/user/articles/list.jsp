<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>

<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">id</th>
      <th scope="col">标题</th>
      <th scope="col">栏目</th>
      <th scope="col">分类</th>
      <th scope="col">发布时间</th>
      <th scope="col">状态</th>
      <th scope="col">操作</th>
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
      <td>
      		<c:choose>
      			<c:when test="${l.status==0 }">待审核</c:when>
      			<c:when test="${l.status==1 }">审核通过</c:when>
      			<c:when test="${l.status==2 }">审核被拒</c:when>
      			<c:otherwise>
      				未知
      			</c:otherwise>
      		</c:choose>
      </td>
      <td width="200px">
      		<input type="button" value="删除" class="btn btn-danger" onclick="del(${l.id})">
			<input type="button" value="修改" class="btn btn-primary" onclick="toupdate(${l.id})">
      </td>
    </tr>
    </c:forEach>
  </tbody>
  
</table>
		<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-center">
			    <li class="page-item"><a class="page-link" href="#" onclick="gopage(1)">首页</a></li>
			    <li class="page-item"><a class="page-link" href="#" onclick="gopage(${p.getPrePage()==0?1:p.getPrePage()})">上一页</a></li>
			    <li class="page-item"><a class="page-link" href="#" onclick="gopage(${p.getNextPage()==0?p.getPages():p.getNextPage()})">下一页</a></li>
			    <li class="page-item">
			      <a class="page-link" href="#" onclick="gopage(${p.getPages()})">尾页</a>
			    </li>
	 		 </ul>
		</nav>
		
<script>
	function gopage(page){
		$("#work").load("/article/list?pageNum="+page);
	}
	function del(id){
		if(confirm("确认删除?")){
			$.post("/article/del.do",{"id":id},function(i){
				if(i){
					alert("删除成功");
					$("#work").load("/article/list");
				}else{
					alert("删除失败")
				}
			},"json")
		}
	}
	function toupdate(id){
		$("#work").load("/article/updateArticle?id="+id);
	}
</script>	
		