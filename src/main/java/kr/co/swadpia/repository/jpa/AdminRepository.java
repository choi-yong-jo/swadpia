package kr.co.swadpia.repository.jpa;

import kr.co.swadpia.entity.Admin;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends GenericRepository<Admin>, QuerydslPredicateExecutor<Admin> {
    Optional<Admin> findByEmail(String email);

}
