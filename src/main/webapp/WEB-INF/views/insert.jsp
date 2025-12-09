<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>게시글 생성</title>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/insert.css">
</head>

<body>
	<%@ include file="common/header.jsp" %>

	<div class=container>
		<div class=emtpyContainer> </div>
		
		<div class="inputContainer">
			<form id="boardFrm">
				<input type="hidden" name="userId" value="${sessionScope.user.userId}">
				<input type="hidden" name="deptCode" value="${sessionScope.user.deptCode}">
				<input type="hidden" name="rankCode" value="${sessionScope.user.rankCode}">
				
				<table>
					<tr>
						<th class="tTitle"> 제 목 </th>
						<td class="tTitle">
							<input type="text" class="tInner" id="title" name="title" placeholder="제목을 입력하세요. (100자 이내)" maxlength="100">
						</td>
					</tr>
					<tr>
						<th class="tWriter"> 작 성 자 </th>
						<td class="tWriter">
							<input type="text" class="tInner" value="${writer }" readonly> 
						</td>
					</tr>
					<tr>
						<th class="tContent"> 내 용 </th>
						<td class="tContent">
							<textarea class="tInner" id="content" name="content" placeholder="내용을 입력하세요. (5000자 이내)" maxlength="5000"></textarea>
						</td>
					</tr>
				</table>
			
				<div class="btnContainer">
					<input type="button" class="cancelBtn" id="cancelBtn" value="취 소" /> 
					<input type="button" class="insertBtn" id="insertBtn" value="등 록" /> 
				</div>
			</form>
		</div>
	</div>
	
	<script>
		var user = "${sessionScope.user.name}";
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/insert.js"></script>
</body>
</html>