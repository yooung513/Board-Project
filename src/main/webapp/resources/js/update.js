$(function() {
	var user = "${sessionScope.user.name}";
	var boardId = "${board.boardId }";
	
	$("#cancelBtn").click(function(){
		location.href = '/board/' + boardId;
	})
	
	$("#updateBtn").click(function() {
		if(!$("#title").val()) {
			alert("제목을 입력해주세요.");
			return;
		}
		
		if(!$("#content").val()) {
			alert("내용을 입력해주세요.");
			return;
		}
		
		var formData = $("#boardFrm").serialize();
		$.ajax({
			url: "/update",
			type: "POST",
			data: formData,
			dataType: "text",
			success: function(result) {
				
				if(result == "success") {
					alert("게시글 수정이 완료됐습니다.");
					location.href = '/board/' + boardId;
				} 
				
				if(result == "fail") {
					alert("게시글 수정이 실패했습니다. \n다시 확인해주세요.");
					location.reload();
				}
			},
			error: function() {
				alert("서버 오류가 발생했습니다.");
			}
		})
	})
}) 