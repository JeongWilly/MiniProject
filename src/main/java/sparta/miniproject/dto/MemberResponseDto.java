package sparta.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.miniproject.model.Member;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String nickname;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getNickname());
    }
}

