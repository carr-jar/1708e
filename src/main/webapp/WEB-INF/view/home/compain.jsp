<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/css/bootstrap.min.css"></script>
</head>
<body>
<h3>投诉内容</h3>
<form:form action="/article/compain" method="post" modelAttribute="compain">
<input type="hidden" name="article_id" value="${article.id }">
<form:radiobutton path="complaintype" label="涉及黄色"  value="A"/>
<form:radiobutton path="complaintype" label="涉及暴力" value="B"/>
<form:radiobutton path="complaintype" label="涉及违宗教政策" value="C"/><br>
证据url地址<form:input path="urlip"/><form:errors path="urlip"></form:errors><br>
<form:button>提交</form:button>
</form:form>
</body>
</html>