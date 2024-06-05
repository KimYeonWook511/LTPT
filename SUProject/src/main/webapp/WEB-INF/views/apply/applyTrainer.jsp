<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 트레이너 신청서 등록</title>
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
	<c:if test="${msg == '트레이너 신청을 해주세요.'}">
		<script>
			alert('${msg}');
			location.href = "/apply/applyTrainer"; // url 새로고침
		</script>
	</c:if>
	
	<div class="myPT-info" style="margin-left:300px;">
		<br>
		<h2>트레이너 신청서</h2>
		<br><br><br>
		<form action="/apply/applyTrainer" method="post">
			<table class="info-table">
				<tr>
					<th class="myPT_info_th">이름</th>
					<td>${loginVO.username }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">전화번호</th>
					<td>${loginVO.callnum }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">신청내용</th>
					<td><textarea rows="10" cols="95" name="content" maxlength="1000"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><button class="btn btn-default pull-right">트레이너 신청</button></td>
				</tr>
			</table>
		</form>	
	</div>
	
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>s
</body>
</html>