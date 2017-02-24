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
	<title>角色分配菜单管理</title>
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/metroStyle/metroStyle.css" rel="stylesheet" type="text/css"/>
</head>

<body>

    <c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div id="message1" class="alert alert-success" style="display: none"></div>
	<p>当前分配菜单的角色为：${role.name}</p>
	<input id="roleid" style="display: none" value="${role.id}"/>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    	角色分配菜单管理
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="form-group">
						<span>菜单权限：</span>
						<div id="ztree" class="ztree"></div>
					</div>
                    <div><a class="btn btn-primary btn-sm" id="allocatedmenusave" href="javascript:">保存分配信息</a>&nbsp;<a class="btn btn-default btn-sm" id="back" href="${ctx }/role">返回</a></div>
	
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
	

	<!-- jQuery -->
    <script src="${ctxStatic}/jquery/dist/jquery.min.js"></script>
    <script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	<script>
		$(document).ready(function(){
	    	var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:true},data:{simpleData:{enable:true,idKey:"id",pIdKey:"parentId",rootPId:'0'}},
					callback:{onClick:function(event, treeId, treeNode){
							var id = treeNode.id == '0' ? '' :treeNode.id;
						}
					}
				};
			var roleid=$("#roleid").val();
			$.getJSON("${ctx}/role/menu/get/"+roleid,function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
			
			
			$("#allocatedmenusave").click(function(){
				var treeObj = $.fn.zTree.getZTreeObj("ztree");
				var nodes = treeObj.getCheckedNodes(true);
				var menuidarray=new Array();
				for(var i=0;i<nodes.length;i++){
					 //if(positionids[i].checked==true){
						 menuidarray.push(nodes[i].id);
						 //index++;
					 //}
				}
				$.ajax({
	                url: "${ctx}/role/allocatedmenusave",
	                type: "POST",  
	               // contentType: "application/json;charset=utf-8",//设置内容的类型
	                //dataType: "json",//设置返回data的类型
				     data:{   //需要传入的参数
						"roleId":roleid,
						"menuids":menuidarray
				     },
	                success: function(data) {
	                   // alert(data);
	                   $("#message1").html("<button data-dismiss=\"alert\" class=\"close\">×</button>分配菜单成功,请先注销后再登陆生效");
	                   $("#message1").css('display','block'); 
	                   //window.history.back();
	                },
	                error: function() {
	                    alert("系统发生异常，请稍候再试！\n\n有任何疑问，请联系系统管理员！");
	                }
	            });
			});
	    });
	</script>
</body>
</html>
