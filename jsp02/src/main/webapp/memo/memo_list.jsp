<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$("#chkAll").click(function(){
		if($("#chkAll").prop("checked")){ //체크여부
			$("input[name=idx]").prop("checked",true); //전부 체크
		}else{
			$("input[name=idx]").prop("checked",false); //전부 해제
		}
	});
	$("#btnAllDel").click(function(){
		document.form1.action="/jsp02/memo_servlet/delete_all.do";
		document.form1.submit();
	});
});
function memo_del(idx){
	location.href="/jsp02/memo_servlet/del.do?idx="+idx;
}
</script>
</head>
<body>
<form method="post" name="form1">
<table border="1">
	<tr>
		<th><input type="checkbox" id="chkAll"></th>
		<th>번호</th>
		<th>이름</th>
		<th>메모</th>
		<th>날짜</th>
		<th><input type="button" value="선택삭제" id="btnAllDel"></th>
	</tr>
	<c:forEach var="row" items="${list}" varStatus="s">
		<tr>
			<td><input type="checkbox" name="idx" value="${row.idx}">
			<td>${s.count}</td>
			<td>${row.writer}</td>
			<td><a href="/jsp02/memo_servlet/view.do?idx=${row.idx}">
			${row.memo}</a></td>
			<td>${row.post_date}</td>
			<td><input type="button" value="삭제" 
				onclick="memo_del('${row.idx}')"></td>
		</tr>
	</c:forEach>
</table>
</form>
</body>
</html>







