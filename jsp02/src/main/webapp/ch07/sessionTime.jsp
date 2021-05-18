<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>세션의 유효시간</h2>
<%
session.setMaxInactiveInterval(600); //세션의 유효시간 변경
int timeout=session.getMaxInactiveInterval(); //세션의 유효시간 조회 
out.println("세션의 유효시간:"+timeout);
%>
</body>
</html>