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

   

    <title>编辑用户</title>

    
</head>

<body>

    <!-- /.row -->
    <c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                   	 编辑用户
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                	<div class="col-lg-4">
                		<form role="form" action="${ctx}/user/update" method="post" class="form-horizontal">
							<input type="hidden" name="id" value="${user.id}"/>
								<div class="form-group input-group">
									<span class="input-group-addon">用户名：</span>
									<input type="text" value="${user.username}" name="username" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">密&nbsp;&nbsp; 码：</span>
									<input type="text" value="${user.password}" name="password" class="form-control" placeholder="如果不修改密码此处留空。。"/>
								</div>
								
								<div class="form-actions">
									<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
									<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
								</div>
						</form>
                	</div>
                    
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</body>

</html>
