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
session.invalidate();

%>
세션이 초기화되었습니다.
<a href="viewSession.jsp">세션확인</a>

</body>
</html>