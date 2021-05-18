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
String id="kim@nate.com";
String passwd="1234";
int age=20;
double height=170.5;
session.setAttribute("id", id);
session.setAttribute("passwd", passwd);
session.setAttribute("age", age);
session.setAttribute("height", height);
out.println("저장되었습니다.");
%>
<a href="viewSession.jsp">세션 확인</a>
</body>
</html>