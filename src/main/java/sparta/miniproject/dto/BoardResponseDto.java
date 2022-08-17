package sparta.miniproject.dto;

import lombok.Getter;
import sparta.miniproject.model.Board;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long boardId;

    private String nickname;

    private String title;

    private String content;

    private LocalDateTime createTime;

    public BoardResponseDto(Board board){
        this.boardId=getBoardId();
        this.nickname=board.getNickname();
        this.title=board.getTitle();
        this.content=board.getContent();
        this.createTime=board.getCreatedAt();
    }
}
