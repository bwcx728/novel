<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户管理-清风小说网后台管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<c:if test="${msg!=null}">
	<script type="text/javascript">
		window.onload = function() {
			alert("${msg}");
		}
	</script>
	<%
		session.removeAttribute("msg");
	%>
</c:if>
</head>

<body class="user-select">
	<section class="container-fluid">
		<jsp:include page="../public/admin_top.jsp"></jsp:include>
		<div class="row">
			<jsp:include page="../public/admin_left_nav.jsp"></jsp:include>
			<div
				class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main"
				id="main">
				<h1 class="page-header">操作</h1>
				<ol class="breadcrumb">
					<li><a data-toggle="modal" data-target="#addUser">增加用户</a></li>
				</ol>
				<h1 class="page-header">
					管理 <span class="badge">${pageInfo.total }</span>
				</h1>
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th><span class="visible-lg">编号</span></th>
								<th><span class="visible-lg">用户名</span></th>
								<th><span class="visible-lg">手机号</span></th>
								<th><span class="visible-lg">电子邮箱</span></th>
								<th><span class="visible-lg">操作</span></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageInfo.list }" var="u">
								<tr>
									<td>${u.id }</td>
									<td>${u.username }</td>
									<td>${u.usertel }</td>
									<td>${u.email }</td>
									<td><a rel="${u.id }" data-toggle="modal" name="modify"
										data-target="#updUser">修改</a> <a rel="${u.id }" name="delete">删除</a>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="../public/pageInfo.jsp"></jsp:include>
	</section>
	<!--增加用户信息模态框-->
	<div class="modal fade" id="addUser" tabindex="-1" role="dialog"
		aria-labelledby="addUserModalLabel">
		<div class="modal-dialog" role="document" style="max-width: 450px;">
			<form action="insUsers" method="post" autocomplete="off"
				draggable="false">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">增加用户</h4>
					</div>
					<div class="modal-body">
						<table class="table" style="margin-bottom: 0px;">
							<thead>
								<tr>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td wdith="20%">用户名:</td>
									<td width="80%"><input type="text" class="form-control"
										name="username" maxlength="18" autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">电话:</td>
									<td width="80%"><input type="text" value=""
										class="form-control" name="usertel" maxlength="13"
										autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">邮箱:</td>
									<td width="80%"><input type="text" value=""
										class="form-control" id="email" name="email" maxlength="30"
										autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">新密码:</td>
									<td width="80%"><input type="password"
										class="form-control" name="password" maxlength="18"
										autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">确认密码:</td>
									<td width="80%"><input type="password"
										class="form-control" name="new_password" maxlength="18"
										autocomplete="off" /></td>
								</tr>
							</tbody>
							<tfoot>
								<tr></tr>
							</tfoot>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--修改用户信息模态框-->
	<div class="modal fade" id="updUser" tabindex="-1" role="dialog"
		aria-labelledby="updUserModalLabel">
		<div class="modal-dialog" role="document" style="max-width: 450px;">
			<form action="modifyUser?position=2" method="post" autocomplete="off"
				draggable="false">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">修改用户</h4>
					</div>
					<div class="modal-body">
						<table class="table" style="margin-bottom: 0px;">
							<thead>
								<tr>
								</tr>
							</thead>
							<tbody>
								<tr style="display: none;">
									<td wdith="20%">编号:</td>
									<td width="80%"><input type="text" class="form-control"
										id="userid" name="id" maxlength="10" autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">用户名:</td>
									<td width="80%"><input type="text" class="form-control"
										id="username" name="username" maxlength="20"
										autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">电话:</td>
									<td width="80%"><input type="text" value=""
										class="form-control" id="usertel" name="usertel"
										maxlength="13" autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">邮箱:</td>
									<td width="80%"><input type="text" value=""
										class="form-control" id="useremail" name="email"
										maxlength="30" autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">旧密码:</td>
									<td width="80%"><input type="password"
										class="form-control" name="old_password" maxlength="18"
										autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">新密码:</td>
									<td width="80%"><input type="password"
										class="form-control" name="password" maxlength="18"
										autocomplete="off" /></td>
								</tr>
								<tr>
									<td wdith="20%">确认密码:</td>
									<td width="80%"><input type="password"
										class="form-control" name="new_password" maxlength="18"
										autocomplete="off" /></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<input type="hidden" name="userid" value="" />
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../public/admin_bottom.jsp"></jsp:include>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/admin-scripts.js"></script>
	<script>
		//一页展示几条记录数
		function select_jump() {
			//获取被选中的option标签
			var pageSize = $('#sizeSelect  option:selected').val();
			var url = "/showAllUsers?pageSize=" + pageSize;
			window.location.href = url;
		}
		//页面跳转事件
		function a_jump(pageNumber) {
			var url = "/showAllUsers?pageNumber="
					+ pageNumber
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
									var url = "/showAllUsers?pageNumber="
											+ pageNumber
											+ "&fromIndex=${pageInfo.fromIndex}&pageSize=${pageInfo.pageSize}";
									window.location.href = url;
								}
							}
						})
		$(function() {
			$("#main table tbody tr td a").click(function() {
				var name = $(this);
				var id = name.attr("rel"); //对应id   
				if (name.attr("name") === "modify") {
					$.ajax({
						type : "POST",
						url : "/selUsers",
						data : "id=" + id,
						cache : false, //不缓存此页面   
						success : function(data) {
							var data = data["users"];
							$('#userid').val(data.id);
							$('#username').val(data.username);
							$('#usertel').val(data.usertel);
							$('#useremail').val(data.email);
							$('#updUser').modal('show');
						}
					});
				} else if (name.attr("name") === "delete") {
					if (window.confirm("此操作不可逆，是否确认？")) {
						$.ajax({
							type : "POST",
							url : "/delUsers",
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
