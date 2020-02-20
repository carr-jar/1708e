<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
<form id="f4">
<table>
  <tr>
    <th>文章地址</th>
    <th>
    	<input type="text" name="url">
    </th>
  </tr>
  <tr>
    <td>文章标题</td>
    <td>
    	<input type="text" name="name">
    </td>
  </tr>
  <tr>
  	<td>
  		<input type="button" value="添加" onclick="add()">
  	</td>
  </tr>
</table>

</form>
<script type="text/javascript">
function add(){
	var date=$("#f4").serialize();
	$.post("/collect/add",date,function(i){
		if(i){
			alert("添加成功");
		}else{
			alert("url错误");
		}
	},"json")
}
</script>