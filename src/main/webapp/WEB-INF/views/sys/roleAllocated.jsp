<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>角色分配用户管理</title>
</head>

<body>

    <c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div id="message1" class="alert alert-success" style="display: none"></div>
	<p>当前分配用户的角色为：${role.name}</p>
	<input id="roleid" style="display: none" value="${role.id}"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    	角色分配用户管理
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead><tr><th>选择</th> <th>用户名</th></tr></thead>
							<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<td><input type="checkbox" name="userid" class="userid" value="${user.id}"/></td>
									<td>${user.username}</td>
								</tr>
							</c:forEach>
							</tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                    <div><a class="btn btn-primary btn-sm" id="allocatedusersave" href="javascript:">保存分配信息</a>&nbsp;<a class="btn btn-default btn-sm" id="back" href="${ctx }/role">返回</a></div>
	
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
	
	
	<!-- <div><input class="btn btn-primary btn-sm" id="allocatedusersave" value="保存分配信息" /><input class="btn btn-sm" id="back" value="返回" /></div><div></div>
	 -->
	<table class="table table-striped table-bordered table-condensed" style="display: none">
		<thead><tr><th>选择</th></tr></thead>
		<tbody>
		<c:forEach items="${alreadyAllocatedUsers}" var="user"><!-- 当前已经分配了的 -->
			<tr>
				<td><input type="checkbox" name="cuserid" class="cuserid" value="${user.id}"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- jQuery -->
    <script src="${ctxStatic}/bower_components/jquery/dist/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			//$("#dept_departmentNo").focus();
			//为inputForm注册validate函数
			//$("#inputForm").validate();
			var deptpositions=document.getElementsByName("cuserid");//当前已经选择了的
			//console.log(deptpositions);
			var positionids=document.getElementsByName("userid");
			for(var i=0;i<positionids.length;i++){
				 for(var j=0;j<deptpositions.length;j++){
					 if(positionids[i].value==deptpositions[j].value){
						 positionids[i].checked=true;
						 continue;
					 }
				 }
			}
			
			//href="${ctx}/dept/allocatedpositionsave"
			$("#back").click(function(){
				window.history.back();
			});
			$("#allocatedusersave").click(function(){
				var departmentid=document.getElementById("roleid").value;
				var positionids=document.getElementsByName("userid");
				var posisionidarray=new Array();
				var index=0;
				for(var i=0;i<positionids.length;i++){
					 if(positionids[i].checked==true){
						 posisionidarray.push(positionids[i].value);
						 //index++;
					 }
				}
				console.log(posisionidarray);
				$.ajax({
	                url: "${ctx}/role/allocatedusersave",
	                type: "POST",  
	               // contentType: "application/json;charset=utf-8",//设置内容的类型
	                //dataType: "json",//设置返回data的类型
				     data:{   //需要传入的参数
						"roleId":departmentid,
						"userids":posisionidarray
				     },
	                success: function(data) {
	                	$("#message1").html("<button data-dismiss=\"alert\" class=\"close\">×</button>分配用户成功,请先注销后再登陆生效");
		                   $("#message1").css('display','block'); 
	                },
	                error: function() {
	                    alert("系统发生异常，请稍候再试！\n\n有任何疑问，请联系系统管理员！");
	                }
	            });
			});
		});
	</script>
</body>
</html>
