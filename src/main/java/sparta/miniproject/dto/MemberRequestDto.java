package sparta.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import sparta.miniproject.model.Authority;
import sparta.miniproject.model.Member;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
//
   private String username;

   private String password;

   private String passwordConfirm;

   public Member toMember(PasswordEncoder passwordEncoder) {
      return Member.builder()
              .username(username)
              .password(passwordEncoder.encode(password))
              .authority(Authority.ROLE_USER)
              .build();
   }

   public UsernamePasswordAuthenticationToken toAuthentication() {
      return new UsernamePasswordAuthenticationToken(username, password);
   }
}

