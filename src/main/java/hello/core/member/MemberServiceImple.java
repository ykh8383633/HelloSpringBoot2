package hello.core.member;

public class MemberServiceImple implements MemberService{

    // MemberRepository memberRepository = new MemoryMemberRepository();
    MemberRepository memberRepository;

    public MemberServiceImple(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
