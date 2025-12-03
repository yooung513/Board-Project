package com.kdn.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdn.project.domain.DepartmentCodeVO;
import com.kdn.project.domain.RankCodeVO;
import com.kdn.project.domain.Role;
import com.kdn.project.domain.UserVO;
import com.kdn.project.dto.UserDTO;
import com.kdn.project.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	// 사번 중복검색
	@Override
	public boolean isExistKdnNum(String kdnNumber) {
		int cnt = loginMapper.selectCntKdnNum(kdnNumber);
		
		return cnt > 0 ? true : false;
	}

	// 로그인
	@Override
	public UserVO selectUserInfo(String kdnNumber, String password) {
		// 비밀번호 암호화
		
		UserVO userInfo = loginMapper.selectUserInfo(kdnNumber);
		if(!password.equals(userInfo.getPassword())) {
			return null;
		}
		
		return userInfo;
	}

	// 직위 검색
	@Override
	public List<RankCodeVO> selectRank() {
		List<RankCodeVO> rankList = loginMapper.selectRank();
		return rankList;
	}

	// 부서 검색
	@Override
	public List<DepartmentCodeVO> selectDept() {
		List<DepartmentCodeVO> deptList = loginMapper.selectDept();
		return deptList;
	}

	// 회원가입
	@Override
	public void insertUser(UserDTO userDTO) {
		StringBuilder sb = new StringBuilder();
		String phone = sb.append(userDTO.getPhone1()).append("-")
						 .append(userDTO.getPhone2()).append("-")
						 .append(userDTO.getPhone3())
						 .toString();
		
		sb = new StringBuilder();
		String email = sb.append(userDTO.getEmailId()).append("@")
						.append(userDTO.getEmailAdd())
						.toString();
		
		Long deptCode = "".equals(userDTO.getDeptCode()) ? null : Long.valueOf(userDTO.getDeptCode());
		Long rankCode = "".equals(userDTO.getRankCode()) ? null : Long.valueOf(userDTO.getRankCode());
		
		UserVO user = UserVO.builder()
				.kdnNumber(userDTO.getKdnNumber())
				.password(userDTO.getPassword())		// 암호화 적용 필요
				.name(userDTO.getName())
				.phone(phone)
				.email(email)
				.deptCode(deptCode)
				.rankCode(rankCode)
				.role(Role.USER)
				.build();
		
		loginMapper.insertUser(user);
	}
	

}
