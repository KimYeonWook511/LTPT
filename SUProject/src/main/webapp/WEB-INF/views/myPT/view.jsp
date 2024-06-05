<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | PT 피드백</title>
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
		<c:if test="${msg == '평점 등록이 완료되었습니다.' }">
			<script>
				alert('${msg}');
			</script>
		</c:if>
		<jsp:include page="/WEB-INF/views/layout/navbar.jsp" flush="false"/>
		<div class="myPT_videoMain">
			${notVideo = true;'' }
			<c:forEach var="video" items="${videoList }">
				<span class="myPT_video" style="display:inline-block;">
					<video class="myPT_videotag" src="/resources/video/${video.getFilenameURL() }" controls></video>
					<c:if test="${loginVO.authority.equals('member') && feedbackProgress == '피드백 완료'}">
						<a href="/myPT/feedback?bno=${boardVO.bno }&videono=${video.videono }" class="btn btn-default">피드백 보기</a>
					</c:if>
					<c:if test="${loginVO.authority.equals('trainer') }">
						<a href="/myPT/trainer/write?bno=${boardVO.bno }&videono=${video.videono }" class="btn btn-default">피드백 작성</a>
					</c:if>
					<c:if test="${loginVO.authority.equals('admin') }">
						<a href="/myPT/trainer/write?bno=${boardVO.bno }&videono=${video.videono }" class="btn btn-default">피드백 관리</a>
					</c:if>
				</span>
				${notVideo = false;'' }
			</c:forEach>
			<c:if test="${notVideo == true }">
				<div class="myPT_notvideo"><div class="myPT_notvideo_inner">등록된 비디오가 없습니다.</div></div>
			</c:if>
		</div>
		<hr>
		<form role="form" method="post">
			<div class="main">
				<div class="column-main">
					<div class="myPT-title">
						<input type="hidden" name="bno" value="${boardVO.bno }">
						<div class="myPT-name">
							${boardVO.title }
							<c:if test="${(feedbackProgress == '피드백 완료') && (boardVO.rating == 0) && (loginVO.authority == 'member') && !videoList.isEmpty()}">
								<span class="starSpan">
									<img id="star1" onmouseover=star(1) onmouseout=nostar(1) onclick=postRating(1) src="/resources/img/nostar.png">
									<img id="star2" onmouseover=star(2) onmouseout=nostar(2) onclick=postRating(2) src="/resources/img/nostar.png">
									<img id="star3" onmouseover=star(3) onmouseout=nostar(3) onclick=postRating(3) src="/resources/img/nostar.png">
									<img id="star4" onmouseover=star(4) onmouseout=nostar(4) onclick=postRating(4) src="/resources/img/nostar.png">
									<img id="star5" onmouseover=star(5) onmouseout=nostar(5) onclick=postRating(5) src="/resources/img/nostar.png"> 
								</span>
								<input type="hidden" id="rating" name="rating" value="${boardVO.rating }">
							</c:if>
							<c:if test="${(feedbackProgress == '피드백 완료') && (boardVO.rating != 0) && (loginVO.authority == 'member')}">
								<span class="starSpan">
									<c:forEach var="i" begin="1" end="5">
										<c:if test="${i <= boardVO.rating}">
											<img src="/resources/img/star.png">
										</c:if>
										<c:if test="${i > boardVO.rating}">
											<img src="/resources/img/nostar.png">
										</c:if>
									</c:forEach>
								</span>
							</c:if>
						</div>
						<div class="myPT-status">
							<div class="status-viewCount">
								<c:if test="${!videoList.isEmpty() }">
									피드백상태 : ${feedbackProgress }
								</c:if>
							</div>
							<div class="status-write">
								<span class="status-writer">작성자 : ${enrollVO.membername }</span>
								<span class="status-writeDate">작성일 : <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate }"/></span>
							</div>
							<hr class="hr-bar">
						</div>
					</div>
					<div class="myPT-info">
						<table class="info-table">
							<tr>
								<th class="myPT_info_th">운동내용</th>
								<td>${boardVO.getReplContent() }</td>
							</tr>
						</table>
						<c:if test="${loginVO.authority.equals('admin') || loginVO.userid.equals(boardVO.writer)}">
							<button type="button" class="btn btn-default pull-right delete">삭제</button>
							<button type="button" class="btn btn-default pull-right modify">수정</button>	
						</c:if>
						<button type="button" class="btn btn-default pull-right list">목록</button>
					</div>
				</div>
				<div class="column-side">
					<div class="member-info">
						<div class="member-info-title">
							게시물 회원 정보
						</div>
						<table class="info-table">
							<tr>
								<th class="myPT_info_th">회원</th>
								<td>${enrollVO.membername }</td>
							</tr>
							<tr>
								<th class="myPT_info_th">트레이너</th>
								<td>${enrollVO.trainername }</td>
							</tr>
							<tr>
								<th class="myPT_info_th">PT 등록일</th>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${enrollVO.regdate }"/></td>
							</tr>
							<tr>
								<th class="myPT_info_th">총 PT 신청 수</th>
								<td>${enrollVO.totalcnt }회</td>
							</tr>
							<tr>
								<th class="myPT_info_th">완료된 PT 횟수</th>
								<td>${enrollVO.completecnt }회</td>
							</tr>
							<tr>
								<th class="myPT_info_th">잔여 PT 횟수</th>
								<td>${enrollVO.totalcnt - enrollVO.completecnt }회</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>
		<script>
			$(document).ready(function(){
				var h = 350;
				var w = ($(window).width())/5-4;
				$('.myPT_videoMain').css({height:h+'px', width:$(window).width()+'px'});
				$('.myPT_notvideo').css({height:h+'px', width:$(window).width()+'px'});
				$('.myPT_video').css({height:h+'px', width:w+'px'});
				$('.myPT_videotag').css({height:h-30+'px', width:w+'px'});
			});
		</script>
		<script>
			$(document).ready(function() {
				var formObj = $("form[role='form']");
				console.log(formObj);
				
				$(".modify").on("click", function() {
					formObj.attr("action", "/myPT/modify");
					formObj.attr("method", "get");
					formObj.submit();
				});
				
				$(".delete").on("click", function() {
					if (!confirm("정말로 삭제하시겠습니까?")) return;
					formObj.attr("action", "/myPT/delete");
					formObj.submit();
				});
				
				$(".list").on("click", function() {
					self.location="/myPT/list";
				});
			});
		</script>
		<c:if test="${(feedbackProgress == '피드백 완료') && (loginVO.authority == 'member') && (boardVO.rating == 0) && !videoList.isEmpty()}">
			<script>
				alert("피드백이 완료되었습니다.\n평점을 등록해주세요.");
				var i;
				var imgTag;
				var complete = false;
				var hiddenRating = document.getElementById("rating");
				var formObj = $("form[role='form']");
				
				function star(rat) {
					
					if (complete) return;
					
					for (i = 1; i <= rat; i++) {
						imgTag = document.getElementById("star" + i);
						imgTag.src = "/resources/img/star.png";
					}
				}
				
				function nostar(rat) {
					
					if (complete) return;
					
					for (i = 1; i <= rat; i++) {
						imgTag = document.getElementById("star" + i);
						imgTag.src = "/resources/img/nostar.png";
					}
				}
				
				function completeFunc(rat) {
					star(rat);
					complete = true;
				}
				
				function postRating(rat) {
					if (complete == true || !confirm("평점 " + rat + "점으로 등록하시겠습니까?\n(수정이 불가능하니 주의해주세요!)")) return;
					completeFunc(rat);
					hiddenRating.setAttribute("value", rat);
					formObj.attr("action", "/myPT/rating");
					formObj.submit();
				}
			</script>
		</c:if>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>