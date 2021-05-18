<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//세션에 저장된 userid 값을 가져옴
String userid=(String)session.getAttribute("userid");
if(userid==null){ // null이면 
%>
	<script>
		alert("먼저 로그인을 해주세요.");
		location.href="/jsp02/ch07/session_login.jsp";
	</script>	
<%
}
%>