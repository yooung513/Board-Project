$(function() {
	$("#cancelBtn").click(function(){
		location.href = '/';
	})
	
	$("#insertBtn").click(function() {
		if(!$("#title").val()) {
			alert("제목을 입력해주세요.");
			$("#title").val("").focus();
			return;
		}
		
		if(!$("#content").val()) {
			alert("내용을 입력해주세요.");
			$("#content").val("").focus();
			return;
		}
		
		var formData = $("#boardFrm").serialize();
		$.ajax({
			url: "/insert",
			type: "POST",
			data: formData,
			dataType: "text",
			success: function(result) {
				
				if(result == "success") {
					alert("게시글 등록이 완료됐습니다.");
					// 본인 글로 이동
					location.href = "/";
				} 
				
				if(result == "fail") {
					alert("게시글 등록이 실패했습니다. \n다시 확인해주세요.");
					location.reload();
				}
			},
			error: function() {
				alert("서버 오류가 발생했습니다.");
			}
		})
	})
}) 