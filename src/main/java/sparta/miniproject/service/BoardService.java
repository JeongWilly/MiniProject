package sparta.miniproject.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sparta.miniproject.dto.BoardRequestDto;
import sparta.miniproject.dto.BoardResponseDto;
import sparta.miniproject.model.Board;
import sparta.miniproject.model.Member;
import sparta.miniproject.repository.BoardRepository;
import sparta.miniproject.repository.MemberRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Getter
public class BoardService {

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    public String getNickname() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName(); //로그인해서 뽑은 아이디르 넣음
        Optional<Member> member = memberRepository.findById(Long.valueOf(userId));
        return member.get().getNickname();
    }

    public Member getMember(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findById(Long.valueOf(userId))
                .orElseThrow(()-> new RuntimeException("유저를 찾지 못했습니다."));
        return member;
    }


    //게시물작성
    public Board createBoard(BoardRequestDto boardRequestDto){ // ? IOException?
       Member member=getMember();
       Board board = new Board(boardRequestDto,member);
       member.getBoardList().add(board);
       return board;
    }
//
    //게시물조회
    public List<BoardResponseDto> getBoard(){
    List<BoardResponseDto>boardResponseDtoList = new ArrayList<>();
    List<Board>board = boardRepository.findAll();
    for(Board brd1 : board){
        boardResponseDtoList.add(new BoardResponseDto(brd1));

    }return boardResponseDtoList;


    }


    //게시물 수정
    @Transactional
    public void update(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("아이디가 없습니다"));
        board.update(boardRequestDto);
    }


    //게시물 삭제
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다"));

        boardRepository.delete(board);
    }


}
