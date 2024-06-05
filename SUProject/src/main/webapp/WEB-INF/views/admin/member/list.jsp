<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 회원 목록</title>
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
	<h2>회원 목록</h2>
	<table class="table table-bordered">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>성별</th>
			<th>전화번호</th>
			<th>등록일</th>
			<th>트레이너</th>
			<th>총 PT 신청 수</th>
			<th>완료된 PT 횟수</th>
			<th>잔여 PT 횟수</th>
		</tr>
		<c:forEach items="${enrollList }" var="enrollVO" varStatus="idx">
			<tr class="rowdata">
				<td class="userid">${enrollVO.member }</td>
				<td>${enrollVO.membername }</td>
				<td>${userList.get(enrollVO.member).usergender }</td>
				<td>${userList.get(enrollVO.member).callnum }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${enrollVO.regdate }"/></td>
				<td>${enrollVO.trainername }</td>
				<td>${enrollVO.totalcnt }회</td>
				<td>${enrollVO.completecnt }회</td>
				<td>${enrollVO.totalcnt - enrollVO.completecnt }회</td>
			</tr>		
		</c:forEach>
	</table>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>