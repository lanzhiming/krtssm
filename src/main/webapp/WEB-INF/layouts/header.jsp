<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
 <!--顶部-->
    <div class="layout_top_header">
           <div style="float: left"><span style="font-size: 16px;line-height: 45px;padding-left: 20px;color: #8d8d8d">用户权限管理后台</h1></span></div>
           <div id="ad_setting" class="ad_setting">
           
           <div class="dropdown">
			  <%-- <button id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			    <span><% out.println(SecurityUtils.getSubject().getPrincipal()); %></span>
			    <span class="caret"></span>
			  </button> --%>
			  <a id="drop3" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                <span>欢迎您，<% out.println(SecurityUtils.getSubject().getPrincipal()); %></span>
                <span class="caret"></span>
              </a>
			  <ul class="dropdown-menu" aria-labelledby="drop3">
			       <li class="ad_setting_ul_li"> <a href="${ctx}/"><i class="icon-user glyph-icon"></i>首页 </a> </li>
                   <li class="ad_setting_ul_li"> <a href="javascript:;"><i class="icon-cog glyph-icon"></i> 设置 </a> </li>
                   <li class="ad_setting_ul_li"> <a href="${ctx}/logout"><i class="icon-signout glyph-icon"></i> <span class="font-bold">退出</span> </a> </li>
			  </ul>
			</div>
               <%-- <a class="ad_setting_a dropdown" href="javascript:; " id="dLabel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                   <i class="icon-user glyph-icon" style="font-size: 20px"></i>
                   <span><% out.println(SecurityUtils.getSubject().getPrincipal()); %></span>
                   <i class="icon-chevron-down glyph-icon"></i>
               </a>
               <ul aria-labelledby="dLabel" class="dropdown-menu-uu dropdown-menu"  id="ad_setting_ul">
                   <li class="ad_setting_ul_li"> <a href="${ctx}/"><i class="icon-user glyph-icon"></i>首页 </a> </li>
                   <li class="ad_setting_ul_li"> <a href="javascript:;"><i class="icon-cog glyph-icon"></i> 设置 </a> </li>
                   <li class="ad_setting_ul_li"> <a href="${ctx}/logout"><i class="icon-signout glyph-icon"></i> <span class="font-bold">退出</span> </a> </li>
               </ul> --%>
           </div>
    </div>
    <!--菜单-->
   <!--  <div id="layout_right_content" class="layout_right_content">

        <div class="route_bg">
            <a href="#">主页</a><i class="glyph-icon icon-chevron-right"></i>
            <a href="#">菜单管理</a>
        </div>
        <div class="mian_content">
            <div id="page_content">
                <iframe id="menuFrame" name="menuFrame" src="main.html" style="overflow:visible;"
                        scrolling="yes" frameborder="no" width="100%" height="100%"></iframe>
            </div>
        </div>
    </div> -->



<%-- <div id="header">
	<div id="title">
	    <h1><a href="${ctx}">QuickStart示例</a><small>--TodoList应用演示</small>
	    <shiro:user>
			<div class="btn-group pull-right">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<i class="icon-user"></i> <shiro:principal property="name"/>
					<span class="caret"></span>
				</a>
			
				<ul class="dropdown-menu">
					<shiro:hasRole name="admin">
						<li><a href="${ctx}/admin/user">Admin Users</a></li>
						<li class="divider"></li>
					</shiro:hasRole>
					<li><a href="${ctx}/api">APIs</a></li>
					<li><a href="${ctx}/profile">Edit Profile</a></li>
					<li><a href="${ctx}/logout">Logout</a></li>
				</ul>
			</div>
		</shiro:user>
		</h1>
	</div>
</div> --%>