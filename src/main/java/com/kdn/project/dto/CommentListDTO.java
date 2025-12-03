package com.kdn.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentListDTO {

	private long commentId;
	private long boardId;
	private String comment;
	
	private long userId;
	private String userName;
	private String deptName;
	private String rankName;
	
	private String displayDate;
	private boolean isUpdated;
	
	public String getWriter() {
		this.deptName = (deptName == null) ? "" : deptName;
		this.rankName = (rankName == null) ? "" : rankName;
		
		String info = (deptName + " " + rankName).trim();
		String writer = info.isBlank() ? userName : userName + " (" + info + ")"; 
		return writer;
	}
}
