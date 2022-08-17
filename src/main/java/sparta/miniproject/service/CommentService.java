package sparta.miniproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sparta.miniproject.dto.CommentRequestDto;
import sparta.miniproject.model.Board;
import sparta.miniproject.model.Comment;
import sparta.miniproject.model.Member;
import sparta.miniproject.repository.BoardRepository;
import sparta.miniproject.repository.CommentRepository;
import sparta.miniproject.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    private final BoardRepository boardRepository;


    //GET방식에서는 Transactional을 쓸 필요x 클래스 전체에 건다기보단
    public String getNickname() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName(); //로그인해서 뽑은 아이디르 넣음
        Optional<Member> member = memberRepository.findById(Long.valueOf(userId));
        return member.get().getNickname();
    }


    //Comment 작성
    @Transactional
   public Comment createComment(Long boardId,CommentRequestDto commentRequestDto) {
        String nickname=getNickname();
        Board board=boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 존재하지 않습니다"));
        Comment comment=new Comment(nickname,commentRequestDto,board); //해당 댓글이 어디에 달렸는지가 안달려있음,양측에 다 기입해줘야함

        board.addComment(comment);

        return commentRepository.save(comment);
    }

    //Comment 수정
    @Transactional
    public void updateComment(Long commentId, CommentRequestDto commentRequestDto){
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다.(댓글)"+commentId));

      if(getNickname().equals(comment.getNickname())){
                comment.updateCmt(commentRequestDto);
//                commentRepository.save(comment);

        }else{
          throw new IllegalArgumentException("아이디가 일치하지 않습니다"); // 예외처리를 던져줄때는 throw
      }
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다.(댓글)"+commentId));


        if(getNickname().equals(comment.getNickname())){
//            Board boardDelete=boardRepository

            commentRepository.delete(comment);


        }else{
            throw new IllegalArgumentException("여긴 못 지나간다"); // 예외처리를 던져줄때는 throw
        }
    }


//    public Comment updateComment(Long board_Id,CommentRequestDto commentRequestDto){
//        Board board = boardRepository.findById(board_id)
//                .orElseThrow(()->)
//
//    }
}
