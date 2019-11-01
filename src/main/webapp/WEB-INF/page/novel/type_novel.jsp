<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>${novelType.name}-清风小说网</title> 
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
	href="${pageContext.request.contextPath }/css/type_novel.css" />
<style>
.small-bottom{
	overflow: hidden ;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 4;
	-webkit-box-orient: vertical;	
}


</style>
</head>
<body>
	<div class="container">
		<jsp:include page="../public/novel_header_nav.jsp"></jsp:include>
		<div class="main">
			<div class="items">
				<c:forEach items="${novelList }" var="novel" begin="0" end="5"
					step="1">
					<div class="item">
						<div class="cover">
							<a href="${pageContext.request.contextPath }/novel/${novel.id}"><img
								src="${novel.cover }" alt="${novel.author }"></a>
						</div>
						<div class="small">
							<div class="small_top">
								<a href="${pageContext.request.contextPath }/novel/${novel.id}">${novel.name }</a>
								<span>${novel.author }</span>
							</div>
							<span  class="small-bottom">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${novel.introduce }</span>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="footer_link"></div>
		<div class="footer_cont">
			<p>本站所有小说为转载作品，所有章节均由网友上传，转载至本站只是为了宣传本书让更多读者欣赏。</p>
		</div>
	</div>
</body>
</html>
