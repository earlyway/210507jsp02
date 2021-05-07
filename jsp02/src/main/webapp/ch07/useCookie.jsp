<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
</head>
<body>
<%
 Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(int i=0; i<cookies.length; i++){
		out.println("쿠키이름:" + cookies[i].getName());
		out.println(",쿠키값:" + cookies[i].getValue() + "<br>");
	}
}

%>
아이디 : ${cookie.id.name } ${cookie.id.value }<br>
비번 : ${cookie.pwd.name } ${cookie.pwd.value }<br>
이름 : ${param.name }<br>
<a href="deleteCookie.jsp?name=${param.name }">쿠키삭제</a><br>
<a href="editCookie.jsp">쿠키 변경</a><br>

</body>
</html>