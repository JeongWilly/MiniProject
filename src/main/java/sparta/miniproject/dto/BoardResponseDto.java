package sparta.miniproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import sparta.miniproject.model.Board;
import sparta.miniproject.model.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponseDto {

    private Long boardId;

    private String nickname;

    private String title;

    private String content;
    private List<Comment> commentList;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createTime;

    public BoardResponseDto(Board board){
        this.nickname=board.getNickname();
        this.title=board.getTitle();
        this.createTime=board.getCreatedAt();
    }

    @Builder

    public BoardResponseDto(Long boardId, String nickname, String title, String content, List<Comment> commentList, LocalDateTime createTime) {
        this.boardId = boardId;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.commentList = commentList;
        this.createTime = createTime;
    }
}
