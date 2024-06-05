<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LTPT | PT 피드백 등록</title>
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
	<c:if test="${not empty loginVO}">
		<jsp:include page="/WEB-INF/views/layout/navbar.jsp" flush="false" />
		<%-- 
			enroll 테이블에 관련 레코드 없으면 처리하기
			<c:if test="${loginVO.authority == 'member' }">
				<script>
					alert("현재 신청한 PT가 없습니다.");
					location.href = "/apply/applyPT";
				</script>
			</c:if> 
		--%>
		<form action="/myPT/write" method="post" enctype="multipart/form-data">
			<table class="myPT_videoFile">
				<tr>
					<td><input type="file" name="video1" accept="video/mp4" class="file_input"></td>
					<td><input type="file" name="video2" accept="video/mp4" class="file_input"></td>
					<td><input type="file" name="video3" accept="video/mp4" class="file_input"></td>
					<td><input type="file" name="video4" accept="video/mp4" class="file_input"></td>
					<td><input type="file" name="video5" accept="video/mp4" class="file_input"></td>
				</tr>
			</table>
			<div class="myPT_videoMain">
				<span class="myPT_video" style="display: table-cell; text-align: center; vertical-align: middle;">썸네일</span>
				<span class="myPT_video" style="display: table-cell; text-align: center; vertical-align: middle;">썸네일</span>
				<span class="myPT_video" style="display: table-cell; text-align: center; vertical-align: middle;">썸네일</span>
				<span class="myPT_video" style="display: table-cell; text-align: center; vertical-align: middle;">썸네일</span>
				<span class="myPT_video" style="display: table-cell; text-align: center; vertical-align: middle;">썸네일</span>
			</div>
			<hr>
			<div class="main">
				<div class="column-main">
					<div class="myPT-title">
						<div class="myPT-name" style="font-size: 20px;">
							피드백 게시판 제목<br>
							<input type="text" name="title" maxlength="50" style="width: 800px; height: 30px;">
						</div>
						<div class="myPT-status"></div>
						<hr class="hr-bar">
					</div>
					<div class="myPT-info">
						<table class="info-table">
							<tr>
								<th class="myPT_info_th">운동내용</th>
								<td><textarea rows="10" cols="95" name="content" maxlength="5000"></textarea></td>
							</tr>
							<tr>
								<th class="myPT_info_th">트레이너</th>
								<td> 
									<c:if test="${!trainerList.isEmpty() }">
										<select id="trainer" name="trainer">
											<c:forEach items="${trainerList }" var="trainer">
												<option value="${trainer.trainer }">${nameList.get(trainer.trainer) }(${trainer.trainer })</option>
											</c:forEach>
										</select><br><br><br>
									</c:if>
								</td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn btn-default pull-right">피드백 게시판 등록</button></td>
							</tr>
						</table>
					</div>	
				</div>
				<div class="column-side">
					<div id="map" style="width:400px;height:400px;"></div>
				</div>
			</div>
		</form>
		<script>
			let input = document.querySelectorAll('[type="file"]');
			let preview = document.querySelectorAll('.myPT_video');
			for(let i=0; i<input.length; i++) {
			    input[i].addEventListener('change', function(e) {
			        let file = e.target.files;
			        if(file.length) {
			            let reader = new FileReader();
			            reader.readAsDataURL(file[0]);
			            reader.onload = function() {
			                let dataUrl = reader.result;
			                preview[i].innerHTML = '<video src="' + dataUrl + '" style="height:'+ $('.myPT_video').height() +'px; width:'+ $('.myPT_video').width() +'px;">';
			            }
			        } else {
			            preview[i].innerHTML = '썸네일';
			        }
			    })
			}
		</script>
		<script>
			$(document).ready(function(){
				var h = 350;
				var w = ($(window).width())/5-5;
				$('.myPT_videoFile').css({width:$(window).width()+'px'});
				$('.file_input').css({width:w+'px'});
				$('.myPT_videoMain').css({height:h+'px', width:$(window).width()+'px'});
				$('.myPT_video').css({height:h+'px', width:w+'px'});
			});
		</script>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
	</c:if>
</body>
</html>