package sparta.miniproject.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sparta.miniproject.dto.BoardRequestDto;
import sparta.miniproject.dto.BoardResponseDto;
import sparta.miniproject.model.Board;
import sparta.miniproject.model.Member;
import sparta.miniproject.repository.BoardRepository;
import sparta.miniproject.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Getter
public class BoardService {

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    public String getNickname() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(); //로그인해서 뽑은 아이디르 넣음
        Optional<Member> member = memberRepository.findById(Long.valueOf(userName));
        return member.get().getNickname();
    }


    public Member getMember(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findById(Long.valueOf(userId))
                .orElseThrow(()-> new RuntimeException("유저를 찾지 못했습니다."));
        return member;
    }


    //게시물작성
    public Board createBoard(BoardRequestDto boardRequestDto){ // ? IOException?
//        System.out.println("여기2");
       Member member=getMember();
        String nickname = getNickname();
//        Board board = new Board(boardRequestDto,member);
        Board board = new Board(boardRequestDto,nickname);
       member.getBoardList().add(board);
       boardRepository.save(board);
       return board;
    }
//
    //게시물조회
    public List<BoardResponseDto> getBoard(){
    List<BoardResponseDto>boardResponseDtoList = new ArrayList<>();
    List<Board>board = boardRepository.findAll();
    for(Board brd1 : board){
        boardResponseDtoList.add(new BoardResponseDto(brd1));
    }return boardResponseDtoList;


    }

    //게시물 상세 조회
    public Board getEachBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () ->new IllegalArgumentException("찾는 게시물이 존재하지 않습니다.")
        );
        return board;
    }


    //게시물 수정
    @Transactional
    public void update(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("아이디가 없습니다"));
        System.out.println(boardRequestDto.getContent());

        if(getNickname().equals(board.getNickname())){
//            Board boardDelete=boardRepository

            board.update(boardRequestDto);


        }else{
            throw new IllegalArgumentException("여긴 못 지나간다"); // 예외처리를 던져줄때는 throw
        }

    }


    //게시물 삭제
    public Long deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다"));


        if(getNickname().equals(board.getNickname())){
//            Board boardDelete=boardRepository

            boardRepository.deleteById(boardId);
//        System.out.println("여기1");
            return boardId;
        }else{
            throw new IllegalArgumentException("여긴 못 지나간다"); // 예외처리를 던져줄때는 throw
        }

    }

}
