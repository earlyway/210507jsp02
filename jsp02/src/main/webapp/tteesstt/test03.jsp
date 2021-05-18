<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$("#id").keyup(function(){
	var reach=$("#reach").val();
	$.ajax({
		type:"post",
		url :"/jsp02/iidd_servlet/possible.do"
		success: function(result){
			$("#result")}
		}
	});
	});

</script>
</head>
<body>
<table border="1">

<tr>
<td>운행거리 :<input type="text" id="id"></td> </tr>
<tr><td><div>result</div>     사용가능한 아이디입니다. 사용불가능한 아이디입니다. </td></tr>
</table>
</body>
</html>
