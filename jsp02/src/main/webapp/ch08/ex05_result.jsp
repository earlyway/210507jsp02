<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ page import="java.util.ArrayList" %>
<%
ArrayList<String> items=(ArrayList<String>)request.getAttribute("items");
for(String str : items){
	out.println(str+"<br>");
}
%>
<!-- forEach var=변수명 items=리스트 -->
<c:forEach var="fruit" items="${items}">
	${fruit}<br>
</c:forEach>	
</body>
</html>







