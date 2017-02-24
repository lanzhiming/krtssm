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
                	<a class="btn btn-default"  href="${ctx}/">返回首页</a>
                	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal">
					  新增
					</button>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                	<th>ID</th>
                                    <th>角色名称</th>
                                    <th>角色类型</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${roles}" var="role">
                            		<tr>
                            			<td>${role.id}</td>
										<td>${role.name}</td>
										<td>${role.roleType}</td>
										<td>
											<a href="javascript:;" onclick="$('#myModalLabel').val('编辑');$('#username').val('${user.username}');$('#addModal').modal({show:true});">编辑</a>
											<a href="${ctx}/role/delete/${role.id}">删除</a>
										</td>
									</tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                    <!-- Modal -->
					<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">新增</h4>
					      </div>
					      <div class="modal-body">
					        <form id="addForm" role="form" action="${ctx}/user/update" method="post" class="form-horizontal">
								<input type="hidden" name="id" id="id" value="${user.id}"/>
									<div class="form-group input-group">
										<span class="input-group-addon">用户名：</span>
										<input type="text" value="${user.username}" id="username" name="username" class="form-control" />
									</div>
									<div class="form-group input-group">
										<span class="input-group-addon">密&nbsp;&nbsp; 码：</span>
										<input type="text" value="${user.password}" id="password" name="password" class="form-control" />
									</div>
									
									<!-- <div class="form-actions">
										<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
										<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
									</div> -->
							</form>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <button id="submit_btn" type="button" class="btn btn-primary" onclick="$('#addForm').submit();">确定</button>
					      </div>
					    </div>
					  </div>
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
