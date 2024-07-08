package kr.co.swadpia.repository.jpa;

import com.querydsl.core.types.Predicate;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T, Long>, QuerydslPredicateExecutor<T>,
    JpaSpecificationExecutor<T> {


    @Override
    Page<T> findAll(Pageable pageable);

    @Override
    List<T> findAll(Predicate predicate);

    @Override
    Page<T> findAll(Predicate predicate, Pageable pageable);

    @Override
    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);

    @Override
    List<T> findAll(@Nullable Specification<T> spec);


    @Override
    Optional<T> findById(Long aLong);
}
