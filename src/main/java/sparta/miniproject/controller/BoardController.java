package sparta.miniproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.miniproject.dto.BoardRequestDto;
import sparta.miniproject.dto.BoardResponseDto;
import sparta.miniproject.model.Board;
import sparta.miniproject.repository.MemberRepository;
import sparta.miniproject.service.BoardService;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final MemberRepository memberRepository;
    private final BoardService boardService;


    //게시물작성
    @PostMapping("/api/auth/board")
    public Board createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(boardRequestDto);
    }


    //게시물조회
    @GetMapping("/api/board")
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();
    }



    //게시물 상세조회
    @GetMapping("/api/board/{boardId}")
    public Board getBoard(@PathVariable Long boardId){return boardService.getEachBoard(boardId);}

    //게시물수정하기
    @PutMapping("/api/auth/board/{boardId}")
    public Long update(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto) {
        boardService.update(boardId, boardRequestDto);
        return boardId;
    }


    //게시물내에서 삭제하기
    @DeleteMapping("/api/auth/board/{boardId}")
    public Long deleteBoard(@PathVariable Long boardId){
        return boardService.deleteBoard(boardId);
    }
}