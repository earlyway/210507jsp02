<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>세션 로그인</h2>
<form method="post" name="form1" action="/jsp02/session_servlet/login.do">
<table border="1">
	<tr>
		<td>아이디</td>
		<td><input name="userid"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그인"></td>
	</tr>
</table>
</form>
<%
String message="";
if(request.getParameter("message")!=null){ //파라미터가 null이 아니면
	message=request.getParameter("message"); //파라미터를 읽어오고
	if(message.equals("logout")){ // 스트링의 내용 비교
%>
		<div style="color:red;">로그아웃되었습니다.</div>
<%		
	}
}
%>
</body>
</html>



