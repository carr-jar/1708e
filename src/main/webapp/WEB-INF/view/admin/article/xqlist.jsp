<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.js"></script>
<table class="table">
    <tr>
    <th>编号</th>
    <td>${compain.id }</td>
  </tr>
  <tr>
    <th>标题</th>
    <td>${compain.title }</td>
  </tr>
  <tr>
    <th>投诉人</th>
    <td>${compain.username }</td>
  </tr>
  <tr>
    <th>投诉类型</th>
    <td>
    	<c:if test="${compain.complaintype=='A' }">
    			涉及黄色
   		</c:if>
   		<c:if test="${compain.complaintype=='B' }">
   			涉及暴力
   		</c:if>
   		<c:if test="${compain.complaintype=='C' }">
   			涉及宗教政策
   		</c:if>
    </td>
  </tr>
</table>