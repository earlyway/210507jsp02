<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js">
</script>
</head>
<body>
<%
	Cookie cookie = new Cookie("id","");
cookie.setMaxAge(0);// 유효시간 0초, 즉시 삭제
response.addCookie(cookie);
%>
쿠키가 삭제되었습니다.
<a href="useCookie.jsp">쿠키 확인</a>

</body>
</html>