package kr.co.swadpia.repository.jpa.common;

import kr.co.swadpia.system.entity.CommonGroupCode;
import kr.co.swadpia.repository.jpa.GenericRepository;
import kr.co.swadpia.repository.jpa.common.impl.CommonGroupCodeRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonGroupCodeRepository extends GenericRepository<CommonGroupCode>, CommonGroupCodeRepositoryCustom {
}
