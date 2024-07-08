package kr.co.swadpia.repository.jpa;

import kr.co.swadpia.member.entity.Role;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<Role>, QuerydslPredicateExecutor<Role> {

	Role findTopByValue(String value);
}
