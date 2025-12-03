<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>KDN 커뮤니티</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css?after">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/daterangepicker.css">

	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/moment.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/daterangepicker.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
	
<body>
	<%@ include file="common/header.jsp" %>

	<div class="container">
		<form id="searchFrm">
			<div class="searchContainer">
				<div class="keywordContainer">
					<input type="text" class="dateRange" id="dateRange" name="dateRange">
					
					<select class="inputBox" id="keywordType" name="keywordType">
						<option value="" selected> 전 체 </option>
						<option value="title"> 제 목 </option>
						<option value="writer"> 작 성 자 </option>
						<option value="content"> 내 용 </option>
					</select>
					<input type="text" id="keyword" name="keyword" class="inputBox" >
					<input type="button" value="검 색" class="keywordBtn" id="keywordBtn">
				</div>
			</div>
			
			<div class="sortContainer">
				<select class="inputBox" id="sortSel" name="sort">
					<option value="latest" selected> 최 신 순 </option>
					<option value="recommend"> 추 천 순 </option>
					<option value="oldest"> 과 거 순 </option>
				</select>
			</div>
		</form>
		
		<div class="boardContainer">
			<table class="boardTable">
				<thead>
					<tr>
						<th class="tNum"> 번 호 </th>
						<th class="tTitle">	제 목 </th>
						<th class="tWriter"> 작 성 자 </th>
						<th class="tDate"> 날 짜 </th>
						<th class="tRcm"> 추 천 수 </th>
					</tr>
				</thead>
				
				<tbody id="tList">
					<c:choose>
						<c:when test="${empty boardList }">
							<tr>
								<td class="noResult" colspan="5"> 검색 결과가 없습니다. </td>
							</tr>
						
						</c:when>

						<c:otherwise>
							<c:forEach items="${boardList }" var="board" varStatus="num">
								<tr class="row-click" data-id="${board.boardId} ">
									<td class="tNum"> ${pagination.totalCount - ((pagination.currentPage - 1) * 10) - num.index} </td>
									<td class="tTitle"> ${board.title} </td>
									<td class="tWriter"> ${board.writer} </td>
									<td class="tDate">
										<fmt:parseDate value="${board.displayDate }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" />
										<fmt:formatDate value="${parsedDate }" pattern="yyyy-MM-dd" />
									</td>
									<td class="tRcm"> ${board.recommendation} </td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
				
			</table>
		</div>
		
		<div class="btnContainer">
				<input type="button" class="writeBtn" id="writeBtn" value="글 쓰 기" />
		</div>
		
		<div class="pageContainer">
		    <div class="krds-pagination" id="pagingBox">
		    </div>
		</div>
	</div>
</body>
</html>