<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="emp.dao.EmpDAO" %>
<%
EmpDAO dao=new EmpDAO();
dao.insert();
dao.insert_batch();
out.println("완료되었습니다.");
%>
</body>
</html>