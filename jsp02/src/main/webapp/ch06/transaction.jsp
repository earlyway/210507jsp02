<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="emp.dao.EmpDAO" %>
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
	EmpDAO dao=new EmpDAO();
dao.insert();
dao.insert_batch();
out.println("완료!!");
%>

</body>
</html>