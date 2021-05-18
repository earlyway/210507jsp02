<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<sql:setDataSource url="jdbc:oracle:thin:@localhost:1521/xe"
driver="oracle.jdbc.OracleDriver" user="java" password="java1234"
var="ds1" scope="page" />
<sql:query var="rs" dataSource="${ds1}">
	select empno,ename,sal,deptno from emp
</sql:query>
<table border="1">
	<tr>
		<th>번호</th>
		<th>사번</th>
		<th>이름</th>
		<th>급여</th>
		<th>부서코드</th>
	</tr>
	<c:forEach var="row" items="${rs.rows}" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${row.empno}</td>
			<td>${row.ename}</td>
			<td>${row.sal}</td>
			<td>${row.deptno}</td>	
		</tr>
	</c:forEach>	
</table>
</body>
</html>



















