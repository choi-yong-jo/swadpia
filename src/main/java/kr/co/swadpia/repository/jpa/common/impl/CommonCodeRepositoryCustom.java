package kr.co.swadpia.repository.jpa.common.impl;

import kr.co.swadpia.dto.common.CommonCodeCondition;
import kr.co.swadpia.dto.common.CommonCodeDTO;

import java.util.List;

public interface CommonCodeRepositoryCustom {
	List<CommonCodeDTO> search(CommonCodeCondition condition);
}
