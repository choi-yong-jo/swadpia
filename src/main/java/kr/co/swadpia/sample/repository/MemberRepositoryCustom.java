package kr.co.swadpia.sample.repository;

import kr.co.swadpia.sample.MemberSearchCondition;
import kr.co.swadpia.sample.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSearchCondition condition);

    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchComplex(MemberSearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchComplexCountQuery(MemberSearchCondition condition, Pageable pageable);

}
