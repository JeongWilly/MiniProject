package sparta.miniproject.dto;

import lombok.Getter;
import sparta.miniproject.model.Board;

@Getter
public class BoardResponseDto {

    private Long boardId;

    private String nickname;

    private String title;

    private String content;

    public BoardResponseDto(Board board){
        this.boardId=board.getBoardId();
        this.nickname=board.getNickname();
        this.title=board.getTitle();
        this.content=board.getContent();

    }
}
