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
$(function(){//자동 실행되는 부분
	list('1');//페이지넘버 1
});
function list(curPage){
	var param = "curPage="+curPage;
	$.ajax({
		type : "post",
		url :"${path}/page_servlet/list.do",
		data :param,
		success:function(result){
			$("#result").html(result);
		}
	});
}

</script>
</head>
<body>
<h2>페이지 나누기</h2>
<div id="result"></div>
</body>
</html>