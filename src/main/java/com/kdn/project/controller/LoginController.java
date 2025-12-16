package com.kdn.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdn.project.domain.DepartmentCodeVO;
import com.kdn.project.domain.RankCodeVO;
import com.kdn.project.domain.UserVO;
import com.kdn.project.dto.UserDTO;
import com.kdn.project.service.LoginService;


@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	// 1-1. 로그인 화면 이동
	@GetMapping("/login")
	public String loginPage(HttpServletRequest request, Model model) {
		model.addAttribute("result", "login");
		return "login";
	}
	
	// 1. 유저 로그인
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestParam String kdnNumber, @RequestParam String password, HttpSession session) throws Exception {
		UserVO userVO = new UserVO();
		
		try {
			
			if("".equals(kdnNumber) || kdnNumber == null || "".equals(password) || password == null) {
				// 유효성검사 (정규식)
				//id.chars().allMatch(Character::isDigit)
				return "fail";
			}
			
			userVO = loginService.selectUserInfo(kdnNumber, password);
			if(userVO == null) {
				return "fail";
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return "fail";
		}
		
		session.setAttribute("user", userVO);
		
		return "success";
	}

	
	// 2. 회원가입 페이지 
	@GetMapping("/signup")
	public String pageSignUp(HttpServletRequest request, Model model) {
		
		List<RankCodeVO> rankList = loginService.selectRank();
		model.addAttribute("rankList", rankList);
		
		return "signup";
	}
	
	// 2-1. 사번 중복확인
	@GetMapping("/checkNum")
	@ResponseBody
	public boolean checkNum(@RequestParam String kdnNumber) {
		// 유효성검사 (정규식)
		if("".equals(kdnNumber) || kdnNumber == null) return false;
		
		boolean isExist = loginService.isExistKdnNum(kdnNumber);
		
		return !isExist;
	}
	
	// 2-2. 부서 검색
	@GetMapping("/deptSearch")
	public String deptSearch(Model model) {
		List<DepartmentCodeVO> deptList = loginService.selectDept();
		model.addAttribute("deptList", deptList);
		return "/popup/deptSearch";
	}
	
	
	// 3. 회원가입
	@PostMapping("/signup")
	@ResponseBody
	public String signUp(@ModelAttribute UserDTO userDTO) {
		// 유효성검사
		loginService.insertUser(userDTO);
		boolean isExist = loginService.isExistKdnNum(userDTO.getKdnNumber());
		
		return isExist ? "success" : "fail";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}
}
