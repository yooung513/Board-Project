package com.kdn.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCodeVO extends BaseVO {

	private Long deptCode;
	private Long parDeptCode;
	private String deptName;
	private String useFlag;
	private long createdId;
	private long updatedId;
}
