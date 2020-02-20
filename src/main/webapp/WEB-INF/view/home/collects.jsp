<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
	<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
	<input type="button" value="添加" onclick="addcol()">
<table>
	<c:forEach items="${clist }" var="l">
  <tr>
    <th><a href="${l.url }" target="_blank">${l.name }</a></th>
    <th>${l.created }</th>
    <th>
    	<input type="button" value="删除" onclick="delcol(${l.id})">
    </th>
  </tr>
  </c:forEach>
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
<script type="text/javascript">
function gopage(page){
	$("#work").load("/collect/collects?pageNum="+page);
}
function addcol(){
	$("#work").load("/collect/toadd");
}
function delcol(id){
	$.post("/collect/del",{"id":id},function(i){
		if(i){
			alert("删除成功");
		}else{
			alert("删除失败");
		}
	},"json")
}
</script>
	