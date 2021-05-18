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
session.removeAttribute("id");
session.removeAttribute("age");
//session.invalidate();
%>
세션이 초기화되었습니다.
<a href="viewSession.jsp">세션 확인</a>
</body>
</html>