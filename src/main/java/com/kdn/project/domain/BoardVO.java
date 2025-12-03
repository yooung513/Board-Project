package com.kdn.project.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardVO extends BaseVO {

	private long boardId;
	private String title;
	private String content;
	private long userId;
	private String useFlag;
	private Long deptCode;
	private Long rankCode;
	
}
