<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.Enumeration" %>
<%
Enumeration<String> attr=session.getAttributeNames();
while(attr.hasMoreElements()){
	String key=attr.nextElement();
	Object value=session.getAttribute(key);
	out.println("세션변수명:"+key);
	out.println(",세션값"+value+"<br>");
}
String id=(String)session.getAttribute("id");
String passwd=(String)session.getAttribute("passwd");
//int age=(Integer)session.getAttribute("age");
//Object a=session.getAttribute("age");
//double height=(Double)session.getAttribute("height");
%>
아이디 : <%=id%><br>
비번 : <%=passwd%><br>
<%-- 나이 : <%=age%><br>
나이 : <%=a%><br>
키 : <%=height%><br> --%>

아이디: ${sessionScope.id}<br>
비번: ${sessionScope.passwd}<br>
나이: ${sessionScope.age}<br>
키: ${sessionScope.height}<br>
<a href="deleteSession.jsp">세션 삭제</a>
</body>
</html>