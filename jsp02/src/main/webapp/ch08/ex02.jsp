<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">
아이디: <input name="id" value="${param.id}"><br>
비번: <input name="pw" value="${param.pw}"><br>
이름: <input name="name" value="${param.name}"><br>
나이: <input name="age" value="${param.age}"><br>
<input type="submit" value="확인">
</form>
아이디: <%=request.getParameter("id") %><br>
<%-- 나이: <%=Integer.parseInt(request.getParameter("age"))%><br> --%>
나이: ${param.age}<br>
아이디: ${param.id}<br>
비번: ${param.pw}<br>
이름: ${param.name}<br>
</body>
</html>



