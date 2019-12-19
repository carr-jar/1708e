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
		    <li class="nav-item nav-link"><a style="color:red" href="/user/tologin.do">退出登录</a></li>
		</ul>
    </div>
  </div>
</nav>
		

<div class="row">
	<!-- 左侧导航 -->
  <div class="col-1">
    	<div class="row">
		    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
		    	<a href="/home/index" class="nav-link ${channelId==0?'active':'' }" style="margin-top:100px;margin-left:50px" id="v-pills-home-tab"  role="tab" aria-controls="v-pills-home" aria-selected="true">推荐</a>
		    	<c:forEach items="${channels }" var="c" varStatus="index">
		     	 	<a href="/home/channel?channelId=${c.id}" class="nav-link ${channelId==c.id?'active':'' }" style="margin-top:10px;margin-left:50px" id="v-pills-home-tab"  href="/home/channel?channelId=${c.id}" role="tab" aria-controls="v-pills-home" aria-selected="false">${c.name }</a>
		      	</c:forEach>
		    </div>
		</div>
  </div>
  <div class="col-11">
    	<div class="row">
    		<div class="col-6">
				
				<!-- 文章列表 -->	
    			<div>
				<ul class="nav nav-pills">
				 	  <li class="nav-item">
						    <a class="nav-link ${catId==0?'active':''}" href="/home/channel?channelId=${channelId}" >全部</a>
				       </li>
					  <c:forEach items="${categoris}" var="cat" >
						  <li class="nav-item">
						    <a class="nav-link ${catId==cat.id?'active':''}"  href="/home/channel?channelId=${channelId}&catId=${cat.id}">${cat.name}</a>
						  </li>
					  </c:forEach>
					  
					</ul>
				</div>
				<div style="margin-top:20px">
				<c:forEach items="${channelList}" var="article">
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
			<div class="row justify-content-center" style="margin-top:20px">
				<nav aria-label="Page navigation example" >
					  <ul class="pagination ">
					  
					    <li class="page-item">
					      <a class="page-link" href="/home/channel?pageNum=${p.pageNum-1}&channelId=${channelId }&catId=${catId }" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    
					    <c:forEach begin="1" end="${p.pages}" varStatus="index">
					    	<li class="page-item"><a class="page-link" href="/home/channel?pageNum=${index.index}&channelId=${channelId }&catId=${catId }"> ${index.index}</a></li>
					    </c:forEach>
					    
					    <li class="page-item">
					      <a class="page-link" href="/home/channel?pageNum=${p.pageNum+1}&channelId=${channelId }&catId=${catId }" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    
					  </ul>
					</nav>
			</div>
    			
    		</div>
    		<div class="col-4">
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
					     <!-- 分页开始 -->
							<div class="row" style="margin-left:50px">
								<nav aria-label="Page navigation example" >
										  <ul class="pagination ">
										  
										    <li class="page-item">
										      <a class="page-link" href="javascript:gopage(${p1.pageNum-1})" aria-label="Previous">
										        <span aria-hidden="true">&laquo;</span>
										      </a>
										    </li>
										    
										    <c:forEach begin="1" end="${p1.pages}" varStatus="index">
										    	
										    	<c:if test="${p1.pageNum==index.index}">
										    		<li class="page-item"><a class="page-link" href="javascript:void()"><font color="red"> ${index.index} </font></a>  </li>
										  		</c:if>
										    </c:forEach>
										    
										    <li class="page-item">
										      <a class="page-link" href="javascript:gopage(${p1.pageNum+1})" aria-label="Next">
										        <span aria-hidden="true">&raquo;</span>
										      </a>
										    </li>
										    
										  </ul>
										</nav>
							</div>
					  </div>
				</div>	
				
					
			  <div class="card" style="margin-top:50px">
					  <div class="card-header">
					    公告
					  </div>
					  <div class="card-body">
					     <ul>
					     	<li>1</li>
					     	<li>2</li>
					     	<li>3</li>
					     </ul>
					  </div>
				</div>		
    		</div>	
    		</div>
  		</div>
</div>
<nav class="nav justify-content-center" style="background:#C0C0C0" height="50px"> 
	     <div id="bottom">
	   		
	   </div>
</nav>
</body>
<script type="text/javascript">
$.post("/link/lList",function(arr){
	for(var i in arr){
		$("#bottom").append("<a style='line-height:50px; font-size:25px; margin-right:50px;' target='_blank' href="+arr[i].url+">"+arr[i].name+"</a>");
	}
},"json")
function gopage(page){
	location="/home/channel?lastpage="+page+"&channelId="+${channelId}+"&catId="+${catId}+"&pageNum="+${pageNum};
}
</script>
</html>