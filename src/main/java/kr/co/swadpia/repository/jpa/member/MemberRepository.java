package kr.co.swadpia.repository.jpa.member;

import kr.co.swadpia.member.entity.Member;
import kr.co.swadpia.repository.jpa.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends GenericRepository<Member> {

    Optional<Member> findByEmail(String memberId);

    Integer countAllByEmail(String email);

    Integer countAllByMobile(String mobile);

    Optional<Member> findByNameAndMobile(String name, String mobile);

    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByEmailAndName(String email, String name);

    Iterable<Object> findByUseYn(String useYn);

    Optional<Member> findByMemberSeq(Long memberSeq);
}
