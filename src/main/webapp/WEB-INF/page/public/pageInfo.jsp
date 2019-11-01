<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="pagination pagenav">
	<li><span aria-hidden="true"><select id="sizeSelect"
			onchange="select_jump()">
				<c:forEach var="size" begin="4" end="12" step="1">
					<option <c:if test="${size == pageInfo.pageSize }">selected</c:if>
						value="${size }">${size }</option>
				</c:forEach>
		</select></span></li>
	<c:choose>
		<c:when test="${1==pageInfo.pageNumber }">
			<li class="disabled"><a aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:void(0);"
				onclick="a_jump( ${pageInfo.pageNumber-1})" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach items="${pageInfo.pageList }" var="p">
		<c:choose>
			<c:when test="${p==pageInfo.pageNumber }">
				<li class="active"><a>${p }</a></li>
			</c:when>
			<c:otherwise>
				<li class="a_pageNumber"><a href="javascript:;"
					onclick="a_jump(${p })">${p }</a></li>
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
			<li><a href="javascript:void(0);"
				onclick="a_jump( ${pageInfo.pageNumber+1})" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span>
			</a></li>
		</c:otherwise>
	</c:choose>
	<li><span aria-hidden="true">到第<input type="number"
			style="height: 20px; width: 40px;" id="pageNumber" name="pageNumber"
			size=10>页
	</span></li>
	<li><span>共${pageInfo.pageCount}页</span></li>
</ul>
