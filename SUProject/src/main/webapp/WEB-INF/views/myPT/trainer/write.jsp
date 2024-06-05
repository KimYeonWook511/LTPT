<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 트레이너 피드백 등록</title>
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
	<div class="myPT-info" style="margin-left:300px; margin-right:300px;">
		<br>
		<h2>트레이너 피드백 등록</h2>
		<br><br>
		<video src="/resources/video/${uploadVO.getFilenameURL() }" controls style="width:500px; height:auto; margin:auto; display:block;"></video>
		<br><br>
		<form action="/myPT/trainer/write" method="post">
			<table class="info-table">
				<tr>
					<th class="myPT_info_th">작성자</th>
					<td>${membername }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">운동내용</th>
					<td>${boardVO.getReplContent() }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">트레이너</th>
					<td>${loginVO.username }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">피드백 내용</th>
					<td><textarea rows="10" cols="95" name="content" maxlength="5000">${feedbackVO.content }</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><button class="btn btn-default pull-right">피드백 등록</button></td>
				</tr>
			</table>
			<input type="hidden" name="bno" value="${uploadVO.bno }">
			<input type="hidden" name="videono" value="${uploadVO.videono }">
			<input type="hidden" name="writer" value="${loginVO.userid }">
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>