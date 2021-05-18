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
	list();
	$("#btnSave").click(function(){
		insert();
	});
});
function insert(){
	var param="productcode=" + $("#productcode").val()
			+"&productname=" + $("#productname").val()
			+"&productprice=" + $("#productprice").val()
			+"&productqty=" + $("#productqty").val();
	$.ajax({
		type:"post",
		url : "/jsp02/pro_servlet/join.do",
		data : param,
		success : function(){
			list();
			$("#productcode").val("");
			$("#productname").val("");
			$("#productprice").val("");
			$("#productqty").val("");
			$("#productcode").focus();
		}
	});
});
function list(){
	$.ajax({
		type:"post",
		url : "/jsp02/pro_servlet/list.do",
		success : function(result){
			$("#proList").html(result);
		}
	});
}

</script>
</head>
<body>
<table>
<tr><td>상품코드 :<input type="text" id="productcode"><br></td></tr>
<tr><td>상품명 :<input type="text" id="productname"><br></td></tr>
<tr><td>금액 :<input type="text" id="productprice"><br></td></tr>
<tr><td>수량 :<input type="text" id="productqty"><br></td></tr>
<button id="btnSave">확인</button><br>
<div id="proList"></div>
</table>
</body>
</html>


