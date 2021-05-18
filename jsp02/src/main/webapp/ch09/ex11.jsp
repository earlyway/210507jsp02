<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.js"></script>
<script>
$(function(){
	$.getJSON("item.json", function(data, textStatus){
		$("#fruit").append(
"<tr><td>id</td><td>과일</td><td>가격</td><td>비고</td></tr>");
		$.each(data, function(){
			$("#fruit").append("<tr><td>"+this.id+"</td>"
				+"<td>"+this.name+"</td><td>"+this.price+"</td>"
				+"<td>"+this.description+"</td></tr>");
		});
	});
});
</script>
</head>
<body>
<table id="fruit" border="1"></table>
</body>
</html>