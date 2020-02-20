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
<style type="text/css">
.ex {
		overflow: hidden;
		text-overflow:ellipsis;
		white-space: nowrap;
	}
</style>
</head>
<body>
<!-- 导航条 -->
<nav class="navbar navbar-expand-lg navbar-light bg-secondary">
  <a class="navbar-brand" href="${userRole==adminrole?'/admin/index.do':'/user/home.do' }">个人中心</a>
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
    <!-- <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" id="fff" placeholder="Search" >
      <button class="btn btn-outline-success my-2 my-sm-0"  onclick="ff()">Search</button>
    </form> -->
    <div>
    	<ul class="nav">
		    <li class="nav-item nav-link"><img  src="/img/279885.jpg" width="30px" height="30px"></li>
		    <li class="nav-item nav-link">Window</li>
		    <li class="nav-item nav-link">图像的</li>
		    <li class="nav-item nav-link">支持</li>
		    <li class="nav-item nav-link"><a style="color:red" href="${url }">${islogin}</a></li>
		</ul>
    </div>
  </div>
</nav>
		

<div class="row">
	<!-- 左侧导航 -->
  <div class="col-1">
    	<div class="row">
		    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
		    	<a href="/home/index" class="nav-link active" style="margin-top:100px;margin-left:50px" id="v-pills-home-tab"  role="tab" aria-controls="v-pills-home" aria-selected="true">推荐</a>
		    	<c:forEach items="${channels }" var="c" varStatus="index">
		     	 	<a href="/home/channel?channelId=${c.id}" class="nav-link" style="margin-top:10px;margin-left:50px" id="v-pills-home-tab"  href="/home/channel?channelId=${c.id}" role="tab" aria-controls="v-pills-home" aria-selected="false">${c.name }</a>
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
    				<c:forEach items="${hotlist}" var="article">
					<div class="row" style="margin-top:5px">
						<div class="col-md-3">
							<img src="/pic/${article.picture}"
							  onerror="this.src='/img/279885.jpg'"
							  class="rounded" style="border-radius:12px!important; width: 150px;height: 60px"
							 >
						</div>
						<div class="col-md-9">
							<a href="/home/detail?id=${article.id}" target="_blank">${article.title}</a>
							<br>
							作者：${article.user.username}
							<br>
							栏目：<a> ${article.channel.name} </a>&nbsp;&nbsp;&nbsp;&nbsp; 分类：<a>${article.category.name}</a>
						</div>
					</div>
				</c:forEach>
    			</div>
    			<!-- 分页开始 -->
					
					
					<div style="margin-left:200px">			
						<div class="row" style="text-align: center;padding-top:1px;" >
							<ul class="pagination" >
								    <li><a href="/home/index?pageNum=${p.prePage}&channelId=${channelId }&catId=${catId }">&laquo;</a></li>
								    <c:forEach begin="${p.pageNum-2 > 1 ? p.pageNum-2:1}" end="${p.pageNum+2 > p.pages ? p.pages:p.pageNum+2}" varStatus="index">    		
								    	<c:if test="${p.pageNum!=index.index}">
								    		<li><a href="/home/index?pageNum=${index.index}&channelId=${channelId }&catId=${catId }">${index.index}</a></li>
								    	</c:if>
								    	<c:if test="${p.pageNum==index.index}">
								    		<li><a href="/home/index?pageNum=${index.index}&channelId=${channelId }&catId=${catId }"><strong> ${index.index} </strong> </a></li>
								    	</c:if>
								    	
								    </c:forEach>
								    <li><a href="/home/index?pageNum=${p.nextPage}&channelId=${channelId }&catId=${catId }">&raquo;</a></li>
								</ul>
						</div>
					</div>
					
    		</div>
    		<div class="col-4">
    			<div class="card">
    				<div class="card-header">
					    	<form class="form-inline my-2 my-lg-0" action="/home/es">
						      <input class="form-control mr-sm-2" type="search" name="title" placeholder="Search" >
						      <input class="btn btn-primary" value="Search" type="submit">
						    </form>
					 </div>
    			</div>
    			<div class="card">
					  <div class="card-header">
					    最新文章
					  </div>
					  <div class="card-body">
					     <ul>
					     	<c:forEach items="${lastlist}" var="last" varStatus="index">
					     		<li class="ex"> ${index.index+1}. <a href="/home/detail?id=${last.id}" target="_blank" >${last.title}</a></li>
					     	</c:forEach>
					     	
					     </ul>
					     <div style="margin-left:200px">			
						<div class="row" style="text-align: center;padding-top:1px;" >
							<ul class="pagination" >
								    <li><a href="/home/index?pageNum=${pageNum}&lastpage=${p1.prePage}">&laquo;</a></li>
								    <c:forEach begin="${p1.pageNum-2 > 1 ? p1.pageNum-2:1}" end="${p1.pageNum+2 > p1.pages ? p1.pages:p1.pageNum+2}" varStatus="index">    		
								    	<c:if test="${p1.pageNum!=index.index}">
								    		<li><a href="/home/index?pageNum=${pageNum}&lastpage=${index.index}">${index.index}</a></li>
								    	</c:if>
								    	<c:if test="${p1.pageNum==index.index}">
								    		<li><a href="/home/index?pageNum=${pageNum}"><strong> ${index.index} </strong> </a></li>
								    	</c:if>
								    	
								    </c:forEach>
								    <li><a href="/home/index?pageNum=${pageNum}&lastpage=${p1.nextPage}">&raquo;</a></li>
								</ul>
						</div>
					</div>
					  </div>
				</div>	
				
					
			  <div class="card" style="margin-top:50px">
					  <div class="card-header">
					    投诉排序
					  </div>
					  <div class="card-body">
					     <ul>
					     	<c:forEach items="${alist}" var="last" varStatus="index">
					     		<li class="ex"> ${index.index+1}. <a href="/home/detail?id=${last.id}" target="_blank" >${last.title}</a></li>
					     	</c:forEach>
					     </ul>
					  </div>
				</div>		
    		</div>
    	</div>
  </div>
</div>
<nav class="nav justify-content-center" style="height:50px; background:	#C0C0C0">
	   <div id="bottom">
	   		
	   </div>
</nav>
</body>
<script type="text/javascript">
$(function(){
	$.post("/link/lList",function(arr){
		for(var i in arr){
			$("#bottom").append("<a style='line-height:50px; font-size:25px; margin-right:50px;' target='_blank' href="+arr[i].url+">"+arr[i].name+"</a>");
		}
	},"json")
})
function gopage(page){
	location="/home/index?lastpage="+page+"&pageNum="+${pageNum};
}

</script>
</html>