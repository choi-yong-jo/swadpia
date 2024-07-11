package kr.co.swadpia.repository.jpa;

import kr.co.swadpia.member.entity.Role;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends GenericRepository<Role>, QuerydslPredicateExecutor<Role> {

	Role findTopByRoleId(String roleId);

	Optional<Role> findByRoleIdAndName(String roleId, String roleNm);

	Iterable<Object> findByUseYnOrderByRoleSeq(String useYn);
}
