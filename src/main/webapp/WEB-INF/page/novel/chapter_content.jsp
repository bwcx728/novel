<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${chapterDetail.title }-清风小说网</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/footer.css" />
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/chapter_content.css" />
</head>


<body>
	<div class="container">
		<jsp:include page="../public/novel_header_nav.jsp"></jsp:include>
		<div class="main">
			<div class="content_read">
				<div class="box_con">
					<div class="con_top">
						<a href="/">清风小说网</a> &gt; <a href="${pageContext.request.contextPath }/novel/${novel.id }">${novel.name }</a>
						&gt; ${chapterDetail.title }
					</div>
					<div class="bookname">
						<h1>${chapterDetail.title }</h1>
						<div class="bottem1">
							<a class="btn btn-info" href="${pageContext.request.contextPath }/novel/${novel.id }/${chapterDetail.prev }">上一章</a>
							← <a class="btn btn-success" href="${pageContext.request.contextPath }/novel/${novel.id }">章节目录</a>
							→ <a class="btn btn-info" href="${pageContext.request.contextPath }/novel/${novel.id }/${chapterDetail.next }">下一章</a>
						</div>
					</div>
					<div id="content">${chapterDetail.content }</div>
					<div class="bottem2">
						<a class="btn btn-info" href="${pageContext.request.contextPath }/novel/${novel.id }/${chapterDetail.prev }">上一章</a>
						← <a class="btn btn-primary" href="${pageContext.request.contextPath }/novel/${novel.id }">章节目录</a>
						→ <a class="btn btn-info" href="${pageContext.request.contextPath }/novel/${novel.id }/${chapterDetail.next }">下一章</a>
					</div>
				</div>
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
