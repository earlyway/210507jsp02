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
<h2>세션 로그인</h2>
<form method="post" name="form1" 
action="/jsp02/session_servlet/login.do">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input name="userid" id="userid"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" id="passwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"
			id="btnLogin" value="로그인"></td>
		</tr>
	</table>
</form>
<%
String message="";
if(request.getParameter("message") !=null){
	message=request.getParameter("message");
	if(message.equals("logout")){
%>
		<div style="color:red;">로그아웃 완료</div>
<%
	}
}
%>
</body>
</html>