package com.kdn.project.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserHistoryVO {
	
	private long idx;
	private long userId;
	private String ip;
	private LocalDateTime createdAt;
	
}
