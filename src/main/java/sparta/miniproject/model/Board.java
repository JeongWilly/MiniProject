package sparta.miniproject.model;
import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import sparta.miniproject.Timestamped;
import sparta.miniproject.dto.BoardRequestDto;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable=false)
    private String nickname;


    private String title;

    @Column(nullable=false)
    private String content;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> commentList = new ArrayList<>();

//    public Board(BoardRequestDto boardRequestDto, Member member) {
//        this.nickname = member.getNickname();   //작성자 기준으로 물어볼것 user?nickname
//        this.title = boardRequestDto.getTitle();
//        this.content = boardRequestDto.getContent();
//        this.member =member;
//        //VOID에 따라 생성자와 메서드가 갈림
//    }

    public Board(BoardRequestDto boardRequestDto, String nickname) {
//        this.nickname = member.getNickname();   //작성자 기준으로 물어볼것 user?nickname
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.nickname =nickname;
        //VOID에 따라 생성자와 메서드가 갈림
    }


    public void update(BoardRequestDto boardRequestDto){
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }
}
