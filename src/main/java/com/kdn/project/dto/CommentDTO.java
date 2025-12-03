package com.kdn.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	
	private String commentId;
	private String boardId;
	private String userId;
	private String comment;
	private String deptCode;
	private String rankCode;
	
}
