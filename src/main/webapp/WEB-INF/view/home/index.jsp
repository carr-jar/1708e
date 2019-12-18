<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- 导航条 -->
<nav class="navbar navbar-expand-lg navbar-light bg-secondary">
  <a class="navbar-brand" href="#">Navbar</a>
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
		    <li class="nav-item nav-link">Window</li>
		    <li class="nav-item nav-link">图像的</li>
		    <li class="nav-item nav-link">支持</li>
		    <li class="nav-item nav-link">更新</li>
		</ul>
    </div>
  </div>
</nav>
		

<div class="row">
	<!-- 左侧导航 -->
  <div class="col-1">
    	<div class="row">
		    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
		    	<a class="nav-link active" style="margin-top:100px;margin-left:50px" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true" onclick="show(-1)">推荐</a>
		    	<c:forEach items="${channels }" var="c" varStatus="index">
		     	 <a class="nav-link" style="margin-top:10px;margin-left:50px" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="false" onclick="show(${c.id})">${c.name }</a>
		      	</c:forEach>
		    </div>
		</div>
  </div>
  <div class="col-11">
    	<div class="row">
    		<div class="col-6">
					<!-- 轮播图 -->
					<div>
						<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
								  <ol class="carousel-indicators">
								  <c:forEach items="${slides }" var="s" varStatus="index">
								    	<li data-target="#carouselExampleCaptions" data-slide-to="${s.id }" class="${index.index==0?'active':'' }"></li>
								  </c:forEach>
								  </ol>
								  <div class="carousel-inner">
								  	<c:forEach items="${slides }" var="s" varStatus="index">
									    <div class="carousel-item ${index.index==0?'active':'' }">
									      <img src="/pic/${s.picture }" class="d-block w-100" alt="..." width="40%" height="40%">
									      <div class="carousel-caption d-none d-md-block">
									        <h5>${s.title }</h5>
									        <p>${s.title }</p>
									      </div>
									    </div>
								    </c:forEach>
								  </div>
								  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
								    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
								    <span class="sr-only">Previous</span>
								  </a>
								  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
								    <span class="carousel-control-next-icon" aria-hidden="true"></span>
								    <span class="sr-only">Next</span>
								  </a>
						</div>
					</div>
				<!-- 文章列表 -->	
    			<div id="work">
    				
    			</div>
    		</div>
    		<div class="col-4">
    		
    		</div>
    	</div>
  </div>
</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#work").load("/home/list?id=-1");
	})
	function show(id){
		$("#work").load("/home/list?id="+id);
	}
</script>
</html>