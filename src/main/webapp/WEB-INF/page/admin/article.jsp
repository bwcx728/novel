<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>小说管理 - 清风小说网后台管理</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin-scripts.js"></script>

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
					<li><a href="#">更新小说列表</a></li>
				</ol>
				<h1 class="page-header">
					管理<span class="badge">${pageInfo.total }</span>
				</h1>
				<nav class="navbar">
					<div class="row">
						<form action="pageNovel" method="post">
							<div class=" col-md-offset-6 col-md-2">
								<select name="queryType" class="form-control center">
									<option value="0">类型</option>
									<c:forEach items="${typeList }" var="type">
										<option
											<c:if test="${type.name == pageInfo.map.queryType }">selected</c:if>
											value="${type.name}">${type.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-4">
								<div class="input-group">
									<input type="text" name="fuzzyQuery"
										value="${pageInfo.map.fuzzyQuery}" class="form-control"
										autocomplete="off" placeholder="可搜书名和作者，请你少字也别输错字"
										maxlength="10"> <span class="input-group-btn">
										<button class="btn btn-default" type="submit">搜索</button>
									</span>
								</div>
							</div>
						</form>
					</div>
				</nav>
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th><span class="visible-lg">选择</span></th>
								<th><span class="visible-lg">作者</span></th>
								<th><span class="visible-lg">文章名称</span></th>
								<th><span class="visible-lg">类型</span></th>
								<th class="hidden-sm"><span class="visible-lg">状态</span></th>
								<th class="hidden-sm"><span class="visible-lg">评论</span></th>
								<th><span class="visible-lg">操作</span></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageInfo.list }" var="novel">
								<tr>
									<td><input type="checkbox" class="input-control"
										name="checkbox[]" value="${novel.id }" />${novel.id }</td>
									<td class="Novel-title">${novel.author }</td>
									<td class="Novel-title">${novel.name }</td>
									<td>${novel.type }</td>
									<td class="hidden-sm">${novel.status == 1 ? "连载":"完结" }</td>
									<td class="hidden-sm">0</td>
									<td><a rel="${novel.id }" class="delete">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<footer class="message_footer">
					<nav>
						<div class="btn-toolbar operation" role="toolbar">
							<div class="btn-group" role="group">
								<a class="btn btn-default" onClick="select()">全选</a> <a
									class="btn btn-default" onClick="reverse()">反选</a> <a
									class="btn btn-default" onClick="noselect()">不选</a>
							</div>
							<div class="btn-group" role="group">
								<button type="submit" class="btn btn-default"
									data-toggle="tooltip" data-placement="bottom" title="删除全部选中"
									name="checkbox_delete" onClick="delcheck()">删除</button>
							</div>
						</div>
						<jsp:include page="../public/pageInfo.jsp"></jsp:include>
					</nav>
				</footer>
			</div>
		</div>
	</section>
	<jsp:include page="../public/admin_bottom.jsp"></jsp:include>
	<script type="text/javascript">
	//一页展示几条记录数
	function select_jump() {
		//获取被选中的option标签
		var pageSize = $('#sizeSelect  option:selected').val();
		var url = "/pageNovel?pageSize=" + pageSize
		+"&fuzzyQuery="+encodeURI(encodeURI("${pageInfo.map.fuzzyQuery}"))
			+"&queryType="+encodeURI(encodeURI("${pageInfo.map.queryType}"));
		window.location.href = url;
	}
	//页面跳转事件
	function a_jump(pageNumber) {
		var url = "/pageNovel?pageNumber="
				+ pageNumber
				+ "&fromIndex=${pageInfo.fromIndex}&pageSize=${pageInfo.pageSize}"
				+"&fuzzyQuery="+encodeURI(encodeURI("${pageInfo.map.fuzzyQuery}"))
	 			+"&queryType="+encodeURI(encodeURI("${pageInfo.map.queryType}"));
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
								var url = "/pageNovel?pageNumber="+pageNumber
										+"&fromIndex=${pageInfo.fromIndex}&pageSize=${pageInfo.pageSize}"
										+"&fuzzyQuery="+encodeURI(encodeURI("${pageInfo.map.fuzzyQuery}"))
			            	 			+"&queryType="+encodeURI(encodeURI("${pageInfo.map.queryType}"));
								window.location.href = url;
							}
						}
					})
	
	
		//文章删除事件
		$(function() {
			$("#main table tbody tr td a").click(function() {
				var name = $(this);
				var id = name.attr("rel"); //对应id  
				if (event.srcElement.outerText == "删除") {
					if (window.confirm("此操作不可逆，是否确认？")) {
						$.ajax({
							type : "POST",
							url : "deleteNovel",
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

		var checkall = document.getElementsByName("checkbox[]");
		//全选
		function select() {
			for (var $i = 0; $i < checkall.length; $i++) {
				checkall[$i].checked = true;
			}
		};
		//反选
		function reverse() {
			for (var $i = 0; $i < checkall.length; $i++) {
				if (checkall[$i].checked) {
					checkall[$i].checked = false;
				} else {
					checkall[$i].checked = true;
				}
			}
		}
		//全不选     
		function noselect() {
			for (var $i = 0; $i < checkall.length; $i++) {
				checkall[$i].checked = false;
			}
		}
		var checkId = [];
		//复选框选中删除事件
		function delcheck() {
			var j = 0;
			for (var $i = 0; $i < checkall.length; $i++) {
				if (checkall[$i].checked == true) {
					checkId[j] = checkall[$i].value;
					j++;
				}
			}
			//判断复选框是否被选择
			if (j > 0) {
				if (window.confirm("此操作不可逆，是否确认？")) {
					$.ajax({
						type : "POST",
						url : "batchDeleteNovel",
						data : {
							checkId : checkId
						},
						cache : false, //不缓存此页面   
						success : function(data) {
							window.location.reload();//重新加载
						}
					});
				}
			} else {
				alert("请选择需要删除的文章！");
			}
		}
	</script>
</body>
</html>
