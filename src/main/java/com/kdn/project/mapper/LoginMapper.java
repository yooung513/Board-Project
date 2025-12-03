package com.kdn.project.mapper;

import java.util.List;

import com.kdn.project.domain.DepartmentCodeVO;
import com.kdn.project.domain.RankCodeVO;
import com.kdn.project.domain.UserVO;

public interface LoginMapper {

	int selectCntKdnNum(String kdnNumber);
	
	UserVO selectUserInfo(String kdnNumber);

	List<RankCodeVO> selectRank();
	
	List<DepartmentCodeVO> selectDept();
	
	void insertUser(UserVO user);
}
