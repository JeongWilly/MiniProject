package sparta.miniproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sparta.miniproject.model.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
}
