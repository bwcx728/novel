<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>书架-清风小说网</title>
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
	href="${pageContext.request.contextPath }/css/bookshelf.css" />
<script src="${pageContext.request.contextPath }/js/iconfont.js"></script>
<style type="text/css">
	.no-data{
	margin-top:50px;
	width:100%;
	text-align:center;
	font-size:30px;
	color:#cdcdcd;
}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="../public/novel_header_nav.jsp"></jsp:include>
		<div class="main">
			<div class="bookshelfList">
				<ul>
					<li><a href="javascript:;">分组</a> <a data-toggle="modal"
						data-target="#addBookshelf"> <svg class="icon"
								aria-hidden="true">
								<use xlink:href="#icon-tianjia"></use>
							</svg>
					</a></li>
					<c:forEach items="${bookshelfList }" var="b">
						<li<c:if test='${bookshelf.id == b.id}'> class="active"</c:if>>
							<a href="/bookshelfList/${b.id }">${b.name }</a> 
							<a href="javascript:void(0);" class="updId" data-bid="${b.id }"
								data-toggle="modal" data-target="#updBookshelf"> 
								<svg class="icon" aria-hidden="true">
								 	<use xlink:href="#icon-xiugai"></use>
								</svg>
							</a>
							<a href="javascript:void(0);" onclick="delBook(${b.id})"> 
					 			<svg class="icon" aria-hidden="true">
									<use xlink:href="#icon-shanchu"></use>
								</svg>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="novelList">
				<h3>${bookshelf.name }</h3>
				<hr>
				<c:if test="${emptyNovelList}">
					<div id="limit-list">
						<ul>
							<c:forEach items="${bookshelf.novelList }" var="novel">
								<li>
									<div class="book-img-box">
										<a href="" target="_blank"><img src="${novel.cover }"></a>
									</div>
									<div class="book-mid-info">
										<h4>
											<a href="${novel.url }" target="_blank">${novel.name }</a>
										</h4>
										<p class="author">
											<img
												src="${pageContext.request.contextPath }/images/icon/author.png"><span>${novel.author }</span><em>|</em><span>${novel.status == 1 ? "连载" : "完结" }</span><em>|</em><span>${novel.wordage }</span>
										</p>
										<p class="intro">${novel.introduce }</p>
										<p class="update">
											<a
												href="${pageContext.request.contextPath }/novel/${novel.id}"
												target="_blank">${novel.lastUpdateChapter }</a><em>·</em><span>
												<fmt:formatDate value="${novel.lastUpdateTime}" type="both" />
											</span>
										</p>
									</div>
									<div class="book-right-info">
										<p class="btn">
											<a class="book-details"
												href="${pageContext.request.contextPath }/novel/${novel.id}"
												>书籍详情</a> <a class="remove-book"
												href="javascript:void(0);"
												onclick="delBookshelf(${bookshelf.id},${novel.id })">移出书架</a>
										</p>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<c:if test="${!emptyNovelList}">
					<div class="no-data">
                        <div class="no-data-img">
                        	<img src="${pageContext.request.contextPath }/images/icon/shuji.png">
                        </div>
                        <br/>
                        <p>还没有收藏的书哦</p>
                    </div>
				</c:if>
			</div>
		</div>
		<div class="footer">
			<div class="footer_link"></div>
			<div class="footer_cont">
				<p>本站所有小说为转载作品，所有章节均由网友上传，转载至本站只是为了宣传本书让更多读者欣赏。</p>
			</div>
		</div>
	</div>

	<!--增加书架分组模态框-->
	<div class="modal fade" id="addBookshelf" tabindex="-1" role="dialog"
		aria-labelledby="addBookshelfModalLabel">
		<div class="modal-dialog" role="document" style="max-width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">新建分组</h4>
				</div>
				<div class="modal-body">
					<input type="text" autofocus="autofocus" class="form-control"
						id="addName" maxlength="10">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="addBookshelf" class="btn btn-primary">提交</button>
				</div>
			</div>
		</div>
	</div>
	<!--修改书架分组模态框-->
	<div class="modal fade" id="updBookshelf" tabindex="-1" role="dialog"
		aria-labelledby="updBookshelfModalLabel">
		<div class="modal-dialog" role="document" style="max-width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">修改分组</h4>
				</div>
				<div class="modal-body">
					<input type="text" class="form-control" checked="checked"
						id="updName" maxlength="10">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="updBookshelf" class="btn btn-primary">提交</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		//移出书架
		function delBookshelf(bid,nid){
			$.ajax({
				type:'post',
				url:'/delNovel',
				data:{"bid":bid,"nid":nid},
				cache:false,
				success:function(){
					alert("删除成功");
					window.location.reload();
				}
			})
		}
		$("#addBookshelf").click(function() {
			var name = $("#addName").val();
			if(name){
				$.ajax({
					url:"/addBookshelf",
					type:"post",
					data:"name="+name,
					success:function(data){
						window.location.reload();
					}
				})
			}else{
				alert("请输入分组名称");
			}
		})
		
		//修改id
		var id;
		$("#updBookshelf").on("show.bs.modal",function(e){
			id = $(e.relatedTarget).data("bid");
		})
		$("#updBookshelf").click(function() {
			var name = $("#updName").val();
			if(name){
				$.ajax({
					url:"/updBookshelf",
					type:"post",
					data:{'name':name,"id":id},
					success:function(data){
						window.location.reload();
					}
				})
			}
		})
		
		//删除书架
		function delBook(bid){
			if (window.confirm("此操作不可逆，是否确认？")) {
				$.ajax({
					url:'/delBookshelf',
					type:'post',
					data:"id="+bid,
					cache:false,
					success:function(data){
						window.location.reload();
					}
				})
			}
		}
	</script>
</body>
</html>
