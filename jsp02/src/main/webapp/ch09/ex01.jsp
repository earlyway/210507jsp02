<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//헤더 정보를 읽어옴
Enumeration<String> headerNames=request.getHeaderNames();
while(headerNames.hasMoreElements()){ //다음 요소가 있으면
	String key=(String)headerNames.nextElement(); //다음 요소를 읽고
	String value=request.getHeader(key); //헤더 value 조회
	out.println(key+":"+value+"<br>");
}  
%>
<c:forEach var="h" items="${header}">
	${h.key} => ${h.value}<br>
</c:forEach>
<!-- set var="변수명" value="값" -->
<c:set var="browser" value="${header['user-agent']}" />
<hr>
<!-- 출력문 -->
browser: <c:out value="${browser}" /><br>
browser: ${browser}<br>
<hr>
<!-- 변수 제거 -->
<c:remove var="browser" />
browser: <c:out value="${browser}" />
</body>
</html>