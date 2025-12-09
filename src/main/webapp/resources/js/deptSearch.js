$(function() {
	var selectedDeptCode = "";
	var selectedDeptName = "";
	var isDragging = false;
	
	// 트리 생성 함수
	function buildTree(parentCode, $container) {
		const children = deptList.filter(d => d.parDeptCode == parentCode);
		if(!children.length) return;
		
		const ul = $('<ul class="nested"></ul>');
		
		children.forEach(dept => {
			const hasChildren = deptList.some(d => d.parDeptCode === dept.deptCode);
			const li = $("<li>")
				.attr("data-code", dept.deptCode)
				.attr("data-name", dept.deptName)
				.text(dept.deptName)
				.addClass(hasChildren ? "caret" : "leaf");

			buildTree(dept.deptCode, li);
			ul.append(li);
		});
		
		$container.append(ul);
	}
	
	
	// 최상위 부서코드 로드
	buildTree(null, $("#treeContainer"));
	$("#treeContainer > ul").addClass("active");
	
	
	// 하위 부서 펼치기
	$(document).on("click", ".caret", function(e) {
		if(isDragging) return;
		
		e.stopPropagation();
		const $this = $(this);
		const code = $this.data("code");
		
		
		// 이미 열려있으면 토글만
		if($this.children("ul").length) {
			$this.children("ul").toggleClass("active");
			$this.toggleClass("caret-down");
			return;
		}
		
		// 새로운 하위 부서 추가
		const nested = $('<ul class="nested"></ul>');
		$this.append(nested);
		buildTree(code, nested);
		
		// 트리 표시 전환
		$this.toggleClass("caret-down");
		nested.addClass("active");
	});
	
	
	// 부서 리스트 선택
	$(document).on("click", ".leaf", function(e) {
		e.stopPropagation();
		$("#treeContainer li").removeClass("highlight");
		
		selectedDeptCode = $(this).data("code");
		selectedDeptName = $(this).data("name");
		
		$(this).addClass("highlight");
	});
	
	
	// 검색
	$("#searchBtn").on("click", function() {
		const keyword = $("#searchIpt").val().toLowerCase();
		resetTree();
		
		if(!keyword) {
			return;
		}
		
		$("#treeContainer li").hide();
		
		const matched = deptList.filter(d => 
			d.deptName.toLowerCase().includes(keyword)
		);
		
		if(!matched.length) {
			$("#noResult").show();
			return;
		}
		
		matched.forEach(d => {
			const $target = $("#treeContainer li[data-code='"+d.deptCode+"']");

			$target.show();
			
			$target.parents("li").show();
			$target.parents("ul").show();
			
			$target.parents("li.caret").addClass("caret-down");
			$target.parents("ul.nested").addClass("active");
			
		});
		
	});
	
	$("#searchIpt").keyup(function(e) {
		if(e.which === 13) {
			$("#searchBtn").trigger("click");
			return;
		} 
	});
	
	// 트리 초기화
	function resetTree() {
		$("#treeContainer").empty();
	    buildTree(null, $("#treeContainer"));
	    $("#treeContainer > ul").addClass("active");
	    $("#noResult").hide();
	}
	
	// 데이터 전달
	$("#inputBtn").click(function() {
		if(window.opener && !window.opener.closed) {
			window.opener.document.getElementById("dept").value = selectedDeptName;
			window.opener.document.getElementById("deptCode").value = selectedDeptCode;
		}
		window.close();
	})
	
	// 마우스 드래그 방지
	$(document).on("mousedown", "li", function() {
		isDragging = false;
	});
	
	$(document).on("mousemove", "li", function(){
		isDragging = true;
	});
	
	$("#cancelBtn").click(function() {
		window.close();
	});
	
	$("#titleP").click(function() {
		location.reload();
	});
})