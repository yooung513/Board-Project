<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>게시글</title>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css">
</head>

<body>
	<%@ include file="common/header.jsp" %>
	
	<div class="container">
		<div class="emptyContainer"></div>
		
		<div class="listContainer">
			<input type="button" class="listBtn" id="listBtn" value="목 록">
		</div>
		
		<div class="boardContainer">
			<table>
				<tr> 
					<th class="tInner"> 제 목 </th>
					<td class="tInner" id="tTitle"> ${board.title } </td>
					<th class="tInner"> 날 짜 </th>
					<td> ${board.displayDate } </td>
				</tr>
				
				<tr> 
					<th class="tInner"> 작 성 자 </th>
					<td class="tInner"> ${board.writer } </td>
					<th class="tInner"> 추 천 수 </th>
					<td> ${board.recommendation} </td>
				</tr>
				
				<tr> 
					<td class="tContent" colspan="4"> ${board.content } </td>
				</tr>
			</table>
		</div>
		
		<div class="btnContainer">
			<c:choose>
				<c:when test="${sessionScope.user.userId eq board.userId}">
					<input type="button" class="updateBtn" id="updateBtn" value="수 정">
					<input type="button" class="deleteBtn" id="deleteBtn" value="삭 제"> 
				</c:when>
				<c:otherwise>
					<input type="button" class="rcmBtn" id="rcmBtn" value="추 천">
				</c:otherwise>
			</c:choose>
		</div>
		
		<div class="commentContainer">
			<div class="insertCmtContainer">
				<form id="commentFrm">
					<input type="hidden" name="boardId" value="${board.boardId }">
					<input type="hidden" name="userId" value="${sessionScope.user.userId }">
					<input type="hidden" name="deptCode" value="${sessionScope.user.deptCode }">
					<input type="hidden" name="rankCode" value="${sessionScope.user.rankCode }">
					<textarea id="comment" class="cmtText" name="comment" placeholder="댓글을 입력하세요. (한글 200자)" maxlength="200"></textarea>
					<div class="cmtCounter">
						<span id="currentCount">0</span> / 200자
					</div>
					<input type="button" class="cmtBtn" id="cmtBtn" value="등 록"> 
				</form>
			</div>
			
			<div class="readCmtContainer">
				<c:forEach items="${commentList}" var="comment">
					<div class="cmtItem" data-comment-id="${comment.commentId}">
						<div class="cmtContent">
							<span class="readCmtText">${comment.comment}</span>
							<c:if test="${sessionScope.user.userId eq comment.userId}">
								<span class="readCmtBtn">
									<input type="button" class="updateBtn updateCmtBtn" value="수 정">
									<input type="button" class="deleteBtn deleteCmtBtn" value="삭 제">
								</span>
							</c:if>
						</div>
						<div class="readCmtWriter">
							<span class="nameSpan">${comment.writer}</span> |
							<span class="dateSpan">${comment.displayDate}</span>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<script>
		var boardId = "${board.boardId }";
		var deptCode = '${sessionScope.user.deptCode}';
		var rankCode = '${sessionScope.user.rankCode}';
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/board.js"></script>
</body>
</html>