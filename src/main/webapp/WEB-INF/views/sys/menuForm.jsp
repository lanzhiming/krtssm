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

   

    <title>编辑菜单</title>

    
</head>

<body>

    <c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                   	 编辑菜单
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                	<div class="col-lg-4">
                		<form role="form" action="${ctx}/menu/${action}" method="post" class="form-horizontal">
							<input type="hidden" name="id" value="${menu.id}"/>
							<input type="hidden" name="parentId" value="${menu.parentId}"/>
							<input type="hidden" name="parentIds" value="${menu.parentIds}"/>
								<div class="form-group input-group">
									<span class="input-group-addon">菜单名称：</span>
									<input type="text" value="${menu.name}" name="name" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">菜单链接：</span>
									<input type="text" value="${menu.href}" name="href" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：</span>
									<input type="text" value="${menu.sort}" name="sort" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">权限标识：</span>
									<input type="text" value="${menu.permission}" name="permission" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">是否显示：</span>
									
										<form:radiobuttons path="menu.isShow" items="${menu.isShowDict}"  htmlEscape="false" class="required"/> 
									
									 <%-- <input type="text" value="${menu.href}" name="name" class="form-control" /> --%>
									<%-- 显示: <form:radiobutton path="menu.isShow" value="0"/> <br/>
        							隐藏: <form:radiobutton path="menu.isShow" value="1"/> --%>
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
