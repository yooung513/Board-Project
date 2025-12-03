package com.kdn.project.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentVO extends BaseVO {

	private long commentId;
	private long boardId;
	private long userId;
	private String comment;
	private Long deptCode;
	private Long rankCode;
	private String useFlag;
	
}
