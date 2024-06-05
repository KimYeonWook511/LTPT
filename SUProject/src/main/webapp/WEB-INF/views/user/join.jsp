<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 회원가입</title>
<!-- 라이브러리 등록 - jQuery, Bootstrap : CDN 방식-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/layout/navbar.jsp" flush="false"/>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div style="padding-top: 20px;">
				<form method="post">
					<h3 style="text-align: center;">회원가입 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userid" maxlength="20" id="userid" pattern="^[a-zA-Z0-9]+$">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="userpw" maxlength="20" id="userpw">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호 확인" name="confirmuserpw" maxlength="20" id="confirmuserpw">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" name="username" maxlength="20" id="username">
					</div>
					<div>
						<input type="tel" class="form-control" placeholder="전화번호 / '-'는 빼고 입력해 주세요" name="callnum" maxlength="11" id="callnum" 
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
					</div>
					<br>
					<div class="form-group" style="text-align: center;">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active">
<%-- 								<c:out value="${dto.gender != '여자' ? 'active' : ''}"/>>  --%>
								<input type="radio" name="usergender" autocomplete="off" value="남자" id="male" checked>남자
<%-- 								<c:out value="${dto.gender != '여자' ? 'checked' : ''}"/>>			 --%>
							</label>
							<label class="btn btn-primary">
<%-- 								<c:out value="${dto.gender == '여자' ? 'active' : ''}"/>> --%>
								<input type="radio" name="usergender" autocomplete="off" value="여자" id="female">여자
<%-- 								<c:out value="${dto.gender == '여자' ? 'checked' : ''}"/>> --%>
							</label>
						</div>
					</div>
					<br><br>
					<input type="submit" class="btn btn-primary form-control" value="회원가입">
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	<script>
		if ('${joinResult}' == 'fail_id') {
			alert("이미 존재하는 아이디입니다.");
			
		} else if ('${joinResult}' == 'fail_pw') {
			alert("비밀번호를 확인해 주세요.");
			
		} else if ('${joinResult}' == 'empty_id') {
			alert("아이디를 입력해 주세요.");
			
		} else if ('${joinResult}' == 'empty_pw') {
			alert("비밀번호를 입력해 주세요.");
			
		} else if ('${joinResult}' == 'empty_name') {
			alert("이름을 입력해 주세요.");
	
		} else if ('${joinResult}' == 'empty_callnum') {
			alert("전화번호를 입력해 주세요.");
			
		};
		
		document.getElementById("userid").value = '${dto.userid}';
		document.getElementById("username").value = '${dto.username}';
		document.getElementById("callnum").value = '${dto.callnum}';
	</script>
</body>
</html>