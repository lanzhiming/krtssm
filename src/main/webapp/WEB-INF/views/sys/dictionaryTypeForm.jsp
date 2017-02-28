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

   

    <title>添加字典类型</title>

    
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
                   	添加字典类型
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                	<div class="col-lg-4">
                		<form role="form" action="${ctx}/dictionaryType/${action} " method="post" class="form-horizontal">
								<input type="hidden" name="id" value="${dictionaryType.id}"/>
								<div class="form-group input-group">
									<span class="input-group-addon">字典名称：</span>
									<input type="text" value="${dictionaryType.name}" name="name" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">字典编码：</span>
									<input type="text" value="${dictionaryType.code}" name="code" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">备注：</span>
									<input type="text" value="${dictionaryType.remark}" name="remark" class="form-control" />
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
