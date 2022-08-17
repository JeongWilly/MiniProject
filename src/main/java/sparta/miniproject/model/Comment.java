package sparta.miniproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.miniproject.dto.BoardRequestDto;
import sparta.miniproject.dto.CommentRequestDto;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long commentId;

//    @Column(nullable = false,insertable = false, updatable = false)
    private String nickname;

    @Column(nullable = false)
    private String userContent;



    //comment board
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="boardId",nullable=false)
    private Board board;
//
//    @ManyToOne
//    @JsonBackReference
//    private Member member;



    public Comment(String nickname, CommentRequestDto commentRequestDto,Board board){
        this.nickname=nickname;
        this.userContent=commentRequestDto.getContent();
        this.board=board;
    }

    public void updateCmt(CommentRequestDto commentRequestDto){
        this.userContent = commentRequestDto.getContent();
    }


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    @JsonBackReference
//    private parent;




}
