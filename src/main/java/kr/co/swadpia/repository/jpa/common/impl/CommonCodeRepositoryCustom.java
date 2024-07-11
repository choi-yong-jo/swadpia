package kr.co.swadpia.repository.jpa.common.impl;

import kr.co.swadpia.system.dto.common.CommonCodeCondition;
import kr.co.swadpia.system.dto.common.CommonCodeDTO;

import java.util.List;

public interface CommonCodeRepositoryCustom {
	List<CommonCodeDTO> search(CommonCodeCondition condition);
}
