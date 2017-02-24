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


    <title>角色列表</title>


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
                    	角色列表
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                <div><a class="btn btn-primary btn-sm" href="${ctx}/role/create">新增角色</a></div>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>角色类型</th>
                                    <th>角色名称</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${roles}" var="role">
                            		<tr>
										<td><a href="${ctx}/role/update/${role.id}">${role.roleType}</a></td>
										<td>${role.name}</td>
										
										<td><a href="${ctx}/role/allocateduser/${role.id}">分配用户</a>
										<a href="${ctx}/role/allocatedmenu/${role.id}">分配菜单</a>
										<a href="${ctx}/role/delete/${role.id}">删除</a></td>
									</tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                    
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</body>
</html>
