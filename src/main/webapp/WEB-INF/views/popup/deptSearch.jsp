<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>부서 검색</title>
		<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/deptSearch.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/deptSearch.css">
	</head>

<style>
	
</style>
	
<body>
	<div class="container">
		<div class="titleContainer">
			<p class="titleP" id="titleP"> 부 서 검 색 </p>
		</div>
		
		<div class="searchContainer">
			<input type="text" class="searchIpt" id="searchIpt"/>
			<input type="button" class="searchBtn" id="searchBtn" value="검 색" />
		</div>
		
		<div class="deptContainer">
			<div class="treeContainer" id="treeContainer"></div>
			<div id="noResult" class="noResult"> 검색 결과가 없습니다. </div> 
		</div>
		
		<div class="notionContainer"> ※해당 부서가 없는 경우 관리자에게 문의 부탁 드립니다. (061-931-5372) </div>
		
		<div class="btnContainer">
			<input type="button" class="cancelBtn" id="cancelBtn" value="취 소" />
			<input type="button" class="inputBtn" id="inputBtn" value="확 인" />
		</div>
	</div>
</body>
</html>