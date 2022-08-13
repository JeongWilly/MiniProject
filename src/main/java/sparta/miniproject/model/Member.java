package sparta.miniproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.miniproject.Timestamped;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member extends Timestamped {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
   private Long id;
   @Column(nullable = false, unique = true)
   private String username;

   @Column
   private String nickname;

   @JsonIgnore
   @Column(nullable = false)
   private String password;

   @Enumerated(EnumType.STRING)
   private Authority authority;



   @Builder
   public Member(String username, String password, Authority authority) {
      this.username = username;
      this.password = password;
      this.authority = authority;
   }
}