package kr.co.swadpia.system.service;

import kr.co.swadpia.common.dto.SessionDTO;
import kr.co.swadpia.system.dto.system.MenuGroupAuthorityDTO;
import kr.co.swadpia.system.dto.system.MenuGroupAuthorityParam;
import kr.co.swadpia.common.entity.MenuGroupAuthority;
import kr.co.swadpia.repository.jpa.system.MenuGroupAuthorityRepository;
import kr.co.swadpia.common.utility.PlannCodesBeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuGroupAuthorityService {

	private  MenuGroupAuthorityRepository menuGroupAuthorityRepository;

	/**
	 * 그룹메뉴권한 등록
	 * @param sessionDTO 세션DTO
	 * @param menuGroupAuthorityParam 메뉴그룹권한 등록 데이터
	 * @return MenuGroupAuthorityDTO
	 */
	@Transactional
	public MenuGroupAuthorityDTO insertMenuGroupAuthority(SessionDTO sessionDTO, MenuGroupAuthorityParam menuGroupAuthorityParam) {

		MenuGroupAuthority saveEntity = menuGroupAuthorityRepository.save(MenuGroupAuthority.of(menuGroupAuthorityParam));

		return PlannCodesBeanUtil.copyPropertiesReturnTargetClass(saveEntity,new MenuGroupAuthorityDTO());
	}
	
	/**
	 * 그룹메뉴권한 수정
	 * @param sessionDTO 세션DTO
	 * @param menuGroupAuthorityParam 메뉴그룹권한 수정 데이터
	 * @return MenuGroupAuthorityDTO
	 */
	@Transactional
	public MenuGroupAuthorityDTO modifyMenuGroupAuthority(SessionDTO sessionDTO, MenuGroupAuthorityParam menuGroupAuthorityParam) {

		MenuGroupAuthority menuGroupAuthority = menuGroupAuthorityRepository.findById(menuGroupAuthorityParam.getId())
			.orElseThrow(() -> new RuntimeException("메뉴가 존재하지 않습니다."));

		/*
		 * 업데이트 로직처리
		 *
 		 */

		/////////

		return PlannCodesBeanUtil.copyPropertiesReturnTargetClass(menuGroupAuthority,new MenuGroupAuthorityDTO());
	}

	/**
	 * 메뉴그룹권한 단건 조회
	 * @param sessionDTO 세션DTO
	 * @param id  메뉴그룹권한 ID
	 * @return MenuGroupAuthorityDTO
	 */
	public MenuGroupAuthorityDTO getMenuGroupAuthority(SessionDTO sessionDTO, String id) {
		return null;
	}
}
