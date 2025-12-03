package com.kdn.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends BaseVO {
	
	private long userId;
	private String kdnNumber;
	private String password;
	private String name;
	private Long deptCode;
	private Long rankCode;
	private String phone;
	private String email;
	private Role role;
	
}
