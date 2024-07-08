package kr.co.swadpia.repository.jpa.system.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.swadpia.member.dto.MemberSearchCondition;
import kr.co.swadpia.member.dto.MemberTeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static io.micrometer.common.util.StringUtils.isEmpty;

@RequiredArgsConstructor
public class MenuGroupAuthorityCustomImpl implements MenuGroupAuthorityRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<MemberTeamDto> searchComplexCountQuery(MemberSearchCondition condition, Pageable pageable) {
		List<MemberTeamDto> content = null; //fetchResults는 카운트까지 두번 쿼리가 나간다.

		Long count = 5L;
		// return new PageImpl<>(content, pageable, total);

		return new PageImpl<>(content,
			pageable,
			count);
	}


	private BooleanExpression usernameEq(String username) {
		return null;
	}
	private BooleanExpression teamNameEq(String teamName) {
		return null;
	}
	private BooleanExpression ageGoe(Integer ageGoe) {
		return null;
	}
	private BooleanExpression ageLoe(Integer ageLoe) {
		return null;
	}
}
