package sparta.miniproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sparta.miniproject.model.Board;
import sparta.miniproject.model.Member;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,  Long> {
    Optional<Board> findByBoardId(String nickname);

}
