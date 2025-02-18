package kr.co.swadpia.repository.jpa.member;

import kr.co.swadpia.member.entity.MemberRole;
import kr.co.swadpia.repository.jpa.GenericRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRoleRepository extends GenericRepository<MemberRole>, QuerydslPredicateExecutor<MemberRole> {
    List<MemberRole> findByMemberSeqOrderByRoleSeq(Long memberSeq);
    Optional<MemberRole> findByMemberSeqAndRoleSeq(long memberSeq, long roleSeq);

    void deleteByMemberSeq(Long memberSeq);
}
