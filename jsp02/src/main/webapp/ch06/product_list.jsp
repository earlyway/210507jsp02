<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.*"%>
<%@ page import="tteesstt.*" %>
<table border="1">
	<tr>
		<th>상품코드</th>
		<th>상품명</th>
		<th>금액</th>
		<th>수량</th>
	</tr>
	<%
Map<String, Object> map=(Map<String, Object>)request.getAttribute("map");
List<ProDTO> list=(List<ProDTO>)map.get("list");
for(ProDTO row : list){
%>
	<tr>
		<td><a href="#" onclick="view('<%=row.getProductcode()%>')"></a></td>
		<td><%=row.getProductcode() %></td>
		<td><%=row.getProductname() %></td>
		<td><%=row.getProductprice() %></td>
		<td><%=row.getProductqty() %></td>
	</tr>
<%
}
%>
</table>
</body>
</html>