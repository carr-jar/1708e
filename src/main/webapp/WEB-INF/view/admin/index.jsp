<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<nav class="navbar navbar-expand-lg navbar-light bg-secondary">
  <a class="navbar-brand" href="/home/index">首页</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    <div>
    	<ul class="nav">
		    <li class="nav-item nav-link"><img  src="/img/279885.jpg" width="30px" height="30px"></li>
		    <li class="nav-item nav-link">${user.username }</li>
		    <li class="nav-item nav-link">图像的</li>
		    <li class="nav-item nav-link">支持</li>
		    <li class="nav-item nav-link"><a style="color:red" href="/user/tologin.do">退出登录</a></li>
		</ul>
    </div>
  </div>
</nav>

	<div class="row">
  <div class="col-2">
    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
      <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true" onclick="showWork('/admin/article')">文章管理</a>
      <a style="margin-top:50px" class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false" onclick="showWork('/article/compains')">投诉管理</a>
      <a style="margin-top:50px" class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false" onclick="showWork('/article/comments')">友情链接管理</a>
      <a style="margin-top:50px" class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false">用户管理</a>
    </div>
  </div>
  <div class="col-10" id="work">
    	
  </div>
</div>
<nav class="nav fixed-bottom justify-content-center" style="background:#C0C0C0" height="50px"> 
	      哈哈哈
</nav>
<script type="text/javascript">
	function showWork(url) {
		$("#work").load(url);
	}
</script>	
	
</body>
</html>