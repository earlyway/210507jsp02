<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Integer countNum = (Integer)session.getAttribute("counter");
int num=1;
if(countNum == null){
	countNum = 1;
}else {
	num = countNum.intValue();
	num++;
	countNum = num;
}
session.setAttribute("counter", countNum);

%>
당신은 <%=countNum.intValue() %>번째 방문하셨습니다.<br>
<%
String counter= Integer.toString(num);
for(int i=0; i<counter.length(); i++){
	String img = "<img src='../images/"+counter.charAt(i)+".gif'>";
	out.println(img);
}

%>
</body>
</html>