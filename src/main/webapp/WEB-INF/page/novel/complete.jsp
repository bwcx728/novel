<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>全本小说-清风小说网</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/footer.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/complete.css" />
</head>
<body>
	<div class="container">
		<jsp:include page="../public/novel_header_nav.jsp"></jsp:include>
		<div class="novelList">
			<table class="table table-bordered table-hover ">
				<thead>
					<tr align="center">
						<th width="15%">文章名称</th>
						<th width="7%">类型</th>
						<th width="40%">最新章节</th>
						<th width="10%">作者</th>
						<th width="5%">字数</th>
						<th width="15%">更新时间</th>
						<th width="8%">状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageInfo.list }" var="novel">
						<tr>
							<td class="odd"><a
								href="${pageContext.request.contextPath }/novel/${novel.id}">${novel.name }</a></td>
							<td class="odd">${novel.type }</td>
							<td class="even"><a href="" target="_blank" >
									${novel.lastUpdateChapter}</a></td>
							<td class="odd">${novel.author }</td>
							<td class="even">${novel.wordage }</td>
							<td class="odd" align="center"><fmt:formatDate
									value="${novel.lastUpdateTime}" type="both" /></td>
							<td class="even" align="center">${novel.status == 1 ? "连载":"完结" }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<footer class="message_footer">
				<nav>
					<ul class="pagination pagenav">
						<li><span aria-hidden="true"><input type="number"
								style="height: 20px;" id="pageNumber" name="pageNumber"
								placeholder="请输入跳转的页码" size=10></span></li>
						<li><a href="completeList?pageNumber=1&fromIndex=0"
							aria-label="Previous"> <span aria-hidden="true">首页</span>
						</a></li>
						<c:choose>
							<c:when test="${1==pageInfo.pageNumber }">
								<li class="disabled"><a aria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="completeList?pageNumber=${pageInfo.pageNumber-1}&fromIndex=${pageInfo.fromIndex}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach items="${pageInfo.pageList }" var="p">
							<c:choose>
								<c:when test="${p==pageInfo.pageNumber }">
									<li class="active"><a>${p }</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="completeList?pageNumber=${p }&fromIndex=${pageInfo.fromIndex}">${p }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pageInfo.pageCount==pageInfo.pageNumber }">
								<li class="disabled"><a aria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="completeList?pageNumber=${pageInfo.pageNumber+1}&fromIndex=${pageInfo.fromIndex}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<li><a
							href="completeList?pageNumber=${pageInfo.pageCount}&fromIndex=${pageInfo.fromIndex}"
							aria-label="Previous"> <span aria-hidden="true">尾页</span>
						</a></li>
						<li><span>共${pageInfo.pageCount}页</span></li>
					</ul>
				</nav>
			</footer>
		</div>
	</div>
	<div class="footer">
		<div class="footer_link"></div>
		<div class="footer_cont">
			<p>本站所有小说为转载作品，所有章节均由网友上传，转载至本站只是为了宣传本书让更多读者欣赏。</p>
		</div>
	</div>
	<script type="text/javascript">
	//分页搜索页码
	$("#pageNumber").keypress(function (e) {
            if (e.which == 13) {
            	var pageNumber = $("#pageNumber").val();
            	if(pageNumber>'${pageInfo.pageCount }'||pageNumber<1){
            		alert("输入有误，请重新输入");
            	}else{
            		var url = "/completeList?pageNumber="+pageNumber+"&fromIndex="+pageNumber;
            		window.location.href=url;
            	}
            }    
	});
	</script>
</body>
</html>
