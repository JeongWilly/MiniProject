package sparta.miniproject.dto;

import lombok.Getter;
import sparta.miniproject.model.Board;

@Getter
public class BoardResponseDto {

    private Long board_id;

    private String nickname;

    private String title;

    private String content;

    public BoardResponseDto(Board board){
        this.board_id=board.getBoard_id();
        this.nickname=board.getNickname();
        this.title=board.getTitle();
        this.content=board.getContent();

    }
}
