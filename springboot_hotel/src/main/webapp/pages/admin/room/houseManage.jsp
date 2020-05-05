<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath %>/static/css/style.css" rel="stylesheet" type="text/css" />
<!-- 引入bootstrap分页 -->
<link rel="stylesheet" href="<%=basePath%>/static/js/bootstrap/bootstrap.css" />
<script src="<%=basePath%>/static/js/bootstrap/jquery.min.js"></script>
<script src="<%=basePath%>/static/js/bootstrap/bootstrap.min.js"></script>
<script src="<%=basePath%>/static/js/bootstrap/bootstrap-paginator.js"></script>
<script>
$(function() {
	$('#pagination').bootstrapPaginator({
		bootstrapMajorVersion: 3,
		currentPage: ${requestScope.pageInfo.pageNum },
		totalPages: ${requestScope.pageInfo.pages },
		pageUrl: function(type, page, current) {
			return '/getHouseManage.do?pageNum=' + page;
		},
		itemTexts: function(type, page, current) {
			switch(type) {
				case "first":
					return "首页";
				case "prev":
					return "上一页";
				case "next":
					return "下一页";
				case "last":
					return "末页";
				case "page":
					return page;
			}
		}
	});
});
</script>
<%-- <script type="text/javascript" src="<%=basePath %>/static/js/jquery.js"></script> --%>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

$(".sure").click(function(){
	  $.ajax({
		  	url:"<%=basePath %>/addRoomType.do",
			type:"POST",
			dataType:"json",
			data:{
				roomTypeName:$("input[name=roomTypeName]").val(),
				roomPrice:$("input[name=roomPrice]").val()
			},
			success:function(result){
				if(result){//添加成功
					  $(".tip").fadeOut(100);
					  window.location.reload();
				}else{//添加失败
					 alert("数据添加失败，请重新添加");
				}
			}
	  });
 });

  $(".cancel").click(function(){
	  $(".tip").fadeOut(100);
  });

});
</script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="main.html">首页</a></li>
    <li><a href="#">客房管理</a></li>
    <li><a href="#">客房信息管理</a></li>
  </ul>
</div>
<div class="rightinfo">
  <div class="tools">
    <ul class="toolbar">
      <li class="click"><span><img src="<%=basePath %>/static/images/t01.png" /></span>添加</li>
      <li class="click"><span><img src="<%=basePath %>/static/images/t02.png" /></span>修改</li>
      <li id="batchDel"><span><img src="images/t03.png" /></span>删除</li>
    </ul>
  </div>
  <table class="tablelist">
    <thead>
      <tr>
        <th><input id="selectAll" name="" type="checkbox" value=""/></th>
        <th>编号</th>
        <th>房间类型</th>
        <th>单价</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${requestScope.pageInfo.list }" var="map" varStatus="num">
      	<tr>
	        <td><input name="ck" type="checkbox" value="" /></td>
	        <td>${num.count }</td>
	        <td>${map.room_type_name }</td>
	        <td>${map.room_price}</td>
	      </tr>
      </c:forEach>
    </tbody>
  </table>
  
  <!-- bootstrap的分页 -->
  <!-- 把分页搞出来 -->
  <ul id="pagination"></ul>
  
  <!-- 客房类型添加 -->
  <div class="tip">
    <div class="tiptop"><span>添加客房类型</span><a></a></div>
    <form action="javascript:void(0)">
	    <div class="tipinfo"> 
	      <div class="tipright">
	        <cite>
	        	<table width="360px" height="80px">
	        		<tr>
	        			<td>房间类名：</td>
	        			<td><input style="width:300px;height:35px;border:solid 1px #D7A372" type="text" name="roomTypeName" required="required" pattern="[\u4e00-\u9fa5]{3,10}"  placeholder="请输入3-10位的中文"></input></td>
	        		</tr>
	        		<tr>
	        			<td>房间单价：</td>
	        			<td><input style="width:300px;height:35px;border:solid 1px #D7A372" type="text" name="roomPrice" required="required" pattern="[1-9]\d*.\d*|0.\d*[1-9]\d*"  placeholder="请输入一个正数"></input></td>
	        		</tr>
	        	</table>
	        </cite> 
	       </div>
	    </div>
	    <div class="tipbtn">
	      <input name="" type="submit"  class="sure" value="确定" />
	      &nbsp;
	      <input name="" type="button"  class="cancel" value="取消" />
	    </div>
    </form>
  </div>
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');

	$(function(){
		/* 给选择分类添加事件 */
		$("#condition").change(function(){
			var flag = $("#condition>option:selected").val();
			if(flag==1){//房间类型
				$("#kw>option").remove();
				//触发ajax事件，动态获取数据
				$.ajax({
					url:"<%=basePath %>/getRoomType.do",
					type:"POST",
					dataType:"json",
					success:function(result){
						var content="";
						for(var i in result){
							content+="<option value='"+result[i].id+"'>"+result[i].room_type_name+"</option>";
						}
						$("#kw").append(content);
					}
				});
			}else if(flag==2){//房间状态
				$("#kw>option").remove();
				$("#kw").append("<option value='0'>空闲</option><option value='1'>已入住</option><option value='2'>打扫</option>");
			}
		});
	});
</script>
</body>
</html>
