package com.kdn.project.service;

import java.util.List;

import com.kdn.project.domain.DepartmentCodeVO;
import com.kdn.project.domain.RankCodeVO;
import com.kdn.project.dto.BoardDTO;
import com.kdn.project.dto.BoardListDTO;
import com.kdn.project.dto.CommentDTO;
import com.kdn.project.dto.CommentListDTO;
import com.kdn.project.dto.SearchDTO;
import com.kdn.project.dto.SearchResponseDTO;

public interface BoardService {
	
	DepartmentCodeVO selectByDeptCode(Long deptCode);
	
	RankCodeVO selectByRankCode(Long rankCode);
	
	String getWriter(String userName, Long deptCode, Long rankCode);

	int insertBoard(BoardDTO boardDTO);
	
	SearchResponseDTO selectBoardList(SearchDTO searchDTO);

	BoardListDTO selectBoardById(long boardId);

	int updateBoard(BoardDTO boardDTO);

	int deleteBoard(long boardId);

	int insertComment(CommentDTO commentDTO);

	List<CommentListDTO> selectComment(long boardId);

	int updateComment(CommentDTO commentDTO);

	int deleteComment(long commentId);

	boolean isRecommended(long boardId, long userId);

	int insertRecommend(long boardId, long userId);
}
