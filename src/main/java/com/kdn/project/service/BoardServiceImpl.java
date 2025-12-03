package com.kdn.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdn.project.domain.DepartmentCodeVO;
import com.kdn.project.domain.RankCodeVO;
import com.kdn.project.dto.BoardDTO;
import com.kdn.project.dto.BoardListDTO;
import com.kdn.project.dto.CommentDTO;
import com.kdn.project.dto.CommentListDTO;
import com.kdn.project.dto.SearchDTO;
import com.kdn.project.dto.SearchResponseDTO;
import com.kdn.project.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public DepartmentCodeVO selectByDeptCode(Long deptCode) {
		DepartmentCodeVO deptInfo = boardMapper.selectByDeptCode(deptCode);
		return deptInfo;
	}

	@Override
	public RankCodeVO selectByRankCode(Long rankCode) {
		RankCodeVO rankInfo = boardMapper.selectByRankCode(rankCode);
		return rankInfo;
	}

	@Override
	public String getWriter(String userName, Long deptCode, Long rankCode) {
		String deptName = deptCode != null ? selectByDeptCode(deptCode).getDeptName() : "";
		String rankName = rankCode != null ? selectByRankCode(rankCode).getRankName() : "";
		
		String info = (deptName + " " + rankName).trim();
		String writer = info.isBlank() ? userName : userName + " (" + info + ")"; 
				
		return writer;
	}

	@Override
	public int insertBoard(BoardDTO boardDTO) {
		int success = boardMapper.insertBoard(boardDTO);
		return success;
	}

	@Override
	public SearchResponseDTO selectBoardList(SearchDTO searchDTO) {
		int totalCount = boardMapper.selectBoardCount(searchDTO);
		
		searchDTO.calculateOffset();
		searchDTO.setTotalCount(totalCount);
		searchDTO.calculateTotalPages();
		
		List<BoardListDTO> boardList = boardMapper.selectBoardList(searchDTO);
		
		SearchResponseDTO result = SearchResponseDTO.builder()
										.boardList(boardList)
										.totalCount(totalCount)
										.totalPages(searchDTO.getTotalPages())
										.currentPage(searchDTO.getPage())
										.build();
		
		return result;
	}

	@Override
	public BoardListDTO selectBoardById(long boardId) {
		BoardListDTO board = boardMapper.selectBoardById(boardId);
		return board;
	}

	@Override
	public int updateBoard(BoardDTO boardDTO) {
		int success = boardMapper.updateBoard(boardDTO);
		return success;
	}

	@Override
	public int deleteBoard(long boardId) {
		int success = boardMapper.deleteBoard(boardId);
		return success;
	}

	@Override
	public int insertComment(CommentDTO commentDTO) {
		int success = boardMapper.insertComment(commentDTO);
		return success;
	}

	@Override
	public List<CommentListDTO> selectComment(long boardId) {
		List<CommentListDTO> commentList = boardMapper.selectComment(boardId);
		return commentList;
	}

	@Override
	public int updateComment(CommentDTO commentDTO) {
		int success = boardMapper.updateComment(commentDTO);
		return success;
	}

	@Override
	public int deleteComment(long commentId) {
		int success = boardMapper.deleteComment(commentId);
		return success;
	}

	@Override
	public boolean isRecommended(long boardId, long userId) {
		int cntRecommend = boardMapper.isRecommeded(boardId, userId);
		return cntRecommend > 0 ? true : false;
	}

	@Override
	public int insertRecommend(long boardId, long userId) {
		int success = boardMapper.insertRecommend(boardId, userId);
		return success;
	}
	

}
