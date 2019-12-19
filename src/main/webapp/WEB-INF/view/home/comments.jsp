<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<c:forEach items="${clist}" var="clist">
			<div class="row" style="margin-top:50px">
				<div class="col-md-9">
					${clist.content} 
				</div>
				
				<div class="col-md-3">
					<img alt="" src="/pic/${userUrl }"
					onerror="this.src='/img/269.JPG'"
					 width="20px" height="20px">
					${clist.userName} 发表于 <fmt:formatDate value="${clist.created}" pattern="yyyy-MM-dd" />
				</div>
				
				</div> 
		</c:forEach>  
		<!-- 分页 -->
			<div class="row" style="margin-left:350px">
				<nav aria-label="Page navigation example" >
						  <ul class="pagination ">
						  
						    <li class="page-item">
						      <a class="page-link" href="javascript:gopage(${p.pageNum-1})" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    
						    <c:forEach begin="1" end="${p.pages}" varStatus="index">
						    	
						    	<!-- 当前页码的处理 -->
						    	<c:if test="${p.pageNum==index.index}">
						    		<li class="page-item"><a class="page-link" href="javascript:void()"><font color="red"> ${index.index} </font></a>  </li>
						  		</c:if>
						    </c:forEach>
						    
						    <li class="page-item">
						      <a class="page-link" href="javascript:gopage(${p.pageNum+1})" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						    
						  </ul>
						</nav>
			</div>