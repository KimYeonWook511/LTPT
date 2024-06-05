<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | PT 신청</title>
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
<%
	int idx = 0;
%>
	<jsp:include page="/WEB-INF/views/layout/navbar.jsp" flush="false"/>
	<c:if test="${msg == 'PT 신청을 해주세요.'}">
		<script>
			alert('${msg}');
			location.href = "/apply/applyPT"; // url 새로고침
		</script>
	</c:if>
	<c:if test="${msg == 'PT를 등록해주세요.'}">
		<script>
			alert('${msg}');
			location.href = "/apply/applyPT"; // url 새로고침
		</script>
	</c:if>
	<c:if test="${msg == 'PT를 등록해주세요.'}">
		<script>
			alert('${msg}');
			location.href = "/apply/applyPT"; // url 새로고침
		</script>
	</c:if>
	<div class="col-lg-4"></div>
	<div class="col-lg-4">
		<br>
		<h2>PT 신청</h2>
		<br><br><br>
		<form role="form" method="post">
			<c:if test="${!trainerList.isEmpty() }">
				<select id="trainer" name="trainerid" onchange="handleOnChange(this)">
					<c:forEach items="${trainerList }" var="trainer">
						<option value="${trainer.userid }">"${trainer.username }" 트레이너</option>
					</c:forEach>
				</select><br><br><br>
			</c:if>			
			<button type="button" class="btn btn-default pull-middle totalcnt10">10회 신청</button>
			<button type="button" class="btn btn-default pull-middle totalcnt20">20회 신청</button>
			<button type="button" class="btn btn-default pull-middle totalcnt30">30회 신청</button>
			<input type="hidden" id="totalcnt" name="totalcnt" value="">
		</form>	
	</div>
<!-- 	<div id="info_div"> -->
<%-- 		<c:if test="${Double.isNaN(ratingList.get(trainerList.get(idx).userid)) }"> --%>
<!-- 			평가가 없습니다.<br> -->
<%-- 		</c:if> --%>
<%-- 		<c:if test="${!Double.isNaN(ratingList.get(trainerList.get(idx).userid)) }"> --%>
<%-- 			${ratingList.get(trainerList.get(idx).userid) }점<br> --%>
<%-- 		</c:if> --%>
<%-- 		회원 수 : ${membershipList.get(trainerList.get(idx).userid) }명 --%>
<!-- 	</div> -->
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
	<script>
			$(document).ready(function() {		
				var formObj = $("form[role='form']");
				var selectList = document.getElementById("trainer");
				var hiddenTotalcnt = document.getElementById("totalcnt");
				
				$(".totalcnt10").on("click", function() {
					if (!confirm("PT 이용권 10회를 신청하시겠습니까? (" + selectList.options[selectList.selectedIndex].text + ")")) return;
					alert("신청완료");
					hiddenTotalcnt.setAttribute("value", "10");
					formObj.attr("action", "/apply/applyPT");
					formObj.submit();
				});
				
				$(".totalcnt20").on("click", function() {
					if (!confirm("PT 이용권 20회를 신청하시겠습니까? (" + selectList.options[selectList.selectedIndex].text + ")")) return;
					alert("신청완료");
					hiddenTotalcnt.setAttribute("value", "20");
					formObj.attr("action", "/apply/applyPT");
					formObj.submit();
				});
				
				$(".totalcnt30").on("click", function() {
					if (!confirm("PT 이용권 30회를 신청하시겠습니까? (" + selectList.options[selectList.selectedIndex].text + ")")) return;
					alert("신청완료");
					hiddenTotalcnt.setAttribute("value", "30");
					formObj.attr("action", "/apply/applyPT");
					formObj.submit();
				});
			});
		</script>
</body>
</html>