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
<%
/* int num=Integer.parseInt(request.getParameter("num"));
int sum=0;
for(int i=1; i<=num; i++){
	sum+=i;
}
out.println("합계:"+sum); */
%>
<c:set var="sum" value="0" />
<c:forEach var="i" begin="1" end="${param.num}">
	<c:set var="sum" value="${sum + i}" />
</c:forEach>
합계: ${sum} 
</body>
</html>





