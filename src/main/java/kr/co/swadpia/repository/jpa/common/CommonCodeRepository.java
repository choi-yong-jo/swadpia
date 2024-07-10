package kr.co.swadpia.repository.jpa.common;

import kr.co.swadpia.system.entity.CommonCode;
import kr.co.swadpia.repository.jpa.GenericRepository;
import kr.co.swadpia.repository.jpa.common.impl.CommonCodeRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonCodeRepository extends GenericRepository<CommonCode>, CommonCodeRepositoryCustom {
}
