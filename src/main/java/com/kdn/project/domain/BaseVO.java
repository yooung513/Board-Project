package com.kdn.project.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public abstract class BaseVO {
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
