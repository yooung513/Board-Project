package com.kdn.project.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {

	private String dateRange;
	private String startDate;
	private String endDate; 
	
	private String keywordType;
	private String keyword;
	private String sort = "latest";
	
	private int page = 1; 
	private int pageSize = 10;
	private int totalCount; 
	
	private int offset;
	private int totalPages;
	
	
	public SearchDTO() {
		LocalDate today = LocalDate.now();
		LocalDate oneYearAgo = today.minusYears(1);
		
		this.startDate = oneYearAgo.toString();
		this.endDate = today.toString();
	}
	
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
		
		if(dateRange != null && dateRange.contains("~")) {
			String[] dates = dateRange.split("~");
			
			this.startDate = dates[0].trim();
			this.endDate = dates[1].trim();
		}
	}
	
	public void calculateOffset() {
		this.offset = (this.page - 1) * this.pageSize;
	}
	
	public void calculateTotalPages() {
		this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
	}
	
}
