package com.kdn.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private String kdnNumber;
	private String password;
	private String name;
	private String phone1;
	private String phone2;
	private String phone3;
	private String emailId;
	private String emailAdd;
	private String deptCode;
	private String rankCode;
	
}
