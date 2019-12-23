<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
<select id="status">
	<option value="0" ${status==0?'selected':'' }>待审核</option>
	<option value="1" ${status==1?'selected':'' }>审核通过</option>
	<option value="2" ${status==2?'selected':'' }>审核被拒</option>
</select>
<input type="button" value="查询" onclick="getstatus()">
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">id</th>
      <th scope="col">标题</th>
      <th scope="col">栏目</th>
      <th scope="col">分类</th>
      <th scope="col">发布时间</th>
      <th scope="col">状态</th>
      <th scope="col">是否热门</th>
      <th scope="col">投诉数</th>
      <th scope="col">操作</th>
    </tr>
  </thead>
  <tbody>
  	
  	<c:forEach items="${list }" var="l">
    <tr>
      <th scope="row">${l.id }</th>
      <td>${l.title }</td>
      <td>${l.channel.name }</td>
      <td>${l.category.name }</td>
      <td><fmt:formatDate value="${l.created }" pattern="yyyy年MM月dd日"/></td>
      <td>
      		<c:choose>
      			<c:when test="${l.status==0 }">待审核</c:when>
      			<c:when test="${l.status==1 }">审核通过</c:when>
      			<c:when test="${l.status==2 }">审核被拒</c:when>
      			<c:otherwise>
      				未知
      			</c:otherwise>
      		</c:choose>
      </td>
      <td>${l.hot==1?"热门":"非热门" }</td>
      <td>${l.complainCnt }</td>
      <td width="300px">
      		<input type="button" value="删除" class="btn btn-danger" onclick="del(${l.id})">
      		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#articleContent" onclick="check(${l.id})">审核</button>
      		<button type="button" class="btn btn-success" data-toggle="modal" data-target="#complainModal" onclick="complainList(${l.id})">管理投诉</button>
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
<!-- 查看投诉 -->	
<div class="modal fade" id="complainModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="complainListDiv">
      	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="setStatus(1)">审核通过</button>
        <button type="button" class="btn btn-primary" onclick="setStatus(2)">审核拒绝</button>
      </div>
    </div>
  </div>
</div>		
<!-- 审核文章 -->	
<div class="modal fade" id="articleContent" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        	<div class="row" id="divTitle"></div>
         	<div class="row" id="divOptions" ></div>
         	<div class="row" id="divContent"></div>	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="setStatus(1)">审核通过</button>
        <button type="button" class="btn btn-primary" onclick="setStatus(2)">审核拒绝</button>
        <button type="button" class="btn btn-primary" onclick="setHot(1)">设置热门</button>
        <button type="button" class="btn btn-primary" onclick="setHot(0)">取消热门</button>
      </div>
    </div>
  </div>
</div>
<script>
	
	$('#articleContent').on('hidden.bs.modal',function (e) {
	  // do something...
		$("#work").load("/admin/article?pageNum="+'${p.pageNum}');
	})
	var global_article_id;

	function gopage(page){
		$("#work").load("/admin/article?pageNum="+page+"&status="+${status});
	}
	function getstatus(){
		$("#work").load("/admin/article?status="+$("#status").val());
	}
	function del(id){
		if(confirm("确认删除?")){
			$.post("/article/del.do",{"id":id},function(i){
				if(i){
					alert("删除成功");
					refreshPage();
				}else{
					alert("删除失败")
				}
			},"json")
		}
	}
	function complainList(id){
		$("#complainModal").modal('show')
		$("#complainListDiv").load("/comment/complains?articleId="+id);
		
	}
	
	function check(id){
		$.post("/admin/getDetail",{id:id},function(msg){
			//文章id保存到全局变量当中
 			global_article_id=msg.data.id;
     		if(msg.code==1){
     			//
     			$("#divTitle").html(msg.data.title);
     			//
     			$("#divOptions").html("栏目：" +msg.data.channel.name + 
     					" 分类："+msg.data.category.name + " 作者：" + msg.data.user.username );
     			//
     			$("#divContent").html(msg.data.content);
     			$('#articleContent').modal('show');
     			
     			return;
     		}
     		alert(msg.error);
     	},"json");
	}
	function setStatus(status){
		var id=global_article_id;
		$.post("/admin/setArticleStatus",{id:id,status:status},function(msg){
			if(msg.code==1){
				alert("操作成功");
				//隐藏模态框
				$('#articleContent').modal("hide"); 
				//刷新页面
				refreshPage();
				return;
			}
			alert(msg.error)
		},"json");
	}
	function setHot(hot){
		var id=global_article_id;
		$.post("/admin/setArticleHot",{id:id,hot:hot},function(msg){
			if(msg.code==1){
				alert("操作成功");
				$('#articleContent').modal('hide'); 
				refreshPage();
				return;
			}
		},"json")
	}
	function refreshPage(){
		$(".modal-backdrop").remove();
		$("#work").load("/admin/article?pageNum="+'${p.pageNum}');
		$("body").removeClass('modal-open');
	}
</script>