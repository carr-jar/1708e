<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
<table class="table">
  <!-- <thead class="thead-dark">
    <tr>
      <th scope="col">标题</th>
    </tr>
  </thead> -->
  <tbody>
  	
  	<c:forEach items="${list }" var="l">
    <tr>
      <td><img alt="" src="/pic/${s.picture }" style="height: 10px;width: 10px"></td>
      <td>${l.title }</td>
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
	$("#work").load("/home/list?pageNum="+page);
}
</script>