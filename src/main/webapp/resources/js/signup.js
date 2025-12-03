$(function(){
	var isChecked = false;
	var kdnNum = "";
		
	// 사번 중복확인
	$("#chkNumBtn").click(function(){
		kdnNum = $("#kdnNumber").val();
 
		if(!kdnNum) {
			alert("사번을 입력해주세요.");
			return; 
		}
			
		var idRegex = /^\d{6}$/;
		if(!idRegex.test(kdnNum)) {
			alert("사번은 숫자 6자리 입니다.");
			$("#kdnNumber").val("").focus();
			return;
		}
			
		$.ajax({
			url: "checkNum?kdnNumber=" + kdnNum,
			type: "get",
			success: function(result) {
				if(result == true) {
					alert("사용 가능한 사번입니다.");
					$("#password").focus();
					isChecked = true;
				}
					
				if(result == false) {
					alert("이미 등록된 사번입니다.");
					$("#kdnNumber").val("").focus();
					isChecked = false;
				}
			}
			
		})
	})
		
	$("#kdnNumber").keyup(function(e){
		if(kdnNum != $("#kdnNumber").val()) {
			kdnNum = $("#kdnNumber").val();
			isChecked = false;
		}
	})
		
	// 부서 검색
	$("#dept").click(function(){
		$("#deptBtn").click();
	})
		
	$("#deptBtn").click(function(){
		var popup = window.open("/deptSearch", "부서 검색", "width= 1000px, height= 700px");
	})
	
	$("#cancelBtn").click(function(){
		location.href = 'login';
	})
		
	// 회원가입
	$("#inputBtn").click(function(){
		if(!$("#kdnNumber").val()) { 
			alert("사번을 입력해주세요."); $("#kdnNumber").focus(); return;
		}
			
		if(!isChecked) {
			alert("사번 중복확인을 해주세요");
			return;
		}
			
		var idRegex = /^\d{6}$/;
		if(!idRegex.test(kdnNum)) {
			alert("사번은 숫자 6자리 입니다."); $("#kdn_number").focus();	return;
		}
			
		if(!$("#password").val()) {
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();	
				return;
		}
			
		var pwRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{9,15}$/;
		var pw = $("#password").val();
		if(!pwRegex.test(pw)) {
			alert("비밀번호 형식이 다릅니다.");
			$("#password").focus();
			return;
		}
			
		if(!$("#name").val()) {
			alert("이름을 입력해주세요."); $("#name").focus(); return;
		}
		
		if(!$("#phone2").val()) {
			alert("전화번호를 입력해주세요.");	 $("#phone2").focus();	return;
		}
		
		if(!$("#phone3").val()) {
			alert("전화번호를 입력해주세요."); $("#phone3").focus(); return;
		}
		
		var phone2Regex = /^\d{3,4}$/;
		if( !phone2Regex.test($("#phone2").val()) ) {
			alert("전화번호 형식이 다릅니다.");
			$("#phone2").val("").focus();
			return;
		}
			
		var phone3Regex = /^\d{4}$/;
		if( !phone3Regex.test($("#phone3").val()) ) {
			alert("전화번호 형식이 다릅니다.");
			$("#phone3").val("").focus();
			return;
		}
			
		if(!$("#emailId").val()) {
			alert("이메일을 입력해주세요."); $("#emailId").focus(); return;
		}
		
		if(!$("#emailAdd").val()) {
			alert("이메일을 입력해주세요.");	$("#emailAdd").focus(); return;
		}
		
		var formData = $("#signupFrm").serialize();
		
		$.ajax({
			url: "/signup",
			type: "POST",
			data: formData,
			dataType: "text",
			success: function(result) {
				
				if(result == "success") {
					alert("회원가입이 완료됐습니다.");
					location.href = "/login";
				} 
				
				if(result == "fail") {
					alert("회원가입에 실패했습니다. \n정보를 다시 확인해주세요.");
					location.reload();
				}
			},
			error: function() {
				alert("서버 오류가 발생했습니다.");
			}
		})
	})
});