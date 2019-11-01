<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header row">
	<div class="logo col-md-7 col-xs-3">
		<a href="/" title="清风小说网"><img
			src="${pageContext.request.contextPath }/images/logo.png"></a>
	</div>
	<div class="search col-md-4 col-xs-7">
		<form action="${pageContext.request.contextPath }/searchNovel"
			method="post"
			onsubmit="if($('#fuzzyQuery').val()==''){alert('提示：请输入小说名称或作者名字！');return false;}">
			<div class="input-group">
				<input name="queryType" value="${novelType.id }" hidden="hidden">
				<input type="text" class="form-control" id="fuzzyQuery"
					name="fuzzyQuery" placeholder="小说名称、作者"
					value="${pageInfo.map.fuzzyQuery }"> <span
					class="input-group-btn">
					<button class="btn searchbtn" type="submit"><span style="color: white;">搜索</span></button>
				</span>
			</div>
		</form>
	</div>
	<div class="login col-md-1 col-xs-2">
		<c:choose>
			<c:when test="${sessionScope.user!=null}">
				<a class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"><img
					src="${pageContext.request.contextPath }/${sessionScope.user.photo}" /></a>
				<ul class="dropdown-menu dropdown-menu-left">
					<li><a title="查看或修改个人信息" data-toggle="modal"
						data-target="#seeUserInfo">个人信息</a></li>
					<c:if test="${user.rid==1 }">
						<li><a href="/toAdminIndex">后台管理</a></li>
					</c:if>
					<li><a data-toggle="modal" data-target="#areDeveloping">登录记录</a></li>
					<li><a href="/toLogin"
						onClick="if(!confirm('是否确认退出？'))return false;">退出登录</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath }/toLogin "> <img
					src="${pageContext.request.contextPath }/images/login.png"
					title="登录">
				</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<nav class="header_nav navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#example-navbar-collapse">
			<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="/">首页</a>
	</div>
	<div class="collapse navbar-collapse" id="example-navbar-collapse">
		<ul class="nav navbar-nav">
			<c:forEach items="${typeList}" var="type">
				<li><a
					href="${pageContext.request.contextPath }/type/${type.id}">${type.name}</a></li>
			</c:forEach>
			<li><a href="${pageContext.request.contextPath }/completeList">全本小说</a></li>
			<li><a href="javascript:void(0);" onclick="existUser()">书架</a></li>

		</ul>
	</div>
	<script type="text/javascript">
				function existUser(){
					if(${sessionScope.user != null} ){
					 	window.location.href="${pageContext.request.contextPath }/bookshelfList/"+"${sessionScope.user.bookshelfId}";
					}else{
						window.location.href="${pageContext.request.contextPath }/toLogin";
					}
				} 
			</script>
</nav>

<!--个人信息模态框-->
<div class="modal fade" id="seeUserInfo" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<form action="modifyUser?position=1" method="post">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">个人信息</h4>
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
								<td width="80%"><input type="text" value="${user.id }"
									class="form-control" name="id" maxlength="10"
									autocomplete="off" readonly unselectable="on" /></td>
							</tr>
							<tr>
								<td wdith="20%">头像:</td>
								<td width="80%" height="100px">
									<%-- <div style="width:100px;height:100px;background:url(${pageContext.request.contextPath }/${user.photo }) no-repeat;">
		                <input type="file" name="file" style="opacity:0;width:100px;height:100px;">
                	</div> --%> <img
									style="width: 100px; height: 100px; border-radius: 50%;"
									src="${pageContext.request.contextPath }/${sessionScope.user.photo}"></img>
									<input name="photo" value="${sessionScope.user.photo}" hidden="hidden">
								</td>
							</tr>
							<tr>
								<td wdith="20%">用户名:</td>
								<td width="80%"><input type="text"
									value="${user.username }" class="form-control" name="username"
									maxlength="20" autocomplete="off" readonly unselectable="on" /></td>
							</tr>
							<tr>
								<td wdith="20%">电话:</td>
								<td width="80%"><input type="text" value="${user.usertel }"
									class="form-control" name="usertel" maxlength="13"
									autocomplete="off" /></td>
							</tr>
							<tr>
								<td wdith="20%">邮箱:</td>
								<td width="80%"><input type="text" value="${user.email }"
									class="form-control" name="email" maxlength="20"
									autocomplete="off" /></td>
							</tr>
							<tr>
								<td wdith="20%">旧密码:</td>
								<td width="80%"><input type="password" class="form-control"
									name="password" maxlength="18" autocomplete="off" /></td>
							</tr>
							<tr>
								<td wdith="20%">新密码:</td>
								<td width="80%"><input type="password" class="form-control"
									name="new_pwd" maxlength="18" autocomplete="off" /></td>
							</tr>
							<tr>
								<td wdith="20%">确认密码:</td>
								<td width="80%"><input type="password" class="form-control"
									name="true_pwd" maxlength="18" autocomplete="off" /></td>
							</tr>
						</tbody>
						<tfoot>
							<tr></tr>
						</tfoot>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="submit" id="updUsersSubmit" class="btn btn-primary">提交</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!--提示模态框-->
<div class="modal fade user-select" id="areDeveloping" tabindex="-1"
	role="dialog" aria-labelledby="areDevelopingModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="areDevelopingModalLabel"
					style="cursor: default;">该功能正在日以继夜的开发中…</h4>
			</div>
			<div class="modal-body">
				<img src="${pageContext.request.contextPath }/images/baoman/baoman_01.gif" alt="深思熟虑" />
				<p
					style="padding: 15px 15px 15px 100px; position: absolute; top: 15px; cursor: default;">很抱歉，程序猿正在日以继夜的开发此功能，本程序将会在以后的版本中持续完善！</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">朕已阅</button>
			</div>
		</div>
	</div>
</div>