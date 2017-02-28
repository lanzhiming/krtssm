<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.page{
    width:200px;
}
.page span{
    margin-left:30px;
}
a{
    text-decoration:none;
}
</style>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript">
    $(function() {
        var $table = $(".goods_table");
        var currentPage = 1;
        var pageSize = 10;
        var sumRows = $table.find("tbody tr").length;
        var sumPages = Math.ceil(sumRows/pageSize);
        
        init();
        paging(currentPage)
        
        $("#prev").click(function(){
            currentPage--;
            init();
            paging(currentPage);
        })
        
        $("#next").click(function(){
            currentPage++;
            init();
            paging(currentPage);
        })
        
        var $page = $("<div class='page'></div>");
        for(var pageIndex=1;pageIndex<=sumPages;pageIndex++){
            $("<a href='#'><span>["+(pageIndex)+"]</span></a>").bind("click",{"newPage":pageIndex},function(event){
                currentPage=event.data["newPage"];
                init();
                paging(currentPage);
            }).appendTo($page);
        }
        $('#whichpage').append($page);
        /*$page.insertAfter($table); */
        
        function paging(currentPage){
            $table.find("tbody tr:not(.prevnext)").hide().slice((currentPage-1)*pageSize,(currentPage)*pageSize).show();
            $("#currentPage").val(currentPage+1);
            $("#currentPage").text(currentPage);
            $("#sumPages").text(sumPages);
        }
        
        function init(){
            if(currentPage==1){
                $("#prev").attr({"disabled":"disabled"});
            }else{
                $("#prev").removeAttr("disabled");
            }
            if(currentPage==sumPages){
                $("#next").attr({"disabled":"disabled"});
            }else{
                $("#next").removeAttr("disabled");
            }
        }
        $("#search").click(function(){
            var name = $("#name").val();
            if(name==""){
                $("#msg").text("未输入检索条件!");
                return;
            }
            $("#form0").submit();
        });
        
    })
</script>
</head>
<body>
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
  				<form action="${ctx}/goodsManage/query" id="form0" method="POST">
               	<div >
               		<input name="name" id="name" type="text"  placeholder="根据商品名称查询">
               		<input type="submit" class="btn btn-primary btn-sm" value="查询"/>
               	<a class="btn btn-primary btn-sm" href="${ctx}/goodsManage/create">新增</a>
               	</div>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover goods_table">
                            <thead>
                                <tr>
                                	<td >id</td>
				                    <td >商品名称</td>
				                    <td >商品价格</td>
				                    <td >商品描述</td>
				                    <td >订单号</td>
				                    <td >操作</td>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="goods" items="${pageTableForm.goodsList}" varStatus="status">
			                    <tr>
			                        <td>${goods.id}</td>
			                        <td>${goods.name}</td>
			                        <td>${goods.price}</td>
			                        <td>${goods.description}</td>
			                        <td>${goods.orderNo}</td>
			                        <td>
										<a href="${ctx}/goodsManage/update/${goods.id}">编辑</a>
										<a href="${ctx}/goodsManage/delete/${goods.id}">删除</a>
									</td>
			                    </tr>
			                </c:forEach>
			                <tr class="prevnext">
			                    <td>
			                        <input id="prev" type="button" value="上一页">
			                    </td>
			                    <td>当前<label id="currentPage"></label>页/共<label id="sumPages"></label>页</td>
			                    <td>
			                        <input id="next" type="button" value="下一页">
			                    </td>
			                    <td id="whichpage">
			                    </td>
			                </tr>
                            </tbody>
                        </table>
                       <!--  <nav aria-label="Page navigation">
						  <ul class="pagination">
						    <li>
						      <a href="#" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <li><a href="#">1</a></li>
						    <li><a href="#">2</a></li>
						    <li><a href="#">3</a></li>
						    <li><a href="#">4</a></li>
						    <li><a href="#">5</a></li>
						    <li>
						      <a href="#" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav> -->
                        <input id="currentPage" type="hidden" name="currentPage" value="${pageTableForm.currentPage}">
                    </div>
                    <!-- /.table-responsive -->
                </form>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>


   <%--  <form action="${ctx}/goodsManage/query" id="form0" method="POST">
        <table>
            <tr><td>name:</td><td><input name="name" id="name" type="text"></td></tr>
        </table>
        <span id="msg" style="color:red;"></span>
        <table class="goods_table" border="1">
            <thead>
                <tr>
                    <td width="60px">id</td>
                    <td width="120px">商品名称</td>
                    <td width="60px">商品价格</td>
                    <td width="60px">商品描述</td>
                    <td width="60px">订单号</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="goods" items="${pageTableForm.goodsList}" varStatus="status">
                    <tr>
                        <td>${goods.id}</td>
                        <td>${goods.name}</td>
                        <td>${goods.price}</td>
                        <td>${goods.description}</td>
                        <td>${goods.orderNo}</td>
                    </tr>
                </c:forEach>
                <tr class="prevnext">
                    <td>
                        <input id="prev" type="button" value="上一页">
                    </td>
                    <td>当前<label id="currentPage"></label>页/共<label id="sumPages"></label>页</td>
                    <td>
                        <input id="next" type="button" value="下一页">
                    </td>
                </tr>
                
            </tbody>
        </table>
        <input id="currentPage" type="hidden" name="currentPage" value="${pageTableForm.currentPage}">
    </form> --%>
</body>
</html>