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

    private final BoardService boardService;

    @PostMapping("/api/auth/board")
    public Board createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(boardRequestDto);
    }


    @GetMapping("/api/board")
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();
    }

    @PutMapping("/api/auth/board/{boardId}")
    public Long update(@PathVariable Long boardId,@RequestBody BoardRequestDto boardRequestDto) {
        boardService.update(boardId, boardRequestDto);
        return boardId;
    }

    @DeleteMapping("/api/auth/board/{boardid}")
    public Long deleteBoard(@PathVariable Long boardId){
        boardService.deleteBoard(boardId);
        return boardId;
    }
}