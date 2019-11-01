<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录-清风小说网</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="apple-touch-icon-precomposed" href="images/icon/icon.png">
<script src="js/jquery-3.3.1.min.js"></script>
</head>

<body class="user-select">
<div class="container">
  <div class="siteIcon"><a href="/"><img src="images/icon/icon.png" alt="" data-toggle="tooltip" data-placement="top" title="回到首页？" draggable="false" /></a></div>
  <form action="usersLogin" method="post" autocomplete="off" class="form-signin">
    <h2 class="form-signin-heading">用户登录</h2>
    <label for="userName" class="sr-only">用户名</label>
    <input type="text" id="userName" name="username" class="form-control" placeholder="请输入用户名" required autofocus autocomplete="off" maxlength="10">
    <label for="userPwd" class="sr-only">密码</label>
    <input type="password" id="userPwd" name="password" class="form-control" placeholder="请输入密码" required autocomplete="off" maxlength="18">
    <button class="btn btn-lg btn-primary btn-block" type="submit" id="signinSubmit">登录</button>
  </form>
  <div class="footer">
    <p><a href="toRigister" data-toggle="tooltip" data-placement="left" title="没有账号?">注册→</a></p>
  </div>
</div>
<script src="js/bootstrap.min.js"></script> 
<script>
$('[data-toggle="tooltip"]').tooltip();
$('#signinSubmit').click(function(){
	if($('#userName').val() === ''){
		$(this).text('用户名不能为空');
	}else if($('#userPwd').val() === ''){
		$(this).text('密码不能为空');
	}else{
		$(this).text('请稍后...');
	}
});
</script>
</body>
</html>
