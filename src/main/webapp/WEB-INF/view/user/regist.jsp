<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="/jquery-validation-1.19.1/dist/jquery.validate.js"></script>
<script type="text/javascript" src="/jquery-validation-1.19.1/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/change.js"></script>
</head>
<body>
<nav class="nav nav-pills flex-column flex-sm-row">
  <a class="flex-sm-fill text-sm-center nav-link active" href="tologin.do">登录</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="#">Longer nav link</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="#">Link</a>
  <a class="flex-sm-fill text-sm-center nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
</nav>
	<form:form  action="regist.do" method="post" modelAttribute="user" style="margin-left:500px;margin-top:200px;" id="form" max="8" min="5">
		<div class="container">
		<div class="form-group">
			<div>
 				<form:input path="username" remote="/user/checkname.do" class="form-control" style="display:inline;width:200px;height:35px" placeholder="username"/>			
 				<form:errors path="username" cssStyle="color:red"></form:errors>
			</div>
		</div>
		<div class="form-group">
			<div>
 				<form:input path="password" class="form-control" style="display:inline;width:200px;height:35px" placeholder="password"/>
 				<form:errors path="password" cssStyle="color:red"></form:errors>
			</div>
		</div>
		<div class="form-group">
			<div>
				<form:button class="btn btn-primary">regist</form:button>
			</div>
		</div>
	</div>
	</form:form>
	<script type="text/javascript">
			$("#form").validate();
	</script>
</body>
</html>