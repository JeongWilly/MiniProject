package sparta.miniproject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String userContent;
    private String nickname;


    @Builder
    public CommentResponseDto(Long id, String userContent, String nickname) {
        this.id = id;
        this.userContent = userContent;
        this.nickname = nickname;
    }

}
