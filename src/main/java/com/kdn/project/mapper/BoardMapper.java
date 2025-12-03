package com.kdn.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kdn.project.domain.DepartmentCodeVO;
import com.kdn.project.domain.RankCodeVO;
import com.kdn.project.dto.BoardDTO;
import com.kdn.project.dto.BoardListDTO;
import com.kdn.project.dto.CommentDTO;
import com.kdn.project.dto.CommentListDTO;
import com.kdn.project.dto.SearchDTO;

public interface BoardMapper {

	DepartmentCodeVO selectByDeptCode(Long deptCode);
	
	RankCodeVO selectByRankCode(Long rankCode);

	int insertBoard(BoardDTO boardDTO);
	
	int selectBoardCount(SearchDTO searchDTO);
	
	List<BoardListDTO> selectBoardList(SearchDTO searchDTO);

	BoardListDTO selectBoardById(long boardId);

	int updateBoard(BoardDTO boardDTO);

	int deleteBoard(long boardId);

	int insertComment(CommentDTO commentDTO);

	List<CommentListDTO> selectComment(long boardId);

	int updateComment(CommentDTO commentDTO);

	int deleteComment(long commentId);

	int isRecommeded(@Param("boardId") long boardId, @Param("userId") long userId);

	int insertRecommend(@Param("boardId") long boardId, @Param("userId") long userId);
	
}
