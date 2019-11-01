<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>小说搜索结果-清风小说网</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/js/gaoliang.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/footer.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/search.css" />
</head>
<body>
	<div class="container">
		<jsp:include page="../public/novel_header_nav.jsp"></jsp:include>
		<div class="novelList">
			<h4>搜索结果</h4>
			<table class="table table-bordered table-hover ">
				<thead>
					<tr align="center">
						<th width="15%">文章名称</th>
						<th width="10%">类型</th>
						<th width="30%">最新章节</th>
						<th width="10%">作者</th>
						<th width="5%">字数</th>
						<th width="20%">更新时间</th>
						<th width="10%">状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageInfo.list }" var="novel">
						<tr>
							<td class="name"><a href="${pageContext.request.contextPath }/novel/${novel.id}">${novel.name }</a></td>
							<td class="type">${novel.type }</td>
							<td class="lastUpdateChapter"><a href="${pageContext.request.contextPath }/novel/${novel.id}">${novel.lastUpdateChapter}</a></td>
							<td class="author">${novel.author }</td>
							<td class="wordage">${novel.wordage }</td>
							<td class="lastUpdateTime" align="center">
								<fmt:formatDate value="${novel.lastUpdateTime}" type="both" /></td>
							<td class="status" align="center">${novel.status == 1 ? "连载":"完结" }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="../public/pageInfo.jsp"></jsp:include>
		</div>
	</div>
	<div class="footer">
		<div class="footer_link"></div>
		<div class="footer_cont">
			<p>本站所有小说为转载作品，所有章节均由网友上传，转载至本站只是为了宣传本书让更多读者欣赏。</p>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			var Keyword = '${pageInfo.map.fuzzyQuery}';
		    $('.name').GL({
		    	 ocolor:'red',   //设置关键词高亮颜色
			     fontSize: 35, //设置字体大小
		         fontWeight:'bold', //设置为粗体
			     oshuru:Keyword //设置要显示的关键词
		    });
		    $('.author').GL({
		        ocolor:'red',   //设置关键词高亮颜色
		        fontSize: 30,
	            fontWeight:'bold',
		        oshuru:Keyword   //设置要显示的关键词
		    });
		})
		//一页展示几条记录数
	function select_jump() {
		//获取被选中的option标签
		var pageSize = $('#sizeSelect  option:selected').val();
		var url = "/searchNovel?pageSize=" + pageSize
		+"&fuzzyQuery="+encodeURI(encodeURI("${pageInfo.map.fuzzyQuery}"))
			+"&queryType="+encodeURI(encodeURI("${pageInfo.map.queryType}"));
		window.location.href = url;
	}
	//页面跳转事件
	function a_jump(pageNumber) {
		var url = "/searchNovel?pageNumber="
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
								var url = "/searchNovel?pageNumber="+pageNumber
										+"&fromIndex=${pageInfo.fromIndex}&pageSize=${pageInfo.pageSize}"
										+"&fuzzyQuery="+encodeURI(encodeURI("${pageInfo.map.fuzzyQuery}"))
			            	 			+"&queryType="+encodeURI(encodeURI("${pageInfo.map.queryType}"));
								window.location.href = url;
							}
						}
					})
	</script>
</body>
</html>
