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
			return '/getUserInfo.do?pageNum=' + page;
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
  $(".tip").fadeOut(100);
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
    <li><a href="#">用户管理</a></li>
    <li><a href="#">用户信息查询</a></li>
  </ul>
</div>
<div class="rightinfo">
  <div class="tools">
    <ul class="toolbar">
      <li class="click"><span><img src="<%=basePath %>/static/images/t01.png" /></span>添加</li>
      <li id="update" class="click"><span><img src="<%=basePath %>/static/images/t02.png" /></span>重置密码</li>
      <li id="batchDel"><span><img src="images/t03.png" /></span>删除</li>
    </ul>
    
    <div class="toolbar1">
      <table>
        <form method="post" name="serch" action="<%=basePath%>/getUserInfo.do">
          <tr>
            <td class="zi"><span>选择分类：</span></td>
            <td>
               <select name="type">
	                <option value="1">用户名</option>
               </select>
            </td>
            <td class="zi"><span>关键字：</span></td>
            <td><input name="keyWord" type="text" placeholder="与分类关联"/></td>
            <td><input type="submit" value="查询" class="button"/></td>
          </tr>
        </form>
      </table>
    </div>
    
  </div>
  <table class="tablelist">
    <thead>
      <tr>
        <th><input id="selectAll" name="" type="checkbox" value=""/></th>
        <th>编号</th>
        <th>用户名</th>
        <th>创建时间</th>
        <th>使用状态</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${requestScope.pageInfo.list }" var="map" varStatus="num">
      	<tr>
	        <td><input isAdmin=${map.is_admin } name="ck" type="checkbox" value="${map.id }" /></td>
	        <td>${num.count }</td>
	        <td>${map.username }</td>
	        <td>${map.create_date }</td>
	        <td>${map.use_status=='1'?'启用':'禁用' }</td>
	        <td>
	        	<c:choose>
	        		<c:when test="${map.use_status=='1' && map.is_admin=='0' }">
		       			<a status="0" userId="${map.id }" flag="operateUseStatus" href="javascript:void(0)" class="tablelink">禁用</a>
	        		</c:when>
	        		<c:when test="${map.is_admin=='1' }">
		       			<a href="javascript:void(0)" class="tablelink">无</a>
	        		</c:when>
	        		<c:otherwise>
	        			<a status="1" userId="${map.id }" flag="operateUseStatus" href="javascript:void(0)" class="tablelink">启用</a>
	        		</c:otherwise>
	        	</c:choose>
	        </td>
	      </tr>
      </c:forEach>
    </tbody>
  </table>
  
  <!-- bootstrap的分页 -->
  <!-- 把分页搞出来 -->
  <ul id="pagination"></ul>
  
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	$(function(){
		/* 修改 */
		$("#update").click(function(){
			var len = $("input[name=ck]:checked").size();
			var userId=$("input[name=ck]:checked").val();
			var admin=$("input[name=ck]:checked").attr("isAdmin");
			if(len==0){
				alert("请选择要修改的数据");
			}else if(len>=2){
				alert("只能单选");
			}else{
				$.ajax({
					url:"<%=basePath %>/resetPwd.do",
					type:"POST",
					data:{
						id:userId,
						isAdmin:admin
					},
					dataType:"JSON",
					success:function(result){
						if(result){//代表操作成功
							if(admin=='1'){//是超级管理员
								alert("密码重置成功")
								window.open("<%=basePath %>pages/admin/login.jsp","_top");
							}else{
								alert("密码重置成功")
							}
						}else{
							alert("密码重置失败");
						}
					}
				});
			}
		});
		
		/* 操作使用状态 */
		$("a[flag=operateUseStatus]").click(function(){
			var userId= $(event.target).attr("userId");
			var status= $(event.target).attr("status");
			$.ajax({
				url:"<%=basePath %>/updateUseStatus.do",
				type:"POST",
				data:{
					id:userId,
					useStatus:status
				},
				dataType:"JSON",
				success:function(result){
					if(result){//代表操作成功
						window.location.reload();
					}else{
						window.alert("操作失败，请您重新试一次!!!");
					}
				}
			});
		});
		
		/* 删除操作 */
		$("#delBtn").click(function(){
			var flag = window.confirm("您确认删除本条记录吗？");
			if(flag){
				var iri = $(this).attr("iri");
				$.ajax({
					url:"<%=basePath %>/delInRoomInfo.do",
					type:"POST",
					data:{id:iri},
					dataType:"JSON",
					success:function(result){
						if(result){//代表删除成功
							window.location.reload();
						}else{
							window.alert("删除失败，请您重新试一次!");
						}
					}
				});
			}
		});
		/* 全选复选框按钮 */
		$("#selectAll").change(function(){
			var flag =  $(this).prop("checked");
			if(flag){//全选
				$("input[name=ck]").prop("checked",true);
			}else{//全部取消
				$("input[name=ck]").prop("checked",false);
			}
		});
		
		/* 批量删除 */
		$("#batchDel").click(function(){
			//先判断有没有被勾选
			var $ckChecked = $("input[name=ck]:checked");
			var len = $ckChecked.size();
			if(len>=1){
				var ids = "";
				$ckChecked.each(function(index,dom){
					var id = $(dom).val();
					ids+=id+",";
				});
				//将多个id传递给后台的controller方法
				$.ajax({
					url:"<%=basePath %>/batchDel.do",
					type:"POST",
					dataType:"JSON",
					data:{idAttr:ids},
					success:function(result){
						if(result){
							window.location.reload();
						}else{
							alert("批量删除失败");
						}
					}
				});
			}else{
				alert("亲，请选择要删除的记录!");
			}
		});
	});
</script>
</body>
</html>
