<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | PT 피드백</title>
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
	<jsp:include page="/WEB-INF/views/layout/navbar.jsp" flush="false"/>
	<c:if test="${feedbackVO.content == null }">
		<script>
			alert("작성된 피드백이 없습니다.");
			location.href = history.back();
		</script>
	</c:if>
	<div class="myPT-info" style="margin-left:300px; margin-right:300px;">
		<br>
		<h2>피드백 보기</h2>
		<br><br>
		<video src="/resources/video/${uploadVO.getFilenameURL() }" controls style="width:500px; height:auto;"></video>
		<br><br>
		<table class="info-table">
			<tr>
				<th class="myPT_info_th" style="width:120px;">작성자</th>
				<td>${membername }</td>
			</tr>
			<tr>
				<th class="myPT_info_th">운동내용</th>
				<td>${boardVO.getReplContent() }</td>
			</tr>
			<tr>
				<th class="myPT_info_th">트레이너</th>
				<td>${trainerName }</td>
			</tr>
			<tr>
				<th class="myPT_info_th">피드백 작성 날짜</th>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${feedbackVO.regdate }"/></td>
			</tr>
			<tr>
				<th class="myPT_info_th">피드백 내용</th>
				<td>${feedbackVO.getReplContent() }</td>
			</tr>
		</table>
		<button class="btn btn-default pull-right back">뒤로가기</button>
	</div>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			
			$(".back").on("click", function() {
				history.back();
			});
		});
	</script>
</body>
</html>