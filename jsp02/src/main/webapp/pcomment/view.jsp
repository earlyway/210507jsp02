<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$("#btnSave").click(function(){
		comment_add();
	});
	
});
	function comment_add() {
		var param = "pcode_num="+$("#pcode").val()+"&pwriter=" + $("#pwriter").val() + "&pcontent="
				+ $("#pcontent").val();
		$.ajax({
			type : "post",
			url : "${path}/board_servlet/comment_add.do",
			data : param,
			success : function() {
				$("#pwriter").val("");
				$("#pcontent").val("");
				comment_list();
			}
		});
	}
</script>
</head>
<body>
	<h2>댓글쓰기 폼</h2>

	<table border="1" width="700px">
		<tr>
			<td><input type="text" id="pcode" placeholder="상품코드"></td>

		</tr>
		<tr>
			<td><input id="pwriter" placeholder="이름"></td>
			<td rowspan="2">
				<button id="btnSave" type="button">확인</button>
			</td>
		</tr>
		<tr>
			<td><textarea rows="5" cols="80" placeholder="댓글 내용을 입력하세요"
					id="pcontent"></textarea></td>
		</tr>
	</table>

	<div id="commentList"></div>
</body>
</html>
