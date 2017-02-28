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


    <title>字典类型</title>

</head>

	
<body>
    <!-- <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">用户列表</h1>
        </div>
    </div> -->
    <!-- /.row -->
    <c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
    <div class="row">
        <div class="col-lg-11">
            <div class="panel panel-default">
                <div class="panel-heading">
                    	字典类型
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                	<%-- <a class="btn btn-default"  href="${ctx}/">返回首页</a> --%>
                	<div><a class="btn btn-primary btn-sm" href="${ctx}/dictionaryType/create">新增</a></div>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                	<th>编码</th>
                                    <th>字典名称</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${dictionaryTypes}" var="dictionaryType">
                            		<tr>
                            			<td>${dictionaryType.code}</td>
										<td>${dictionaryType.name}</td>
										<td>${dictionaryType.remark}</td>
										<td>
											<a href="${ctx}/dictionaryType/update/${dictionaryType.id}">编辑</a>
											<a href="${ctx}/dictionaryType/delete/${dictionaryType.id}">删除</a>
											<a href="${ctx}/dictionary/addKeyValue/${dictionaryType.id}">添加键值</a>
										</td>
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
