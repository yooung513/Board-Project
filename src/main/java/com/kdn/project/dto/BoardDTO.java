package com.kdn.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

	private String userId;
	private String title; 
	private String content;
	private String deptCode;
	private String rankCode;
	
	private Long boardId; 
	
	private String writer;
	private LocalDateTime date;
}
