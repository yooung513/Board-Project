package com.kdn.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankCodeVO extends BaseVO {

	private long rankCode;
	private int rankSeq;
	private String rankName;
	private String useFlag;
	private long createdId;
	private long updatedId;
	
}
