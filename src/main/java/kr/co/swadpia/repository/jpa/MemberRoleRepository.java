package kr.co.swadpia.repository.jpa;

import kr.co.swadpia.entity.MemberRole;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRoleRepository extends GenericRepository<MemberRole>, QuerydslPredicateExecutor<MemberRole> {
}
