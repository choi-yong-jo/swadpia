package kr.co.swadpia.sample.repository;

import kr.co.swadpia.entity.Member;
import kr.co.swadpia.repository.jpa.GenericRepository;

public interface QueryDslSampleRepository extends GenericRepository<Member>, MemberRepositoryCustom {
}
