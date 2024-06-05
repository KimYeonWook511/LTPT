<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | 상단 메뉴</title>
<style>
	.navbar-brand {
		background-image: url("/resources/mainImg/logo.png");
		background-size: cover;
		background-position: center;
		margin-left: 10px;
	}
</style>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/" style="width: 100px;"></a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav"  style="margin-left: 10px;">
				<li><a href="/">메인으로</a></li>
				<c:if test="${loginVO.authority == 'member' }">
					<li><a href="/myPT/list">나의 PT 피드백</a></li>
				</c:if>
				<c:if test="${loginVO.authority == 'trainer' }">
					<li><a href="/myPT/list">트레이너 PT 관리</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							회원 관리
						</a>
						<ul class="dropdown-menu">
							<li><a href="/trainer/member/list">회원 리스트</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test="${loginVO.authority == 'admin' }">
					<li><a href="/myPT/list">관리자 PT 관리</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							PT신청 관리
						</a>
						<ul class="dropdown-menu">
							<li><a href="/admin/apply/applyPTList">PT 신청 리스트</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							트레이너 관리
						</a>
						<ul class="dropdown-menu">
							<li><a href="/admin/trainer/list">트레이너 리스트</a></li>
							<li><a href="/admin/apply/applyTrainerList">트레이너 지원현황</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							회원 관리
						</a>
						<ul class="dropdown-menu">
							<li><a href="/admin/member/list">회원 리스트</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test="${loginVO.authority == 'member' }">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							신청하기
						</a>
						<ul class="dropdown-menu">
							<c:if test="${loginVO.authority == 'member' }">
								<li><a href="/apply/applyPT">PT 신청하기</a></li>
								<li><a href="/apply/viewApplyPT">PT 신청서 보기</a></li>
							</c:if>
							<li><a href="/apply/applyTrainer">트레이너 신청하기</a></li>
							<li><a href="/apply/viewApplyTrainer">트레이너 신청서 보기</a></li>
						</ul>
					</li>
				</c:if>
			</ul>
			<c:if test="${empty loginVO}">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" 
							aria-expanded="false" style="font-weight: bold; font-size: 15px; padding-left: 20px; padding-right: 20px;">
							접속하기
						</a>
						<ul class="dropdown-menu">
							<li><a href="/user/login">로그인</a></li> 
							<li><a href="/user/join">회원가입</a></li>
						</ul>
					</li>
				</ul>
			</c:if>
			<c:if test="${not empty loginVO}">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdwon">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" 
							aria-expanded="false" style="font-weight: bold; font-size: 15px; padding-left: 20px; padding-right: 20px;">
							<c:if test="${loginVO.authority == 'member' }">
								${loginVO.username } 회원님
							</c:if>
							<c:if test="${loginVO.authority == 'trainer' }">
								${loginVO.username } 트레이너님
							</c:if>
							<c:if test="${loginVO.authority == 'admin' }">
								${loginVO.username } 관리자님
							</c:if>
						</a>
						<ul class="dropdown-menu">
<!-- 							<li><a href="/user/update">정보수정</a></li>  -->
							<li><a href="/user/logout">로그아웃</a></li> 							
						</ul>
					</li>
				</ul>
			</c:if>
		</div>
	</nav>
</body>
</html>