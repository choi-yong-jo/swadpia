package kr.co.swadpia.repository.jpa.system.custom;

import kr.co.swadpia.member.dto.MemberSearchCondition;
import kr.co.swadpia.member.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuGroupAuthorityRepositoryCustom {

	Page<MemberTeamDto> searchComplexCountQuery(MemberSearchCondition condition, Pageable pageable);
}
