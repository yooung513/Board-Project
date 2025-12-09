<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title> 회원가입 </title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signup.css">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
</head>

<body>
	<div class="headerContainer">
		<a href="http://localhost:8080/login">
			<img class="logo" src="${pageContext.request.contextPath}/resources/images/collaboration.png" />
		</a>
	</div>

	<div class="container">
		<div class="titleContainer">
			회 원 가 입
		</div>
		
		<div class="signupContainer">
			<form class="signupFrm" id="signupFrm">
			
				<div class="inputBox">
					<div class="requireBox"> * </div>
					<label for="kdnNumber">사 번</label>
					<input type="text" id="kdnNumber" name="kdnNumber" class="inputMedium" />
					<input type="button" class="inputBtn" id="chkNumBtn" value="중 복 확 인"/> 
				</div>
				
				<div class="inputBox">
					<div class="requireBox pw"> * </div>
					<label for="password" id="pwLabel">비 밀 번 호</label>
					
					<div class="guideContainer">
						<input type="password" id="password" name="password" class="inputLong" />
						<span class="guideText">* 9-15자리 영어, 숫자, 특수문자 사용 필수</span>
					</div>
				</div>
				
				<div class="inputBox">
					<div class="requireBox"> * </div>
					<label for="name">이 름</label>
					<input type="text" id="name" name="name" class="inputLong" />
				</div>
				
				<div class="inputBox">
					<div class="requireBox"> * </div>
					<label for="phone">전 화 번 호</label>
					
					<div class="phoneContainer">
						<select id="phone1" name="phone1" class="phone" >
							<option value="010" selected> 010 </option>
							<option value="011"> 011 </option>
							<option value="016"> 016 </option>
							<option value="017"> 017 </option>
							<option value="018"> 018 </option>
							<option value="019"> 019 </option>
						</select> 
						<span class="phoneText">-</span> 
						<input type="text" id="phone2" name="phone2" class="phone" pattern="\d{3,4}" title="숫자만 입력하세요." maxlength="4" /> 
						<span class="phoneText">-</span>
						<input type="text" id="phone3" name="phone3" class="phone" pattern="\d{4}" title="숫자만 입력하세요." maxlength="4" />
					</div>
				</div>
				
				<div class="inputBox">
					<div class="requireBox"> * </div>
					<label for="email">이 메 일</label>
					
					<div>
						<input type="text" id="emailId" name="emailId" class="email" required />
						<span class="emailText"> @ </span>
						<input id="emailAdd" name="emailAdd" list="emailList" class="email" placeholder="직 접 입 력" required/>
						<datalist id="emailList" class="email">
							<option value="kdn.com">
							<option value="naver.com">
							<option value="gmail.com">
						</datalist>
					</div>
				</div>
				
				<div class="inputBox">
					<div class="requireBox"> &nbsp; </div>
					<label for="dept">부 서</label>
					<input type="text" id="dept" name="dept" class="inputMedium" placeholder="부서를 검색하세요." readonly />
					<input type="hidden" id="deptCode" name="deptCode" />
					<input type="button" class="inputBtn" id="deptBtn" value="검 색" /> 
				</div>
				
				<div class="inputBox">
					<div class="requireBox"> &nbsp; </div>
					<label for="rank">직 위</label>
					<select id="rank" class="rank" name="rankCode">
						<option value=""> === 선 택 === </option>
						<c:forEach var="rank" items="${rankList }">
							<option value="${rank.rankCode }">
								${rank.rankName }
							</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="buttonBox">
					<input type="button" class="cancelBtn" id="cancelBtn" value="취 소" />
					<input type="button" class="inputBtn" id="inputBtn" value="회 원 가 입" />
				</div>
			</form>
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/js/signup.js"></script>
</body>
</html>