package sparta.miniproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.miniproject.dto.CommentRequestDto;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false,insertable = false, updatable = false)
    private String nickname;

    @Column(nullable = false)
    private String userContent;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="boardId",nullable=false)
    private Board board;


    public Comment(String nickname, CommentRequestDto commentRequestDto,Board board){
        this.nickname=nickname;
        this.userContent=commentRequestDto.getContent();
        this.board=board;
    }

    public void updateCmt(CommentRequestDto commentRequestDto){
        this.userContent = commentRequestDto.getContent();
    }

}
