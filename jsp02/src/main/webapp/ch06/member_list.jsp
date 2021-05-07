<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function view(userid){
	document.form1.userid.value=userid;
	document.form1.submit();
}

</script>
</head>
<body>
<%@ page import="java.util.*"%>
<%@ page import="member.*" %>
<table border="1">
	<tr>
		<th>이름</th>
		<th>아이디</th>
		<th>비번</th>
		<th>가입일자</th>
		<th>주소</th>
		<th>전화</th>
	</tr>
<%
Map<String, Object> map=(Map<String, Object>)request.getAttribute("map");
List<MemberDTO> list=(List<MemberDTO>)map.get("list");
for(MemberDTO row : list){
%>
	<tr>
		<td><a href="#" onclick="view('<%=row.getUserid()%>')"><%=row.getName() %></a></td>
		<td><%=row.getUserid() %></td>
		<td><%=row.getPasswd() %></td>
		<td><%=row.getReg_date() %></td>
		<td><%=row.getAddress() %></td>
		<td><%=row.getTel() %></td>
	</tr>
<%
}
%>
</table>
<form name="form1" method="post" action="/jsp02/member_servlet/view.do">
	<input type="hidden" name="userid">
</form>
</body>
</html>