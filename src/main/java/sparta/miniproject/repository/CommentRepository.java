package sparta.miniproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sparta.miniproject.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
