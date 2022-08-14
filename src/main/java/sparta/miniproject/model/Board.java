package sparta.miniproject.model;
import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparta.miniproject.dto.BoardRequestDto;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name="Board")
public class Board  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;

    @Column(nullable=false)
    private String nickname;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String content;

    @ManyToOne
    private Member member;

    public Board(BoardRequestDto boardRequestDto, Member member) {
        this.nickname = member.getNickname();   //작성자 기준으로 물어볼것 user?nickname
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.member =member;
        //VOID에 따라 생성자와 메서드가 갈림
    }


    public void update(BoardRequestDto boardRequestDto){
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }








}
