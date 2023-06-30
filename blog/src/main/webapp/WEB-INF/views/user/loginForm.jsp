<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>

	<%@ include file="../layout/header.jsp"%>

	<div class="container">
		<form action="/auth/loginProc" method="post">

			<div class="form-group">
				<label for="username">Username:</label> <input type="text" class="form-control" placeholder="Enter Username" name="username" id="username">
			</div>

			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" name="password" id="password">
			</div>

			<button id="btn-login" class="btn btn-primary">로그인</button>
			
			<!-- ========================== 카카오 Open Auth (대신 인증) Oauth 사용  ========================== -->
			<!-- 순서! 카카오개발자센터 로그인 -> 내 에플리케이션 생성 -> RestApi키 저장 -> 플랫폼설정 redirect url 설정 -> 카카오톡 로그인 활성화 -> 동의항목 설정 -> 문서 (카카오 로그인 rest Api) -> 요청 url 복사 -->
			<!-- https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI} -->
			<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=b3dccf45d727c9e88401dedd266d5e08&redirect_uri=http://localhost:8000/auth/kakao/callback"><img
				src="/image/kakao_button.png" style="height: 40px"></a>

		</form>

	</div>

	<%@ include file="../layout/footer.jsp"%>

</body>
</html>