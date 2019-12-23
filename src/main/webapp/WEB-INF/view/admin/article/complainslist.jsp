<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:forEach items="${complainList}" var="cc">
	<div class="row">
		<div class="col-md-3">${cc.user.username}</div>
		<div class="col-md-3">${cc.content}</div>
	</div>

</c:forEach> 
