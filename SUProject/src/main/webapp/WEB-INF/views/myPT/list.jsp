<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 나의 PT 피드백</title>
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
	<c:if test="${msg == '허용되지 않은 접근입니다.'}">
		<script>
			alert('${msg}');
			location.href = "/myPT/list"; // url 새로고침
		</script>
	</c:if>
	<h2 style="margin-left:10px;">피드백 리스트</h2>
	<br>
	<table class="table table-bordered">
		<tr>
			<th style="width: 60px">번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>게시일</th>
			<th>트레이너</th>
			<th>피드백상태</th>
		</tr>
		<c:forEach items="${list }" var="boardVO">
			<tr class="rowdata">
				<td class="bno">${boardVO.bno }</td>
				<td>${boardVO.title }</td>
				<td>${nameList.get(boardVO.writer) }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate }"/></td>
				<td>${nameList.get(boardVO.trainer) }</td>
				<td>${feedbackProgressList.get(boardVO.bno) }</td>
			</tr>			
		</c:forEach>
	</table>
	<a href="/myPT/write" class="btn btn-default pull-left">피드백등록</a>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
	<script>
		$(function() { // onready - html의 body 부분의 내용이 다 로딩되면 동작되도록 한다.
			// 데이터 한줄 클릭하면 글보기로 이동되는 이벤트 처리
			$(".rowdata").click(function() { // rowdata 클래스가 클릭되면 function 실행
				location = '/myPT/view?bno=' + $(this).find(".bno").text();
			})
		});
		
		if ('${message}' == '신청 가능한 PT횟수가 없습니다.' || '${message}' == '피드백 게시글이 등록되었습니다.') {
			alert('${message}');
			
		}
	</script>
</body>
</html>