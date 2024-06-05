<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 트레이너 목록</title>
<!-- 라이브러리 등록 - jQuery, Bootstrap : CDN 방식-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/list.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/layout/navbar.jsp" flush="false"/>
	<h2>트레이너 목록</h2>
	<table class="table table-bordered">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>성별</th>
			<th>전화번호</th>
			<th>가입일</th>
			<th>PT 회원 수</th>
			<th>PT 평점</th>
		</tr>
		<c:forEach items="${list }" var="userVO" varStatus="idx">
			<tr class="rowdata">
				<td class="userid">${userVO.userid }</td>
				<td>${userVO.username }</td>
				<td>${userVO.usergender }</td>
				<td>${userVO.callnum }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${userVO.regdate }"/></td>
				<td>${membershipList.get(userVO.userid) }명</td> <!-- enroll에서 count -->
				<td>
					<c:if test="${Double.isNaN(ratingList.get(userVO.userid)) }">
						평가가 없습니다.
					</c:if>
					<c:if test="${!Double.isNaN(ratingList.get(userVO.userid)) }">
						${ratingList.get(userVO.userid) }점
					</c:if>
				</td> <!-- trainer테이블?? -->
			</tr>		
		</c:forEach>
	</table>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
	<script>
		$(function() { // onready - html의 body 부분의 내용이 다 로딩되면 동작되도록 한다.
			// 데이터 한줄 클릭하면 글보기로 이동되는 이벤트 처리
			$(".rowdata").click(function() { // rowdata 클래스가 클릭되면 function 실행
				location = '/admin/trainer/view?userid=' + $(this).find(".userid").text();
			})
		});
	</script>
</body>
</html>