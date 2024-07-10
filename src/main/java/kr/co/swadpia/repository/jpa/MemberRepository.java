package kr.co.swadpia.repository.jpa;

import kr.co.swadpia.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends GenericRepository<Member> {

    Optional<Member> findByEmail(String memberId);

    Integer countAllByEmail(String email);

    Integer countAllByMobile(String mobile);

    Optional<Member> findByNameAndMobile(String name, String mobile);

    Optional<Member> findByMemberId(String memberId);

    Integer countAllByEmailAndName(String email, String name);

    Optional<Member> findByEmailAndName(String email, String name);

    List<Member> findByMemberIdIn(List<Long> memberId);

}
