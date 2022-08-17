package sparta.miniproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import sparta.miniproject.model.Board;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long boardId;

    private String nickname;

    private String title;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createTime;

    public BoardResponseDto(Board board){
        this.nickname=board.getNickname();
        this.title=board.getTitle();
        this.createTime=board.getCreatedAt();
    }

    @Builder
    public BoardResponseDto(String nickname, String title, String content, LocalDateTime createTime) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }
}
