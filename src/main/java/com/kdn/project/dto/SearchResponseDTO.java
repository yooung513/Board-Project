package com.kdn.project.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchResponseDTO {
	private List<BoardListDTO> boardList;
	private int totalCount;
	private int totalPages;
	private int currentPage;
}