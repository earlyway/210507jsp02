<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"  %>
</head>
<body>
<%@ page import="java.util.HashMap" %>
<%
HashMap<String,String> map=
	(HashMap<String,String>)request.getAttribute("map");
String[] fruits={"포도","오렌지","바나나","사과"};
for(String f : fruits){
	out.println(f+"==>"+map.get(f)+"<br>");
}
%>
${map["포도"]}<br>
${map["오렌지"]}<br>
${map["바나나"]}<br>
${map["사과"]}<br>
<c:forEach var="row" items="${map}">
	${row} : ${row.key} => ${row.value}<br>
</c:forEach>	
</body>
</html>
