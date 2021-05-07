<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Cookie cookie = new Cookie("id","김철수");
	Cookie cookie2 = new Cookie("pwd","1234");
	cookie.setMaxAge(10);
	response.addCookie(cookie);//쿠키를 클라이언트에 저장
	response.addCookie(cookie2);
	
%>
쿠키가 생성되었습니다. <br>
<a href="useCookie.jsp?name=김철수">쿠키 확인</a>

</body>
</html>