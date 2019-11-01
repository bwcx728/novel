<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>${novel.name }章节列表-清风小说网</title>
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
	href="${pageContext.request.contextPath }/css/novel_chapter.css" />
<style>
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="../public/novel_header_nav.jsp"></jsp:include>
		<div class="book">
			<div class="path">
				<div class="p">
					<a href="/">清风小说网</a> &gt; ${novel.name }
				</div>
			</div>
			<div class="info clearfix">
				<div class="cover">
					<img src="${novel.cover }" alt="${novel.name }">
				</div>
				<div class="small">
					<h4>${novel.name }</h4>
					<div class="book_info">
						<p>
							<span><b>作 者：</b>${novel.author }</span>&nbsp; <span><b>分类：</b>${novel.type }</span>
							<span><b>状态：</b>${novel.status == 1 ? "连载" : "完结" }</span>&nbsp;
							<span><b>字数：</b>${novel.wordage }</span>
						</p>
						<p>
							<span><b>简介：</b></span>${novel.introduce} <br>作者：${novel.author }所写的《${novel.name }》无弹窗免费全文阅读为转载作品,章节由网友发布。
						</p>
						<p>
							<span class="last"><b>最新章节：</b><a href="">${novel.lastUpdateChapter}</a></span>&nbsp;<span
								class="last"><b>更新时间：</b> <fmt:formatDate
									value="${novel.lastUpdateTime}" type="both" /> </span>
						</p>
					</div>

					<div class="book_btn">
						<button class="book-start btn btn-danger" id="novelStart">开始阅读</button>
						<button class="add-book btn btn-info " id="addBookshelf"
							<c:if test="${result>0 }">disabled="disabled" 
					 </c:if>>加入书架</button>
					</div>
				</div>
			</div>
			<div class="listmain">
				<table class="table table-bordered">
					<thead>
						<tr class="bt">
							<td colspan="3">最新十二章章节</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${newChapterLists }" var="chapterList">
							<tr>
								<c:forEach items="${chapterList }" var="chapter">
									<td width="33.3%"><a
										href="${pageContext.request.contextPath }/novel/${novel.id }/${chapter.url }">${chapter.title }</a></td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table class="table table-bordered">
					<thead>
						<tr class="bt">
							<td colspan="3">完整章节</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${chapterLists }" var="chapterList">
							<tr>
								<c:forEach items="${chapterList }" var="chapter">
									<td class="chapter" width="33.3%"><a
										href="${pageContext.request.contextPath }/novel/${novel.id }/${chapter.url }">${chapter.title }</a></td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="footer_link"></div>
		<div class="footer_cont">
			<p>本站所有小说为转载作品，所有章节均由网友上传，转载至本站只是为了宣传本书让更多读者欣赏。</p>
		</div>
	</div>

	<!--增加书架列表模态框-->
	<div class="modal fade" id="addBookshelfList" tabindex="-1"
		role="dialog" aria-labelledby="addBookshelfModalLabel">
		<div class="modal-dialog" role="document" style="max-width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">请选择加入的书架</h4>
				</div>
				<div class="modal-body">
					<ul class="list-group">
						<c:forEach items="${user.bookshelfList }" var="b">
							<li class="list-group-item"><a href="javascript:void(0);"
								onclick="selectBookshelf(${b.id},${novel.id })">${b.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		function selectBookshelf(bid,nid){
			$.ajax({
				type:'post',
				url:'/addNovel',
				data:{"bid":bid,"nid":nid},
				cache:false,
				success:function(){
					alert("添加成功");
					window.location.reload();
				}
			})
		}
		$("#addBookshelf").click(function() {
			if(${user!=null}){ 
				$("#addBookshelfList").modal("show");
			}else{
				alert("请先进行登录");
				window.location.href="${pageContext.request.contextPath}/toLogin";
			}
		})
		$("#novelStart").click(function() {
			var id = $(".chapter").eq(0).find("a").first().attr("href");
			window.location.href="${pageContext.request.contextPath}"+id;
		})
	</script>
</body>
</html>
