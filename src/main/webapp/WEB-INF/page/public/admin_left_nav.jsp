<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside class="col-sm-3 col-md-2 col-lg-2 sidebar">
	  <ul class="nav nav-sidebar">
        <li class=""><a href="/toAdminIndex">报告</a></li>
      </ul>
      
     <% 
     	String lean = request.getRequestURI();
     	request.setAttribute("lean", lean);
     %>
      <ul class="nav nav-sidebar">
        <li class="<c:if test="${lean.contains('manage-user') }">active</c:if>"><a class="leftnav  " href="/toUsers">用户 </a></li>
        <li class="<c:if test="${lean.contains('article') }">active</c:if>"><a class="leftnav " href="/toArticle">小说</a></li>
        <li class="<c:if test="${lean.contains('notice') }">active</c:if>"><a class="leftnav" href="/toNotice">公告</a></li>
      </ul>
       <ul class="nav nav-sidebar">
	       <li><a class="leftnav"  data-toggle="tooltip" data-placement="bottom" title="网站暂无留言功能">评论</a></li>
	       <li><a class="leftnav" data-toggle="tooltip" data-placement="bottom" title="网站暂无留言功能">留言</a></li>
       </ul>
     <script type="text/javascript">
	     /*  $(".leftnav").click(function(){
	    	  //刷新页面时会丢失
	    	  $(this).parent().addClass("active");
	      }) */
      </script>
</aside>