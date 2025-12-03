package com.kdn.project.service;

import java.util.List;

import com.kdn.project.domain.DepartmentCodeVO;
import com.kdn.project.domain.RankCodeVO;
import com.kdn.project.domain.UserVO;
import com.kdn.project.dto.UserDTO;

public interface LoginService {

	// 로그인
	UserVO selectUserInfo(String kdnNumber, String password);
	
	// 사번 중복검색
	boolean isExistKdnNum(String kdnNumber);
	
	// 직위 데이터
	List<RankCodeVO> selectRank();
	
	// 부서 데이터
	List<DepartmentCodeVO> selectDept();
	
	// 회원가입
	void insertUser(UserDTO userDTO);
}
