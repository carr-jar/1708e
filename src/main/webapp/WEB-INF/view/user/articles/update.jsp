<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<link href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.js"></script>  

<form name="articleform"  id="articleform">

  <input type="hidden" name="id" value="${article.id}">
  
  <div class="form-group">
    <label for="title">标题</label>
    <input type="text" class="form-control" id="title" name="title" value="${article.title}" placeholder="请输入文章标题">
  </div>
  
  <div class="form-group">
    <label for="channel">栏目</label>
    <select class="form-control" id="channel" name="channel_id">
      <option value="0">请选择</option>
      <c:forEach items="${channels}" var="cat">
      		<!-- 下拉框的回显 -->	
      		<option value="${cat.id}" ${article.channel.id==cat.id?"selected":""}>${cat.name}</option>	
      </c:forEach>
      
    </select>
  </div>
  
  <div class="form-group">
    <label for="category">分类</label>
    <select class="form-control" id="category" name="category_id">
    </select>
  </div>
  
 <div class="form-group">
    <label for="exampleFormControlFile1">文章图片</label>
    <input type="file" class="form-control-file" id="file" name="file">
    <img alt="未找到" src="${picture }" height="50px" width="50px">
  </div>
  
  <div class="form-group">
    <label for="content1">文章内容</label>
    <textarea name="content1" id="contentId" cols="200" rows="200" style="width:700px;height:200px;visibility:hidden;">${content1}</textarea> 
    
  </div>
  
  <div class="form-group">
  	<input type="button" class="btn btn-primary mb-2" value="提交" onclick="commitArticle()">
  </div> 
</form>
<script>
	
	function channelChange(){

		console.log("选中的数据是 " + $("#channel").val())
		$.post("/article/getCategoris",{cid:$("#channel").val()},
				function(data){
					$("#category").empty();
					for ( var i in data) {
						if(data[i].id=='${article.category.id}'){
							$("#category").append("<option selected value='"+ data[i].id+"'>"+data[i].name+"</option>")	
						}
						else{
							$("#category").append("<option value='"+ data[i].id+"'>"+data[i].name+"</option>")
						}	
					}
		})
	
	}
	
	$("#channel").change(function(){
		channelChange();
		
	})
	

	 $(document).ready( function(){
		 
		 // 执行 栏目下拉框的改变事件
		 channelChange();
		 
		KindEditor.ready(function(K) {
			//    textarea[name="content1"]
			editor = K.create('#contentId', {
			cssPath : '/kindeditor/plugins/code/prettify.css',
			//uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			//uploadJson:'/file/upload.do',
			uploadJson:'/file/uploads.do',
			fileManagerJson:'/file/manager',
			//fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
      
	 }); 
		
	
	  
		// 提交的文章的修改
	  function commitArticle(){
		  
		//  var formdata = new FormData($("#articleform"))
		// 生成formData  异步提交的数据包含附件  
		var formData = new FormData($( "#articleform" )[0]);
		  
		console.log("11111111")
		
		   // 把文章内容存放到formData 中
		  formData.set("content",editor.html());
		  $.ajax({url:"/article/updateArticles",
			  dataType:"json",
			  data:formData,
			  // 让jQuery 不要再提交数据之前进行处理
			  processData : false,
			  // 提交的数据不能加消息头
			  contentType : false,
			  // 提交的方式 
			  type:"post",
			  // 成功后的回调函数
			  success:function(data){
				 showWork("/article/list")
			  }
		 })
		  
	  }
		
</script>