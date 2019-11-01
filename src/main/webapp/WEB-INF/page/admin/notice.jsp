<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>公告管理 - 清风小说网后台管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<style type="text/css">
.pagenav ul li{
	background: red;
}

</style>
</head>


<body class="user-select">
	<section class="container-fluid">
		<jsp:include page="../public/admin_top.jsp"></jsp:include>
		<div class="row">
			<jsp:include page="../public/admin_left_nav.jsp"></jsp:include>
			<div
				class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main"
				id="main">
				<form action="/Article/checkAll" method="post">
					<h1 class="page-header">操作</h1>
					<ol class="breadcrumb">
						<li><a data-toggle="modal" data-target="#addNotice">增加公告</a></li>
					</ol>
					<h1 class="page-header">
						管理 <span class="badge">${pageInfo.total }</span>
					</h1>
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th><span class="visible-lg">编号</span></th>
									<th><span class="visible-lg">内容</span></th>
									<th><span class="visible-lg">日期</span></th>
									<th><span class="visible-lg">操作</span></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pageInfo.list}" var="notice">
									<tr>
										<td>${notice.id }</td>
										<td class="article-title">${notice.content }</td>
										<td><fmt:formatDate value="${notice.releaseTime}"
												type="both" /></td>
										<td><a rel="${notice.id }">删除 </a>&nbsp;<a
											data-nid="${notice.id }" data-toggle="modal"
											data-target="#updNotice">修改</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<jsp:include page="../public/pageInfo.jsp"></jsp:include>
		</div>
	</section>
	<!--增加公告信息模态框-->
	<div class="modal fade" id="addNotice" tabindex="-1" role="dialog"
		aria-labelledby="addNoticeModalLabel">
		<div class="modal-dialog" role="document" style="max-width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						增加公告 <span class="badge">${noticeTotal }</span>
					</h4>
				</div>
				<div class="modal-body">
					<textarea class="content" name="content" rows="2" cols="35"
						autofocus maxlength="40" style="resize: none;"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="insertNotice" class="btn btn-primary">提交</button>
				</div>
			</div>
		</div>
	</div>
	<!--修改公告模态框-->
	<div class="modal fade" id="updNotice" tabindex="-1" role="dialog"
		aria-labelledby="updNoticeModalLabel">
		<div class="modal-dialog" role="document" style="max-width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">修改公告</h4>
				</div>
				<div class="modal-body">
					<input type="text" class="form-control" checked="checked"
						id="updName" maxlength="10">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="updNotice" class="btn btn-primary">提交</button>
				</div>
			</div>
		</div>
	</div>
 
	<jsp:include page="../public/admin_bottom.jsp"></jsp:include>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/admin-scripts.js"></script>
	<script>
		//控制器中请求映射路径
		var RequestMapping="/pageNotice";
		//一页展示几条记录数
		function select_jump() {
			//获取被选中的option标签
			var pageSize = $('#sizeSelect  option:selected').val();
			var url = RequestMapping+"?pageSize=" + pageSize;
			window.location.href = url;
		}
		//页面跳转事件
		function a_jump(pageNumber) {
			var url = RequestMapping+"?pageNumber="+ pageNumber
					+ "&fromIndex=${pageInfo.fromIndex}&pageSize=${pageInfo.pageSize}";
			window.location.href = url;
		}
		//分页搜索页码 
		$("#pageNumber")
				.keypress(
						function(e) {
							if (e.which == 13) {
								var pageNumber = $("#pageNumber").val();
								if (pageNumber > '${pageInfo.pageCount}'
										|| pageNumber < 1) {
									alert("输入有误，请重新输入");
								} else {
									var url = RequestMapping+"?pageNumber=" + pageNumber
											+ "&fromIndex=${pageInfo.fromIndex}&pageSize=${pageInfo.pageSize}";
									window.location.href = url;
								}
							}
						})
		$("#insertNotice").click(function() {
			var content = $(".content").val();
			if (content) {
				$.ajax({
					url : "/addNotice",
					type : "post",
					data : "content=" + content,
					success : function(data) {
						window.location.reload();
					}
				})
			} else {
				alert("请输入公告内容!");
			}
		})

		//修改id
		var id;
		$("#updNotice").on("show.bs.modal", function(e) {
			id = $(e.relatedTarget).data("nid");
		})
		$("#updNotice").click(function() {
			var name = $("#updName").val();
			if (name) {
				$.ajax({
					url : "/updNotice",
					type : "post",
					data : {
						'content' : name,
						"id" : id
					},
					success : function(data) {
						window.location.reload();
					}
				})
			}
		})

		//是否确认删除
		$(function() {
			$("#main table tbody tr td a").click(function() {
				var name = $(this);
				var id = name.attr("rel"); //对应id  
				if (event.srcElement.outerText == "删除") {
					if (window.confirm("此操作不可逆，是否确认？")) {
						$.ajax({
							type : "POST",
							url : "/delNotice",
							data : "id=" + id,
							cache : false, //不缓存此页面   
							success : function(data) {
								window.location.reload();
							}
						});
					}
					;
				}
				;
			});
		});
	</script>
</body>
</html>
