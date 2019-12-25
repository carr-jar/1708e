<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
投诉类型<select name="complaintype" id="complaintype">
			<option value="">请选择</option>	
			<option value="A">涉及黄色</option>
			<option value="B">涉及暴力</option>
			<option value="C">涉及宗教政策</option>
		</select>
次数大于<input type="text" name="cnt1" id="cnt1" value="${cnt1 }"/>
次数小于<input type="text" name="cnt2" id="cnt2" value="${cnt2 }"/>
<input type="button" value="查询" onclick="tourl()"/>
<table class="table">
  <thead class="thead-dark">
    <tr>
    <th>编号</th>
    <th>标题</th>
    <th>文章内容</th>
    <th>投诉类型</th>
    <th>
    	<input type="button" value="投诉次数" onclick="order(asc)">
    </th>
    <th>投诉详情</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${cclist }" var="cc">
  <tr>
    <td>${cc.id }</td>
    <td>${cc.title }</td>
    <td>${cc.content }</td>
    <td>
    	<c:if test="${cc.complaintype=='A' }">
    			涉及黄色
   		</c:if>
   		<c:if test="${cc.complaintype=='B' }">
   			涉及暴力
   		</c:if>
   		<c:if test="${cc.complaintype=='C' }">
   			涉及宗教政策
   		</c:if>
    </td>
    <td>${cc.complainCnt }</td>
    <td>
    	<input type="button" value="详情" onclick="xq(${cc.id})">
    </td>
    <td>
    	<input type="${cc.complainCnt>50?'button':'hidden' }" value="禁止" onclick="setStatus(2)">
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
	$("#work").load("/article/compains?pageNum="+page);
}
function tourl(){
	$("#work").load("/article/compains?complaintype="+$("#complaintype").val()+"&cnt1="+$("#cnt1").val()+"&cnt2="+$("#cnt2").val());
}
function order(order){
	$("#work").load("/article/compains?order="+order);
}
function xq(id){
	$("#work").load("/article/compainss?id="+id);
}
</script>