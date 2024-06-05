<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 트레이너 정보 보기</title>
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
		<h2>트레이너 정보 보기</h2>
		<br><br><br>
		<form role="form" method="post">
			<table class="info-table">
				<tr>
					<th class="myPT_info_th">아이디</th>
					<td>${userVO.userid }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">이름</th>
					<td>${userVO.username }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">가입일</th>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${userVO.regdate }" /></td>
				</tr>
				<tr>
					<th class="myPT_info_th">전화번호</th>
					<td>${userVO.callnum }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">성별</th>
					<td>${userVO.usergender }</td>
				</tr>
				<tr>
					<th class="myPT_info_th">회원 수</th>
					<td>${membership }명</td>
				</tr>			
				<tr>
					<th class="myPT_info_th">평점</th>
					<td>
						<c:if test="${Double.isNaN(rating) }">
							평가가 없습니다.
						</c:if>
						<c:if test="${!Double.isNaN(rating) }">
							${rating }점
						</c:if>
					</td>
				</tr>
			</table>
			<input type="hidden" name="userid" value="${userVO.userid }">
<!-- 		<button type="button" class="btn btn-default pull-right adminAuth">관리자 권한 부여</button> -->
			<button type="button" class="btn btn-default pull-right memberAuth">트레이너 권한 해지</button>
		</form>	
	</div>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
	<script>
		$(document).ready(function() {
			var formObj = $("form[role='form']");
			console.log(formObj);
			
			$(".memberAuth").on("click", function() {
				if (!confirm("${userVO.username}(${userVO.userid})님의 트레이너 권한을 해지하시겠습니까?")) return;
				formObj.attr("action", "/admin/trainer/memberAuth");
				formObj.submit();
			});
			
// 			$(".adminAuth").on("click", function() {
// 				if (!confirm("${userVO.username}(${userVO.userid})님에게 관리자 권한을 부여하시겠습니까?")) return;
// 				formObj.attr("action", "/admin/trainer/adminAuth");
// 				formObj.submit();
// 			});
		});
	</script>
</body>
</html>