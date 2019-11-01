<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册-清风小说网</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<style type="text/css">
.rigister {
	float: right;
	margin-right: 100px;
	width: 450px;
}


</style>
</head>
<body>
	<div class="rigister">
		<div class="modal-content">
			<form action="rigisterUser" autocomplete="off" class="form"
				method="post" enctype="multipart/form-data">
				<div class="modal-header">
					<h4 class="modal-title">个人信息</h4>
				</div>
				<div class="modal-body">
					<table class="table" >
						<thead>
							<tr>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>注册头像:</td>
								<td>
									<div class="col-sm-11">
										<input type="file" class="form-control" name="file" />
									</div>
								</td>
							</tr>
							<tr>
								<td>用户名:</td>
								<td>
									<div class="form-group">
										<div class="col-sm-11">
											<input type="text" class="form-control" title="请输入用户名"
												name="username">
										</div>
										<span style="font-size: 22px; color: red;" class="col-sm-1"></span>
									</div>
								</td>
							</tr>
							<tr>
								<td >手机号:</td>
								<td>
									<div class="form-group">
										<div class="col-sm-11">
											<input type="text" class="form-control" autocomplete="off"
												name="usertel">
										</div>
										<span style="font-size: 22px; color: red;" class="col-sm-1"></span>
									</div>
								</td>
							</tr>
							<tr>
								<td>邮箱:</td>
								<td>
									<div class="form-group">
										<div class="col-sm-11">
											<input type="text" class="form-control" autocomplete="off"
												name="email">
										</div>
										<span style="font-size: 22px; color: red;" class="col-sm-1"></span>
									</div>
								</td>
							</tr>
							<tr>
								<td>密码:</td>
								<td width="80%">
									<div class="form-group">
										<div class="col-sm-11">
											<input type="password" autocomplete='new-password'
												class="form-control" name="password">
										</div>
										<span style="font-size: 22px; color: red;" class="col-sm-1"></span>
									</div>
								</td>
							</tr>
							<tr>
								<td>确认密码:</td>
								<td>
									<div class="form-group">
										<div class="col-sm-11">
											<input type="password" class="form-control"
												autocomplete="off" name="passwordSure">
										</div>
										<span style="font-size: 22px; color: red;" class="col-sm-1"></span>
									</div>
								</td>
							</tr>
							<tr>
								<td>验证码:</td>
								<td>
									<div class="form-group">
										<div class="col-sm-4">
											<input type="text" id="codePage" class="form-control"
												name="code">
										</div>
										<span style="font-size: 22px; color: red;" class="col-sm-1"></span>
										<label class="col-sm-5 control-label" title="看不清"> <img
											class="picture" style="width: 120px; height: 35px;"
											src="ImageServlet" onmouseover="this.style.cursor='hand'"
											onclick="refresh(this)">
										</label>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="javascript:window.history.back(-1);">取消</button>
					<button type="submit" id="updUsersSubmit" class="btn btn-primary">提交</button>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		//验证码局部刷新
		function refresh(obj) {
			obj.src = "ImageServlet?id=" + Math.random();
		}
		$(function() {
			var photo = false;
			var username = false;
			var usertel = false;
			var email = false;
			var code = false;
			var password = false;
			var passwordSure = false;
			//用户名
			$(":text:eq(0)").blur(function() {
				if ($(this).val() == "") {
					$(this).parent().next().html("x");
					username = false;
				} else {
					$(this).parent().next().html("√");
					username = true;
				}
			});
			//手机号
			//手机号正则
			var telPattern = /^1[34578]\d{9}$/;
			$(":text:eq(1)").blur(function() {
				if (telPattern.test($(this).val())) {
					$(this).parent().next().html("√");
					usertel = true;
				} else {
					$(this).parent().next().html("x");
					usertel = false;
				}
			});
			//邮箱
			//Email正则
			var ePattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			$(":text:eq(2)").blur(function() {
				if (ePattern.test($(this).val())) {
					$(this).parent().next().html("√");
					email = true;
				} else {
					$(this).parent().next().html("x");
					email = false;
				}
			});
			//验证码
			var codePage;
			$(":text:eq(3)").blur(function() {
				if ($(this).val() == "") {
					$(this).parent().next().html("x");
					code = false;
				} else {
					codePage = $(this).val();
					//验证码判断是否正确
					$.ajax({
						type : "post",
						data : "codePage=" + codePage,
						url : "code_ajax",
						success : function(data) {
							if (data.result == "success") {
								$("#codePage").parent().next().html("√");
								code = true;
							} else {
								$("#codePage").parent().next().html("x");
								code = false;
							}
						}
					})
				}

			});
			//正则判断密码格式
			$(":password:eq(0)").blur(function() {
				if (!$(this).val().match(/^\w{6,12}$/)) {
					$(this).parent().next().html("x");
					password = false;
				} else {
					$(this).parent().next().html("√");
					password = true;
				}
			});
			//确认密码
			$(":password:eq(1)")
					.blur(
							function() {
								if ($(this).val() == ""
										|| $(this).val() != $(":password:eq(0)")
												.val()) {
									$(this).parent().next().html("x");
									passwordSure = false;
								} else {
									$(this).parent().next().html("√");
									passwordSure = true;
								}
							});
			//提交验证
			$(":submit").click(
					function() {
						if (username == false || usertel == false
								|| email == false || code == false
								|| password == false || passwordSure == false
								|| $(":file").val() == "") {
							alert("请添加完整信息");
							return false;
						}
					});
		});
	</script>
</body>
</html>