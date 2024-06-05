<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 트레이너 신청서 보기</title>
<!-- 라이브러리 등록 - jQuery, Bootstrap : CDN 방식-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/board.css">
<link rel="stylesheet" href="/css/navbar.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/layout/navbar.jsp" flush="false" />
	<div class="myPT-info" style="margin-left:300px; margin-right:300px;">
		<br>
		<h2>트레이너 신청서 보기</h2>
		<br><br><br>
		<form role="form" method="post">
			<table class="info-table">
				<tr>
					<th class="myPT_info_th">아이디</th>
					<td>${applyTrainerVO.userid }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">이름</th>
					<td>${applyTrainerVO.username }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">신청일</th>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${applyTrainerVO.regdate }"/></td>
				</tr>
				<tr>
					<th class="myPT_info_th">전화번호</th>
					<td>${applyTrainerVO.callnum }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">성별</th>
					<td>${applyTrainerVO.usergender }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">신청내용</th>
					<td>${applyTrainerVO.getReplContent() }</td>
				</tr>
			</table>
			<input type="hidden" name="userid" value="${applyTrainerVO.userid }">
			<br>
			<button type="button" class="btn btn-default pull-right fail">탈락</button>	
			<button type="button" class="btn btn-default pull-right pass">합격</button>
		</form>	
	</div>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
	<script>
		$(document).ready(function() {
			var formObj = $("form[role='form']");
			console.log(formObj);
			
			$(".pass").on("click", function() {
				if (!confirm("${applyTrainerVO.username}(${applyTrainerVO.userid})님을 합격시키시겠습니까?")) return;
				formObj.attr("action", "/admin/apply/pass");
				formObj.submit();
			});
			
			$(".fail").on("click", function() {
				if (!confirm("${applyTrainerVO.username}(${applyTrainerVO.userid})님을 탈락시키시겠습니까?")) return;
				formObj.attr("action", "/admin/apply/fail");
				formObj.submit();
			});
		});
	</script>
</body>
</html>