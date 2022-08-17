package sparta.miniproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.miniproject.Timestamped;

import javax.persistence.*;
import java.util.List;



@Getter
@NoArgsConstructor
@Entity
public class Member extends Timestamped {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
   private Long memberId;

   @Column(nullable = false, unique = true)
   private String username;


   @Column(nullable = false, unique = true)
   private String nickname;

   @JsonIgnore
   @Column(nullable = false)
   private String password;

   @Enumerated(EnumType.STRING)
   private Authority authority;


   @JsonManagedReference
   @OneToMany(fetch = FetchType.LAZY,mappedBy = "member" )
   private List<Board> boardList;

//   @JsonManagedReference
//   @OneToMany(fetch=FetchType.LAZY)
//   private List<Comment> commentList;




   @Builder
   public Member(String username, String nickname, String password, Authority authority) {
      this.username = username;
      this.nickname = nickname;
      this.password = password;
      this.authority = authority;
   }


}
