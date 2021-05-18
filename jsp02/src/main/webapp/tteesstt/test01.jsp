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
	$("#btn").click(function(){
	var reach=$("#reach").val();
	$.ajax({
		type:"post",
		url :"/jsp02/taxi_servlet/taxi.do"
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
<td>운행거리 :<input type="text" id="reach"></td> </tr>
<button id="btn">계산</button><br>
<tr><td>요금 :  <div>result</div>     원 </td></tr>
</table>
</body>
</html>
