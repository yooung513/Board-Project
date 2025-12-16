<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Header</title>
</head>

<style>
	@font-face {
	    font-family: 'Paperozi400';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/2408-3@1.0/Paperlogy-4Regular.woff2') format('woff2');
	    font-weight: 400;
	    font-display: swap;
	}
	
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}

	.headerContainer {
		width: 1800px;
		height: 100px;
		display: flex;
		align-items: center;
		position: relative;
		padding: 0 50px;
	}
	
	.logoWrapper {
		position: absolute;
		left: 50%;
		transform: translateX(-50%);
	}

	.logo {
		width: 300px;
		height: auto;
		cursor: pointer; /* ✅ 추가 */
	}
	
	.userInfo {
		font-family: 'Paperozi400';
		display: flex;
		align-items: center;
		gap: 20px;
		margin-left: auto;
	}
	
	.welcomeText {
		font-size: 20px;
	}
	
	.logoutBtn {
		font-family: 'Paperozi400';
		font-weight: bold;
    	font-size: large;
    	background-color: rgb(227, 6, 19);
    	color: rgb(255, 255, 255);
    	width: 150px;
		height: 30px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		transition: background-color 0.2s;
		display: flex;
	    justify-content: center;
	    align-items: center;
	}
	
	.logoutBtn:hover {
		color: rgb(255, 255, 255);
	 	background-color: rgb(150, 4, 11);
	}
</style>

<body>
	<div class="headerContainer">
		<div class="logoWrapper">
			<a href="/" id="logoLink">
				<img class="logo" src="${pageContext.request.contextPath}/resources/images/collaboration.png" />
			</a>
		</div>
		
		<c:if test="${not empty sessionScope.user}">
			<div class="userInfo">
				<span class="welcomeText">${sessionScope.user.name}님 환영합니다!</span>
				<button class="logoutBtn" onclick="logout()">로그아웃</button>
			</div>
		</c:if>
	</div>
	
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var logoLink = document.getElementById('logoLink');
			if(logoLink) {
				logoLink.addEventListener('click', function(e) {
					e.preventDefault();
					sessionStorage.removeItem('searchParams');
					window.location.href = '/';
				});
			}
		});
	
		function logout() {
			sessionStorage.removeItem('searchParams'); // ✅ 로그아웃 시에도 삭제
			location.href = "/logout";
		}
	</script>
</body>
</html>