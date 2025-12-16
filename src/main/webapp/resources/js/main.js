$(function(){

	var urlParams = new URLSearchParams(window.location.search);
	var savedParams = sessionStorage.getItem('searchParams');
	
	var params = urlParams.toString() ? urlParams : (savedParams ? new URLSearchParams(savedParams) : null);
	
	if(params) {
		if(params.get('sort')) {
			$("#sortSel").val(params.get('sort'));
		}
		
		if(params.get('keywordType')) {
			$("#keywordType").val(params.get('keywordType'));
		}
		if(params.get('keyword')) {
			$("#keyword").val(params.get('keyword'));
		}
		
		if(params.get('dateRange')) {
			$("#dateRange").val(params.get('dateRange'));
		}
		
		if(params.get('page')) {
			currentPage = parseInt(params.get('page'));
		}
		
		searchBoard(currentPage);
	} else {
		renderPaging(totalPages, currentPage);
	}

	
	
	// 글쓰기
	$("#writeBtn").click(function(){
		location.href='insert';
	})
	
	// 게시글 이동
	$(document).on("click", ".row-click", function() {
		var boardId = $(this).data("id");
		var searchParams = $("#searchFrm").serialize() + "&page=" + currentPage;
		
		sessionStorage.setItem('searchParams', searchParams);
		
		location.href = "/board/" + boardId;
	});
	
	// 검색
	var now = new Date();
	$("#dateRange").daterangepicker({
	    "locale": {
	        "format": "YYYY-MM-DD",
	        "separator": " ~ ",
	        "applyLabel": "확인",
	        "cancelLabel": "닫기",
	        "fromLabel": "From",
	        "toLabel": "To",
	        "customRangeLabel": "직접 선택",
	        "weekLabel": "W",
	        "daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
	        "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
	    },
	    ranges: {
	        '오늘': [moment(), moment()],
	        '어제': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	        '지난 일주일': [moment().subtract(6, 'days'), moment()],
	        '지난 한 달': [moment().subtract(29, 'days'), moment()],
	        '이번 달': [moment().startOf('month'), moment().endOf('month')],
	        '지난달': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	    },
	    
	    "startDate": new Date(now.setFullYear(now.getFullYear() - 1)),
	    "endDate": new Date(),
	    "maxDate": new Date(),
	    "drops": "auto"
	});
	 
	$("#dateRange").on('apply.daterangepicker', function(ev, picker) {
		currentPage = 1;
		searchBoard();
	});
	
	$("#keywordBtn").click(function(){
		currentPage = 1;
		searchBoard();
	});
	
	$("#keyword").keyup(function(e){
		if(e.which === 13) {
			$("#keywordBtn").click();
		}
	});
	
	$("#keywordType").change(function(){
		if( $("#keyword").val().trim() != "" ) {
			currentPage = 1; 
			searchBoard();
		}
	});
	
	$("#sortSel").change(function(){
		currentPage = 1; 
		searchBoard();
	})
	
	// 초기 페이지 생성
	renderPaging(totalPages, currentPage);
})

function searchBoard(page) {
	if(page) currentPage = page;
	
	var formData = $("#searchFrm").serialize() + "&page=" + currentPage;
	
	$.ajax({
		url: "/search",
		type: "GET", 
		data: formData,
		success: function(result) {
			renderBoardList(result.boardList, result.totalCount);
			renderPaging(result.totalPages, result.currentPage);
			totalPages = result.totalPages;
		},
		error: function() {
			alert("검색에 실패했습니다.");
		}
	});
}

function renderBoardList(boardList, totalCount) {
	var html = "";
	
	if(boardList.length === 0) {
		html = '<tr> <td class="noResult" colspan="5"> 검색 결과가 없습니다. </td> </tr>'
	} else {
		$.each(boardList, function(index, board) {
			var rowNum = totalCount - ((currentPage - 1) * 10) - index;
			var date = board.displayDate.substring(0, 10);
			
			var isLastRow = (index === boardList.length - 1) ? 'last-row' : '';
			
			html += '<tr class="row-click ' + isLastRow + '" data-id="' + board.boardId + '">';
			html += '<td class="tNum">' + rowNum + '</td>';
			html += '<td class="tTitle">' + board.title + '</td>';
			html += '<td class="tWriter">' + board.writer + '</td>';
			html += '<td class="tDate">' + date + '</td>';
			html += '<td class="tRcm">' + board.recommendation + '</td>';
			html += '</tr>';
		});
	}
	
	$("#tList").html(html);
}

function renderPaging(totalPages, currentPage) {
	var html = "";
	
	if(totalPages === 0) {
		$("#pagingBox").html("");
		return;
	}
	
	var startPage = Math.floor((currentPage - 1) / 10) * 10 + 1;
	var endPage = Math.min(startPage + 9, totalPages);
	
	if(startPage > 1) {
		html += '<a class="page-navi prev" href="javascript:void(0)" onclick="searchBoard(' + (startPage - 1) +')"> 이전 </a>';
	} else {
        html += '<span class="page-navi prev disabled">이전</span>';
    }
	
	html += '<div class="page-links">';
	for(var i = startPage; i <= endPage; i++) {
		if(i === currentPage) {
			html += '<a class="page-link active" href="javascript:void(0)"><span class="sr-only">현재페이지 </span>' + i + '</a>';
		} else {
			 html += '<a class="page-link" href="javascript:void(0)" onclick="searchBoard(' + i + ')">' + i + '</a>';
		}
	}
	
	if(endPage < totalPages) {
		html += '<span class="page-link link-dot"></span>';
        html += '<a class="page-link" href="javascript:void(0)" onclick="searchBoard(' + totalPages + ')">' + totalPages + '</a>';
	}
	html += '</div>';
	
	if(endPage < totalPages) {
        html += '<a class="page-navi next" href="javascript:void(0)" onclick="searchBoard(' + (endPage + 1) + ')">다음</a>';
    } else {
        html += '<span class="page-navi next disabled">다음</span>';
    }
	
	$("#pagingBox").html(html);
}