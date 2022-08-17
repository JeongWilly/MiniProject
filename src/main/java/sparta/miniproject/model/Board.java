package sparta.miniproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.miniproject.Timestamped;
import sparta.miniproject.dto.BoardRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
@Table(name="Board")
public class Board extends Timestamped {

    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String nickname;


    private String title;

    @Column(nullable=false)
    private String content;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Board(String nickname, String title, String content, List<Comment> commentList) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.commentList = commentList;
    }

    public void update(BoardRequestDto boardRequestDto){
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

}
