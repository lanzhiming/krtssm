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

   

    <title>编辑商品</title>

    
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
                   	编辑商品
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                	<div class="col-lg-4">
                		<form role="form" action="${ctx}/goodsManage/${action}" method="post" class="form-horizontal">
								<input type="hidden" name="id" value="${goods.id}"/>
								<div class="form-group input-group">
									<span class="input-group-addon">商品名称：</span>
									<input type="text" value="${goods.name}" name="name" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">商品分类：</span>
									<input type="text" value="${goods.cateId}" name="cateId" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">商品价格：</span>
									<input type="text" value="${goods.price}" name="price" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">商品描述：</span>
									<input type="text" value="${goods.description}" name="description" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">订单号：</span>
									<input type="text" value="${goods.orderNo}" name="orderNo" class="form-control" />
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
