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

   

    <title>编辑角色</title>
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>

    
</head>

<body>

    <c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                   	 编辑角色
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                	<div class="col-lg-4">
                		<form role="form" action="${ctx}/role/${action}" method="post" class="form-horizontal">
							<input id="roleid" type="hidden" name="id" value="${role.id}"/>
								<div class="form-group input-group">
									<span class="input-group-addon">角色类型：</span>
									<input type="text" value="${role.roleType}" name="roleType" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">角色名称：</span>
									<input type="text" value="${role.name}" name="name" class="form-control" />
								</div>
								
								<!-- <div class="form-group">
									<span>菜单权限：</span>
									<div id="ztree" class="ztree"></div>
								</div> -->
								
								
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
    
    <!-- jQuery -->
    <script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
    
    <script type="text/javascript">
    
    </script>
</body>

</html>
