package kr.co.swadpia.repository.jpa.common.impl;

import kr.co.swadpia.system.dto.common.CommonGroupCodeCondition;
import kr.co.swadpia.system.dto.common.CommonGroupCodeDTO;

import java.util.List;

public interface CommonGroupCodeRepositoryCustom {

	List<CommonGroupCodeDTO> search(CommonGroupCodeCondition condition);
}
