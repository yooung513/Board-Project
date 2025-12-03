<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title> 로그인 </title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
</head>

<body>
	<div class="container">	
		<div class="mainContainer">
			<div class="logoContainer">
				<img class="logo" src="${pageContext.request.contextPath}/resources/images/collaboration.png" />
			</div>
			
			<div class="loginContainer">
				<form class="loginFrm" id="loginFrm" action="/login" method="post">
					<div class="inputBox">
						<label for="kdnNumber"> 사 번 </label>
						<input type="text" id="kdnNumber" name="kdnNumber" placeholder="사번을 입력하세요." maxlength="6" required />
					</div>
					
					<div class="inputBox">
						<label for="password"> 비 밀 번 호 </label>
						<input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요." required />
					</div>
				
					<div class="loginBtnBox">
						 <input type="button" class="loginBtn" id="loginBtn" value="로 그 인">
					</div>
				</form>
			</div>
			
			<div class="signUpContainer">
				<div class="signUpBox">
					<a href="/signup">회 원 가 입</a>
				</div>
			</div>
		</div>
		
		<div class="imageContainer">
			<img class="kdn" src="${pageContext.request.contextPath}/resources/images/kdn.png"/>
			<!-- <img class="kdn" src="https://github.com/yooung513/data-center/blob/main/images/kdn.png?raw=true"/> -->
		</div>
	</div>
</body>
</html>