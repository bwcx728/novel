<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>首页-清风小说网</title>
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
	href="${pageContext.request.contextPath }/css/index_main.css" />
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
<style>
.items_right .notice {
	padding: 0 !important;
	height: 210px;
	width: 90%;
	margin: 0 auto;
	font-size: 15px;
	bottom: -10px;
	position:relative;
	overflow: hidden ;
	/*animation:动画名字 动画持续时间 动画次数infinite让动画一直跳  alternate:轮流反向播放*/
	animation: notice infinite 2s alternate;
}
.notice_content{
	position: absolute;
	height:210px;
	/*animation:动画名字 动画持续时间 动画次数infinite让动画一直跳 alternate:轮流反向播放*/
	animation: content 4s ;			
}
.updNoticeImg{
	height:20px;
	width:20px;
}
@keyframes content { 
	0%{bottom:-210px;}
	100%{bottom:-10px;}
}

.table-bordered>tbody>tr>td{
	padding:0;
	height:28px;
	font-size:15px;
	text-align:center;
	line-height:28px;
}
.calendar h4 {
position:relative;
}
.calendar h4 span{
	position:absolute;
	font-size:12px;
	bottom:0;
	right:0;
}


</style>
</head>
<body>
	<div class="container">
		<jsp:include page="../public/novel_header_nav.jsp"></jsp:include>
		<div class="main">
			<div class="items">
				<c:forEach items="${novelList }" var="novel" begin="0" end="3"
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
							<span class="small_bottom">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${novel.introduce }</span>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="items_right">
				<h4>
					站内公告
					<c:if test="${user.rid==1&&not empty newNotice}">
						<a data-toggle="modal" data-target="#updNotice"><span
						 style="float: right;"><img class="updNoticeImg" src="images/icon/update.png"></span></a>
					</c:if>
				</h4>
				<div class="notice">
					<div  class="notice_content">
						${newNotice.content }
					</div>
				</div>
				<div class="calendar">
					<jsp:include flush="true" page="../public/rili.jsp"></jsp:include>
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

	<!--增加公告信息模态框-->
	<div class="modal fade" id="updNotice" tabindex="-1" role="dialog"
		aria-labelledby="updNoticeModalLabel">
		<div class="modal-dialog" role="document" style="max-width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">修改公告</h4>
				</div>
				<div class="modal-body">
					<input type="text" hidden="hidden" class="id" value="${newNotice.id}">
					<textarea class="content" name="content" rows="3" cols="35"
						autofocus maxlength="40" style="resize: none;" >${newNotice.content }</textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="updNoticeBtn" class="btn btn-primary">提交</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		 $("#updNoticeBtn").click(function(){
			var	id = $(".id").val();
			var content = $(".content").val();
			if (content) {
				$.ajax({
					url : "/updNotice",
					type : "post",
					data : {"content":content,"id":id},
					success : function(data) {
						window.location.reload();
					}
				})
			} else {
				alert("请输入公告内容!");
			}
		})
	</script>
</body>

</html>
