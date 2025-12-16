$(function(){
	
	if(result == "false") {
		alert("로그인 정보를 확인해주세요.");
		$("#kdnNumber").val("");
		$("#password").val("");
		
		setTimeout(function() {
	    	$("#kdnNumber").focus();
	    }, 100);
		return;
	}
	
	// 로그인
	$("#loginBtn").click(function(){
		var id = $("#kdnNumber").val();
		var pw = $("#password").val();
		
		if(id == "" ) {
			alert("사번을 입력하세요.");
			$("#kdnNumber").focus();
			return;
		}
		
		if(pw == "") {
			alert("비밀번호를 입력하세요.");
			$("#password").focus();
			return;
		}
		
		var idRegex = /^\d{6}$/;
		if(!idRegex.test(id)) {
			alert("사번은 숫자 6자리 입니다.");
			$("#kdnNumber").focus();
			return;
		}
		
		var pwRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{9,15}$/;
		if(!pwRegex.test(pw)) {
			alert("비밀번호가 다릅니다.");
			$("#password").focus();
			return;
		}
		
		$.ajax({
			url: "/login",
			type: "POST",
			data: {
				kdnNumber: id,
				password: pw
			},
			dataType: "text",
			success: function (result) {
				if (result === "success") {
					location.href = "/";
				} else {
					alert("사번 또는 비밀번호가 올바르지 않습니다.");
					$("#password").val("").focus();
				}
			},
			error: function () {
				alert("서버 오류가 발생했습니다.");
			}
		});
	})
	
	
	$("#password").keyup(function(e){
		if(e.which === 13) {
			$("#loginBtn").click();
		}
	})
	
});