package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplyDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@Service
public class BoardService {

	// ===================================================
	// Autowired동작원리 원래는 아래처럼 생성자 주입을 많이 한다고 들었다. 이걸 간단하게 @Autowired로 한것이다.
	//@Service가 스프링 IoC컨테이너에 담길때 생성자로 인해 두개의 래파지토리까지 같이 담게된다.
	//그리고 이미 두개의 래파지토리는 IoC에 담겨있어서 스프링이 알수가있다.

//	private BoardRepository boardRepository;
//	private ReplyRepository replyRepository;
//	
//	public BoardService(BoardRepository boardRepository, ReplyRepository replyRepository) {
//		this.boardRepository = boardRepository;
//		this.replyRepository = replyRepository;
//	}
	
	//그리고 가장 간단한 방법은 @RequiredArgsConstructor 어노테이션을 붙이는거다.
	//이러면 해당 BoardService Ioc컨테이너에 담길때 @RequiredArgsConstructor가 두개의 래파지토리를 같이 주입시킨다.
//	@Service
//	@RequiredArgsConstructor
//	public class BoardService {
//	private final BoardRepository boardRepository;
//	private final ReplyRepository replyRepository;
//}

	// ===================================================
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	// 글 쓰기
	@Transactional
	public void save(Board board, User user) {
		board.setUser(user);
		boardRepository.save(board);
	}

	// 전체 조회
	@Transactional
	public Page<Board> sellectAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	// 아이디로 해당 게시물 조회
	@Transactional
	public Board findById(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을수 없습니다.");
		});
	}

	// 아이디로 해당 게시물 삭제
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}

	// 아이디로 글 수정하기
	@Transactional
	public void update(int id, Board board) {
		Board getBoard = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 수정하기 실패 : 아이디를 찾을수 없습니다.");
		});

		getBoard.setTitle(board.getTitle());
		getBoard.setContent(board.getContent());
//		boardRepository.save(getBoard); <= 해당 함수 종료시 트랜젝션이 종료, 이때 더티체킹 -> 자동 업데이트 flush
//		즉 영속성 컨테이너에 select으로 가져온게 1차 캐시에 담겨있고 함수가 끝나면 저절로 commit이 되므로 save안날려도 수정된다.
	}

//	@Transactional
//	public void writeReply(int boardId, Reply reply, User user) {
//		Board board = boardRepository.findById(boardId).get();
//		reply.setUser(user);
//		reply.setBoard(board);
//		replyRepository.save(reply);
//	}

	// 만들어놓은 QueryDsl로 reply쓰기
	@Transactional
	public void writeReply(ReplyDto replyDto) {
		int userId = replyDto.getUserId();
		int boardId = replyDto.getBoardId();
		String content = replyDto.getContent();
		replyRepository.saveReply(userId, boardId, content);
	}

}
