package kr.co.swadpia.repository.jpa.common.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.swadpia.dto.common.CommonCodeCondition;
import kr.co.swadpia.dto.common.CommonCodeDTO;
import kr.co.swadpia.entity.common.CommonCode;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

import static io.micrometer.common.util.StringUtils.isEmpty;

public class CommonCodeRepositoryImpl implements CommonCodeRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public CommonCodeRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<CommonCodeDTO> search(CommonCodeCondition searchDTO) {
		List<CommonCode> results = null;

		return results.stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());
	}

	private CommonCodeDTO convertToDTO(CommonCode commonCode) {
		List<CommonCodeDTO> children = commonCode.getChildren().stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());

		return new CommonCodeDTO(commonCode.getId(),
			commonCode.getCode(),
			commonCode.getCodeName(),
			commonCode.getDescription(),
			commonCode.getDepth(),
			parentIdNullCheck(commonCode.getParent()),
			commonCode.getUseYn(),
			children);
	}

	private BooleanExpression code(String code) {
		return null;
	}

	private BooleanExpression codeName(String codeName) {
		return null;
	}

	private BooleanExpression groupId(Long groupId) {
		return null;
	}

	private Long parentIdNullCheck(CommonCode commonCode) {
		if(ObjectUtils.isEmpty(commonCode)) {
			return 0L;
		} else {
			return commonCode.getId();
		}
	}
}
