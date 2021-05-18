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
<c:set var="country" value="Korea" />
<!-- if test="조건식"  -->
<c:if test="${country != null }">
	국가명: ${country}<br>
	국가명: <c:out value="${country}" /><br>
</c:if>
<%
int[] nums={10,20,30,40,50,60,70,80,90};
%>
<c:set var="num" value="<%=nums%>" />
<c:forEach var="n" items="${num}">
	${n},
</c:forEach>
<c:set var="season" value="a" />
<!-- 다중조건문 -->
<c:choose>
	<c:when test="${season == '봄' }">
	<!-- 설명 -->  
		<img src="/jsp02/images/spring.jpg">
	</c:when>
	<c:when test="${season == '여름' }">
		<img src="/jsp02/images/summer.jpg">
	</c:when>
	<c:when test="${season == '가을' }">
		<img src="/jsp02/images/autumn.jpg">
	</c:when>
	<c:when test="${season == '겨울' }">
		<img src="/jsp02/images/winter3.jpg">
	</c:when>			
	<c:otherwise>기타 모든 경우</c:otherwise>
</c:choose>
</body>
</html>