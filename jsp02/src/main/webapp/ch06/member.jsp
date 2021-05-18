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
	// key=value&key=value
	//var param="userid="+$("#userid").val()+"&passwd="+$("#passwd").val()
	//+"&name="+$("#name").val()+"&address="+$("#address").val()
	//+"&tel="+$("#tel").val();
	var param={"userid":$("#userid").val(), "passwd":$("#passwd").val(),
		"name":$("#name").val(), "address":$("#address").val(),
		"tel":$("#tel").val()};
	$.ajax({
		type: "post",
		url: "/jsp02/member_servlet/join.do",
		data: param,
		success: function(){
			//리스트 갱신
			list();
			//input 태그에 입력된 값들을 지움
			$("#userid").val("");
			$("#passwd").val("");
			$("#name").val("");
			$("#address").val("");
			$("#tel").val("");
		}
	});
}
function list(){
	$.ajax({
		url: "/jsp02/member_servlet/list.do",
		success: function(result){
			$("#memberList").html(result);
		}
	});
}
</script>
</head>
<body>
아이디 <input id="userid"><br>
비번 <input type="password" id="passwd"><br>
이름 <input id="name"><br>
주소 <input id="address"><br>
전화 <input id="tel"><br>
<button id="btnSave">추가</button>
<div id="memberList"></div>

</body>
</html>










