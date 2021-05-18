<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <c:redirect url="ex04.jsp">
	<c:param name="start" value="2"></c:param>
	<c:param name="end" value="5"></c:param>
</c:redirect> --%>
<%
response.sendRedirect("ex04.jsp?start=2&end=5");
%>
</body>
</html>