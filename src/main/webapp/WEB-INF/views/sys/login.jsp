<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>登录页</title>
<link href="${ctx}/static/css/add.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/bootstrap/dist/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="div_from_aoto" style="width: 500px;">
	<h2>用户登录</h2>
	<br>
    <FORM id="loginForm" action="${ctx}/login" method="post">
        <DIV class="control-group">
            <label class="laber_from">用户名</label>
            <DIV  class="controls" ><INPUT class="input_from" type=text id="username" name="username" placeholder=" 请输入用户名"><P class=help-block></P></DIV>
        </DIV>
        <DIV class="control-group">
            <LABEL class="laber_from">密码</LABEL>
            <DIV  class="controls" ><INPUT class="input_from" type=password id="password" name="password" placeholder=" 请输入密码"><P class=help-block></P></DIV>
        </DIV>
        <DIV class="control-group">
        	<div class="checkbox">
	            <label >
	               <input type="checkbox" id="rememberMe" name="rememberMe" style="height:20px;"> 请记住我
	            </label>
	         </div>
        </DIV>
        <DIV class="control-group">
            <LABEL class="laber_from" ></LABEL>
            <DIV class="controls" >
            <button id="btnAjaxForm" type="submit" class="btn btn-info" style="width:120px;">登录</button>
            <!-- <button class="btn btn-success" style="width:120px;" >确认</button> -->
            </DIV>
        </DIV>
    </FORM>
</div>


	<%-- <form id="loginForm" class="form-horizontal" role="form" action="${ctx}/login"
		method="post">
		<div class="form-group">
	      <label for="username" class="col-sm-2 control-label">用户名</label>
	      <div class="col-sm-2">
	         <input type="text" class="form-control" id="username" name="username"
	            placeholder="请输入用户名">
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="password" class="col-sm-2 control-label">密码</label>
	      <div class="col-sm-2">
	         <input type="text" class="form-control" id="password" name="password"
	            placeholder="请输入密码">
	      </div>
	   </div>
	   
	   <div class="form-group">
	      <div class="col-sm-offset-2 col-sm-2">
	         <div class="checkbox">
	            <label>
	               <input type="checkbox" id="rememberMe" name="rememberMe"> 请记住我
	            </label>
	         </div>
	      </div>
	   </div>
	   <div class="form-group">
	      <div class="col-sm-offset-2 col-sm-2">
	         <button id="btnAjaxForm" type="submit" class="btn btn-info">登录</button>
	      </div>
	   </div>
	   
	   
	</form> --%>
	<label id="responseText">${status}</label>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var options = {
			success : function(data) {
				console.log("异步登陆成功");
				$("#responseText").text("异步登陆成功,跳转中。。。");
				if(data){
					window.location.reload();
				}else{
					console.log("登陆失败");
				}
				
			}
		};
		
		//$("#loginForm").ajaxForm(options);
	});
</script>
</html>