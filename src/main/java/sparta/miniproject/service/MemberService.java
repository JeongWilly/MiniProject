package sparta.miniproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.miniproject.dto.MemberResponseDto;
import sparta.miniproject.repository.MemberRepository;
import sparta.miniproject.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String nickname) {
        return memberRepository.findByNickname(nickname)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
    }
}