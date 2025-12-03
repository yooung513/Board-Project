package com.kdn.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdn.project.domain.UserVO;
import com.kdn.project.dto.BoardDTO;
import com.kdn.project.dto.BoardListDTO;
import com.kdn.project.dto.CommentDTO;
import com.kdn.project.dto.CommentListDTO;
import com.kdn.project.dto.SearchDTO;
import com.kdn.project.dto.SearchResponseDTO;
import com.kdn.project.service.BoardService;


@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public String main(@ModelAttribute SearchDTO searchDTO,
						HttpSession session, Model model) { 
		
		UserVO user = (UserVO) session.getAttribute("user");
		if(user == null) return "redirect:/login";
		
		SearchResponseDTO result = boardService.selectBoardList(searchDTO);
		
		model.addAttribute("boardList", result.getBoardList());
		model.addAttribute("pagination", result);
		
		return "main";
	}
	
	@GetMapping("/search")
	@ResponseBody
	public SearchResponseDTO searchBoard(@ModelAttribute SearchDTO searchDTO) {
		SearchResponseDTO result = boardService.selectBoardList(searchDTO);
		return result;
	}
	
	// 게시글 등록
	@GetMapping("/insert")
	public String pageInsertBoard(HttpSession session, Model model) {
		UserVO user = (UserVO) session.getAttribute("user");
		if(user == null) return "redirect:/login";
		
		String userName = user.getName();
		Long deptCode = user.getDeptCode();
		Long rankCode = user.getRankCode();
		
		model.addAttribute("writer", boardService.getWriter(userName, deptCode, rankCode));
		return "insert";
	}
	
	@PostMapping("/insert")
	@ResponseBody
	public String insertBoard(@ModelAttribute BoardDTO boardDTO, HttpSession session, Model model) {
		// 유효성검사
		int success = 0; 
		
		try {
			success = boardService.insertBoard(boardDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return success == 1 ? "success" : "fail";
	}
	
	// 게시글 상세 조회
	@GetMapping("/board/{boardId}")
	public String selectBoard(@PathVariable long boardId, Model model) {
		BoardListDTO board = boardService.selectBoardById(boardId);
		List<CommentListDTO> commentList = boardService.selectComment(boardId);
		
		model.addAttribute("board", board);
		model.addAttribute("commentList", commentList);
		return "board";
	}
	
	// 게시글 수정
	@GetMapping("/update/{boardId}")
	public String pageUpdateBoard(@PathVariable long boardId, Model model) {
		BoardListDTO board = boardService.selectBoardById(boardId);
		
		model.addAttribute("board", board);
		return "update";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public String updateBoard(@ModelAttribute BoardDTO boardDTO) {
		// 유효성 검사 (본인 글이 맞는지)
		int success = 0;
		
		try {
			success = boardService.updateBoard(boardDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return success == 1 ? "success" : "fail";
	}
	
	// 게시글 삭제
	@PostMapping("/delete/{boardId}")
	@ResponseBody
	public String deleteBoard(@PathVariable long boardId) {
		// 유효성 검사 (본인 글이 맞는지)
		int success = 0;
		
		try {
			success = boardService.deleteBoard(boardId);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return success == 1 ? "success" : "fail";
	}
	
	// 댓글
	@PostMapping("/insert/comment")
	@ResponseBody
	public String insertComment(@ModelAttribute CommentDTO commentDTO) {
		int success = 0;
		try {
			success = boardService.insertComment(commentDTO);
			
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return success == 1 ? "success" : "fail";
	}
	
	@PostMapping("/update/comment")
	@ResponseBody
	public String updateComment(@ModelAttribute CommentDTO commentDTO) {
		int success = 0;
		
		try {
			success = boardService.updateComment(commentDTO);
			
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return success == 1 ? "success" : "fail";
	}
	
	@PostMapping("/delete/comment/{commentId}")
	@ResponseBody
	public String deleteComment(@PathVariable long commentId) {
		int success = 0;
		
		try {
			success = boardService.deleteComment(commentId);
			
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return success == 1 ? "success" : "fail";
	}
	
	@PostMapping("/recommend/{boardId}")
	@ResponseBody
	public String recommendBoard(@PathVariable long boardId, HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("user");
		
		boolean isRecommended = boardService.isRecommended(boardId, user.getUserId());
	    if (isRecommended) {
	        return "already";
	    }
	    
	    int success = boardService.insertRecommend(boardId, user.getUserId());
		return success == 1 ? "success" : "fail";
	}
}
