package kr.co.swadpia.sample;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.swadpia.entity.Member;
import kr.co.swadpia.sample.entity.MemberSample;
import kr.co.swadpia.sample.repository.MemberRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static io.micrometer.common.util.StringUtils.isEmpty;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition) {
        //푸쉬테스트
        return null;
    }


    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {
        QueryResults<MemberTeamDto> results = null;

        List<MemberTeamDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MemberTeamDto> searchComplex(MemberSearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> content = null;

        long total = 10;

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MemberTeamDto> searchComplexCountQuery(MemberSearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> content = null;

        JPAQuery<MemberSample> countQuery = null;
        return PageableExecutionUtils.getPage(content, pageable,countQuery::fetchCount);
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