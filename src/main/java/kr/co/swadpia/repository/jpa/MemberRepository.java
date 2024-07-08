package kr.co.swadpia.repository.jpa;

import kr.co.swadpia.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends GenericRepository<Member> {

    Optional<Member> findByEmail(String memberId);

    Integer countAllByEmail(String email);

    Integer countAllByMobile(String mobile);

    Optional<Member> findByNameAndMobile(String name, String mobile);

    Optional<Member> findByVerificationCode(String verificationCode);

    Integer countAllByEmailAndName(String email, String name);

    Optional<Member> findByEmailAndName(String email, String name);

    List<Member> findByMemberIdIn(List<Long> memberId);

}
