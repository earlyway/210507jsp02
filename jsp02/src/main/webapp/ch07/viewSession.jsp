<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
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
Enumeration<String> attr = session.getAttributeNames();
while(attr.hasMoreElements()){
	String key = attr.nextElement();
	Object value = session.getAttribute(key);
	out.println("세션변수명:" + key);
	out.println("세션값:" + value + "<br>");
}
String id=(String) session.getAttribute("id");
int age=(Integer) session.getAttribute("age");
Object a = session.getAttribute("age");
double height = (Double) session.getAttribute("height");

%>      <!-- EL Expression Language표현언어 -->
아이디 : ${sessionScope.id }<br>
비번 : ${sessionScope.passwd }<br>
나이 : ${sessionScope.age }<br>
키 : ${sessionScope.height }<br>

<a href="deleteSession.jsp">세션 삭제</a>


</body>
</html>