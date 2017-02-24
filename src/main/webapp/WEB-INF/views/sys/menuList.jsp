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


    <title>菜单列表</title>
	<link href="${ctxStatic}/treeTable/themes/vsStyle/treeTable.css" rel="stylesheet">
	<link href="${ctxStatic}/treeTable/themes/default/treeTable.css" rel="stylesheet">

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
                    	菜单列表
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover" id="treeTableMenu">
                            <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>链接</th>
                                    <th>排序</th>
                                    <th>权限标识</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%-- <c:forEach items="${roles}" var="role">
                            		<tr>
										<td><a href="${ctx}/role/update/${role.id}">${role.roleType}</a></td>
										<td>${role.name}</td>
										
										<td><a href="${ctx}/role/allocateduser/${role.id}">分配用户</a>
										<a href="${ctx}/role/delete/${role.id}">删除</a></td>
									</tr>
                            	</c:forEach> --%>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                    <%-- <div><a class="btn btn-primary btn-sm" href="${ctx}/role/create">新增角色</a></div> --%>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    
    <!-- jQuery -->
    <script src="${ctxStatic}/jquery/dist/jquery.min.js"></script>
    <script src="${ctxStatic}/treeTable/jquery.treeTable.js"></script>
    
    <script type="text/javascript">
	    $(document).ready(function(){
	    	//获取树
			$.ajax({
				type: 'GET',
				contentType: 'application/json', 
				url: "${ctx}/menu/get",
				dataType: 'json',
				async:false,
				success: function(data) {
					var html="";
					for(var i=0;i<data.length;i++){
						
					html=html+'<tr id="'+data[i].id+'" pId="'+data[i].parentId+'">'
					     	    +'<td>'+data[i].name+'</td><td>'+data[i].href+'</td>'
					            +'<td>'+data[i].sort+'</td><td>'+data[i].permission+'</td>'
					            +"<td><a href='${ctx}/menu/create/"+data[i].id+"'>添加下级</a>  <a href='${ctx}/menu/update/"+data[i].id+"'>修改</a>  <a href='${ctx}/menu/delete/"+data[i].id+"'>删除</a></td>"
					          +'</tr>';
					}
					
					$("tbody").html('');
					$("tbody").html(html);
					
				},
				error: function(req, textStatus, errorThrown){
					 
				}
			});
	    	var option = {
                theme:'default',
                expandLevel : 3,
                beforeExpand : function($treeTable, id) {
                    //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                    if ($('.' + id, $treeTable).length) { return; }
                   /*  //这里的html可以是ajax请求
                    var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
                             + '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';

                    $treeTable.addChilds(html); */
                },
                onSelect : function($treeTable, id) {
                    window.console && console.log('onSelect:' + id);
                    
                }

            };
	        $('#treeTableMenu').treeTable(option).show();
	    });
    </script>
</body>
</html>
