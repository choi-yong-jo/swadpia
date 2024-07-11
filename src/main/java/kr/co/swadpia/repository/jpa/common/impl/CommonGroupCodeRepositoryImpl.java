package kr.co.swadpia.repository.jpa.common.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.swadpia.system.dto.common.CommonGroupCodeCondition;
import kr.co.swadpia.system.dto.common.CommonGroupCodeDTO;

import java.util.List;

public class CommonGroupCodeRepositoryImpl implements CommonGroupCodeRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public CommonGroupCodeRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<CommonGroupCodeDTO> search(CommonGroupCodeCondition condition) {
		return null;
	}

	private BooleanExpression groupId(Long groupId) {
		return null;
	}

	private BooleanExpression groupName(String groupName) {
		return null;
	}

	private BooleanExpression groupCode(String groupCode) {
		return null;
	}

}
