package kr.co.swadpia.repository.jpa.customer.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.swadpia.dto.customer.CustomerCondition;
import kr.co.swadpia.dto.customer.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static io.micrometer.common.util.StringUtils.isEmpty;


@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public Page<CustomerDTO> customerList(CustomerCondition condition) {

		BooleanExpression query = getQuery(condition);

		List<CustomerDTO> customers = null;

		long totalRecords = 10;

		return new PageImpl<>(customers, condition.getPageable(), totalRecords);
	}

	private BooleanExpression getQuery(CustomerCondition condition) {
		return null;
	}

	private BooleanBuilder search(CustomerCondition condition) {
		return null;
	}

}
