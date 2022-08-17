package sparta.miniproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sparta.miniproject.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoard(Long id);
}
