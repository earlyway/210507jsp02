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
<!-- set var="변수명" value="값" -->
<c:set var="name" value="김철수" scope="session" />
<c:set var="age" value="20" scope="session" />
<c:set var="job" value="dba" scope="session" />
세션변수가 생성되었습니다.<br>
<a href="ex04_result.jsp">확인</a> 

</body>
</html>