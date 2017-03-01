<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>
</head>
<body>
	<h4>这是首页</h4>
	欢迎您使用本系统！
	<%-- <a href="${ctx}/user">用户列表</a><br>
	<a href="${ctx}/role">角色列表</a><br> --%>
	<a href="${ctx}/logout">shiro安全退出</a><br>
	
</body>
</html>