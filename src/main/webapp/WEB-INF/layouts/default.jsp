<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>用户管理后台<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/css/index.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/bootstrap/dist/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/tendina.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script> --%>
<script type="text/javascript" src="${ctx}/static/bootstrap/dist/js/bootstrap.min.js"></script>

<style type="text/css">
.slideMenu{
	width:200px; 
	float:left;
}
#content{
	display: block;
    width:100%;
    padding-left:220px;
    padding-top:50px;
}
.accordion {
 	width: 100%;
 	max-width: 360px;
 	margin: 30px auto 20px;
 	background: #FFF;
 	-webkit-border-radius: 4px;
 	-moz-border-radius: 4px;
 	border-radius: 4px;
 }

.accordion .link {
	cursor: pointer;
	display: block;
	padding: 15px 15px 15px 42px;
	color: #4D4D4D;
	font-size: 14px;
	font-weight: 700;
	border-bottom: 1px solid #CCC;
	position: relative;
	-webkit-transition: all 0.4s ease;
	-o-transition: all 0.4s ease;
	transition: all 0.4s ease;
}

.accordion li:last-child .link {
	border-bottom: 0;
}

.accordion li i {
	position: absolute;
	top: 16px;
	left: 12px;
	font-size: 18px;
	color: #595959;
	-webkit-transition: all 0.4s ease;
	-o-transition: all 0.4s ease;
	transition: all 0.4s ease;
}

.accordion li i.fa-chevron-down {
	right: 12px;
	left: auto;
	font-size: 16px;
}

.accordion li.open .link {
	color: #b63b4d;
}

.accordion li.open i {
	color: #b63b4d;
}
.accordion li.open i.fa-chevron-down {
	-webkit-transform: rotate(180deg);
	-ms-transform: rotate(180deg);
	-o-transform: rotate(180deg);
	transform: rotate(180deg);
}

/**
 * Submenu
 -----------------------------*/
 .submenu {
 	display: none;
 	background: #444359;
 	font-size: 14px;
 }

 .submenu li {
 	border-bottom: 1px solid #4b4a5e;
 }

 .submenu a {
 	display: block;
 	text-decoration: none;
 	color: #d9d9d9;
 	padding: 12px;
 	padding-left: 42px;
 	-webkit-transition: all 0.25s ease;
 	-o-transition: all 0.25s ease;
 	transition: all 0.25s ease;
 }

 .submenu a:hover {
 	background: #b63b4d;
 	color: #FFF;
 }

</style>
<script>
$(function() {
	function edit(user){
		alert('aa');
		$('#myModalLabel').text('编辑');
		$('#username').val(user.username);
		$('#password').val(user.password);
		$('#addModal').modal({
			  show: true
		});
	};
	
	var Accordion = function(el, multiple) {
		this.el = el || {};
		this.multiple = multiple || false;

		// Variables privadas
		var links = this.el.find('.link');
		// Evento
		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
	}

	Accordion.prototype.dropdown = function(e) {
		var $el = e.data.el;
			$this = $(this),
			$next = $this.next();

		$next.slideToggle();
		$this.parent().toggleClass('open');

		if (!e.data.multiple) {
			$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
		};
	}	

	var accordion = new Accordion($('#accordion'), false);
});
</script>
<sitemesh:head/>
</head>


<body>
	<div class="container">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div class="slideMenu col-lg-2">
			<ul id="accordion" class="accordion">
			<li>
				<div class="link"><i class="fa fa-paint-brush"></i>用户管理<i class="fa fa-chevron-down"></i></div>
				<ul class="submenu">
					<li><a href="${ctx}/user">用户列表</a></li>
				</ul>
			</li>
			<li>
				<div class="link"><i class="fa fa-code"></i>角色管理<i class="fa fa-chevron-down"></i></div>
				<ul class="submenu">
					<li><a href="${ctx}/role">角色列表</a></li>
				</ul>
			</li>
			<li>
				<div class="link"><i class="fa fa-code"></i>菜单管理<i class="fa fa-chevron-down"></i></div>
				<ul class="submenu">
					<li><a href="${ctx}/menu">菜单列表</a></li>
				</ul>
			</li>
		</ul>
		</div>
		<div id="content">
			<sitemesh:body/>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
</body>
</html>