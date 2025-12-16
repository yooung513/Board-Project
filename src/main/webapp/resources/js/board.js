$(function() {

	var isEditingComment = false;

	$("#comment").on("input", function() {
		var currentLength = $(this).val().length;
		$("#currentCount").text(currentLength);
		
		if(currentLength >= 180) {
			$("#currentCount").css("color", "red");
		} else {
			$("#currentCount").css("color", "#333");
		}
	});

	$("#listBtn").click(function() {
		var searchParams = sessionStorage.getItem('searchParams');
		
		// URL에 검색 조건이 있으면 그대로 전달
		if(searchParams) {
			location.href = '/?' + searchParams;
		} else {
			location.href = '/';
		}
	});

	$("#updateBtn").click(function() {
		location.href = '/update/' + boardId;
	})
	
	
	$("#deleteBtn").click(function() {
		var deleteConfirm = confirm("정말로 삭제하시겠습니까?");
		
		if(!deleteConfirm) {
			return;
		}
		
		$.ajax({
			url: "/delete/" + boardId,
			type: "POST",
			dataType: "text",
			success: function(result) {
				
				if(result == "success") {
					alert("게시글 삭제가 완료됐습니다.");
					location.href = '/';
				} 
				
				if(result == "fail") {
					alert("게시글 삭제가 실패했습니다. \n다시 확인해주세요.");
					location.reload();
				}
			},
			error: function() {
				alert("서버 오류가 발생했습니다.");
			}
		})
	})
	
	
	$("#cmtBtn").click(function(){
		if(!$("#comment").val()) {
			alert("댓글 내용을 등록해주세요.");
			$("#comment").val("").focus();
			return;
		}
		
		if($("#comment").val().length > 200) {
			alert("댓글은 200자까지만 입력 가능합니다.");
			return;
		}
		
		var formData = $("#commentFrm").serialize();
		$.ajax({
			url: "/insert/comment",
			type: "POST",
			data: formData,
			dataType: "text",
			success: function(result) {
				
				if(result == "success") {
					alert("댓글 등록이 완료됐습니다.");
				} 
				
				if(result == "fail") {
					alert("댓글 등록이 실패했습니다. \n다시 확인해주세요.");
				}
				
				location.reload();
			},
			error: function() {
				alert("서버 오류가 발생했습니다.");
			}
		})
	})
	
	
	$(document).on("click", ".updateCmtBtn", function() {
		if (isEditingComment) {
	        alert("이미 수정 중인 댓글이 있습니다.\n완료 또는 취소 후 다시 시도해주세요.");
	        return;
	    }
	
		isEditingComment = true;
	
		var cmtItem = $(this).closest(".cmtItem");
		var cmtTextSpan = cmtItem.find(".readCmtText");
		var originalText = cmtTextSpan.text().trim();
		var commentId = cmtItem.data("comment-id");
		
		$(".cmtItem").not(cmtItem).find(".updateCmtBtn, .deleteCmtBtn").hide();
		
		if (cmtItem.hasClass("editing")) return;
		cmtItem.addClass("editing");
		
		cmtTextSpan.replaceWith(
			'<div>' +
			'<textarea id="editCmtText" class="editCmtText" maxlength="200">' + originalText + '</textarea>' +
			'<div class="cmtCounter"><span class="editCount">' + originalText.length + '</span> / 200자</div>' +
			'</div>'
		);
		
		cmtItem.find(".updateCmtBtn").val("완 료").removeClass("updateCmtBtn").addClass("saveCmtBtn");
		cmtItem.find(".deleteCmtBtn").val("취 소").removeClass("deleteCmtBtn").addClass("cancelCmtBtn");
		
		var textarea = cmtItem.find(".editCmtText");
		textarea.focus();
		textarea[0].setSelectionRange(originalText.length, originalText.length);
		
		textarea.on("input", function() {
			var currentLength = $(this).val().length;
			$(this).siblings(".cmtCounter").find(".editCount").text(currentLength);
			
			if(currentLength >= 180) {
				$(this).siblings(".cmtCounter").find(".editCount").css("color", "red");
			} else {
				$(this).siblings(".cmtCounter").find(".editCount").css("color", "#333");
			}
		});
	});
	
	
	$(document).on("click", ".saveCmtBtn", function() {
		var cmtItem = $(this).closest(".cmtItem");
		var commentId = cmtItem.data("comment-id");
		var newComment = cmtItem.find(".editCmtText").val();
		
		if (!newComment.trim()) {
			alert("댓글 내용을 입력해주세요.");
			return;
		}
		

		$.ajax({
			url: "/update/comment",
			type: "POST",
			data: { 
					commentId: commentId,
					comment: newComment,
					deptCode: deptCode !== '' ? deptCode : null,
					rankCode: rankCode !== '' ? rankCode : null
				  },
			dataType: "text",
			success: function(result) {
				if (result == "success") {
					isEditingComment = false;
					alert("댓글이 수정되었습니다.");
					location.reload();
				} else {
					alert("댓글 수정에 실패했습니다.");
				}
			},
			error: function() {
				alert("서버 오류가 발생했습니다.");
			}
		});
	});

	$(document).on("click", ".cancelCmtBtn", function() {
		isEditingComment = false;
		location.reload();
	});
	
	$(document).on("click", ".deleteCmtBtn", function() {
		var cmtItem = $(this).closest(".cmtItem");
		var commentId = cmtItem.data("comment-id");
		var deleteConfirm = confirm("정말로 삭제하시겠습니까?");
		
		if(!deleteConfirm) {
			return;
		}
		
		$.ajax({
			url: "/delete/comment/" + commentId,
			type: "POST",
			dataType: "text",
			success: function(result) {
				
				if(result == "success") {
					alert("댓글 삭제가 완료됐습니다.");
				} 
				
				if(result == "fail") {
					alert("댓글 삭제가 실패했습니다. \n다시 확인해주세요.");
				}
				
				location.reload();
			},
			error: function() {
				alert("서버 오류가 발생했습니다.");
			}
		})
	});
	
	$("#rcmBtn").click(function() {
	    $.ajax({
	        url: "/recommend/" + boardId,
	        type: "POST",
	        dataType: "text",
	        success: function(result) {
	            if (result == "success") {
	                alert("추천되었습니다.");
	                location.reload();
	                
	            } else if (result == "already") {
	                alert("이미 추천한 게시글입니다.");
	                
	            } else {
	                alert("추천에 실패했습니다.");
	            }
	        },
	        error: function() {
	            alert("서버 오류가 발생했습니다.");
	        }
	    });
	});
})