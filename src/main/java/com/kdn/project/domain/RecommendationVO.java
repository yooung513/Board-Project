package com.kdn.project.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecommendationVO {
	
	private long boardId;
	private long userId;
	private LocalDateTime createdAt;
}
