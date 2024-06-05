<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 로그인</title>
<!-- 라이브러리 등록 - jQuery, Bootstrap : CDN 방식-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/navbar.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/layout/navbar.jsp" flush="false"/>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div style="padding-top: 20px;">
				<form method="post" action="/user/loginPOST">
					<h3 style="text-align: center;">로그인 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userid" maxlength="20" id="userid">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="userpw" maxlength="20">
					</div>
					<div class="form-group" style="text-align: right;">
					<input type="submit" class="btn" value="로그인">
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
<script>
	if ('${loginResult}' == -2) {
		alert("아이디가 존재하지 않습니다.");
	} else if ('${loginResult}' == -1) {
		alert("비밀번호가 일치하지 않습니다.");
		document.getElementById("userid").value = '${dto.userid}';
	} else if ('${loginResult}' == '로그인 필요') {
		alert("로그인이 필요합니다.");
		location.href = "/user/login"; // url 새로고침
	}
	
</script>
</html>