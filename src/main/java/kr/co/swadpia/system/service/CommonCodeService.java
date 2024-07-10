package kr.co.swadpia.system.service;

import kr.co.swadpia.dto.common.CommonCodeCondition;
import kr.co.swadpia.dto.common.CommonCodeDTO;
import kr.co.swadpia.dto.common.CommonCodeParam;
import kr.co.swadpia.system.entity.CommonCode;
import kr.co.swadpia.system.entity.CommonGroupCode;
import kr.co.swadpia.repository.jpa.common.CommonCodeRepository;
import kr.co.swadpia.repository.jpa.common.CommonGroupCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommonCodeService {

	private final CommonCodeRepository commonCodeRepository;

	private final CommonGroupCodeRepository commonGroupCodeRepository;

	@Transactional
	public void insertCommonCode (CommonCodeParam commonCodeParam)  {

		CommonCode commonCode = new CommonCode();
		BeanUtils.copyProperties(commonCodeParam, commonCode);
		commonCode.setId(0L);

		Optional<CommonGroupCode> optionalCommonGroupCode = commonGroupCodeRepository.findById(commonCodeParam.getGroupId());
		optionalCommonGroupCode.ifPresentOrElse(
			commonGroupCode -> {
				commonCode.setGroup(commonGroupCode);
				commonCodeRepository.save(commonCode);
			},
			() -> {throw new RuntimeException("공통그룹코드를 찾을 수 없습니다.");}
		);
	}

	@Transactional
	public void modifyCommonCode (CommonCodeParam commonCodeParam)  {
		Optional<CommonCode> optionalCommonCode = commonCodeRepository.findById(commonCodeParam.getId());
		optionalCommonCode.ifPresentOrElse(
			commonCode -> {
				BeanUtils.copyProperties(commonCodeParam, commonCode, "parentCodeId", "groupId");
				commonCodeRepository.save(commonCode);
			},
			() -> {throw new RuntimeException("공통코드를 찾을 수 없습니다.");}
		);
	}

	public CommonCodeDTO getCommonCode (Long codeId)  {
		Optional<CommonCode> optionalCommonCode = commonCodeRepository.findById(codeId);
		CommonCodeDTO commonCodeDTO = new CommonCodeDTO();
		optionalCommonCode.ifPresentOrElse(
			commonCode -> {
				BeanUtils.copyProperties(commonCode, commonCodeDTO);
			},
			() -> {throw new RuntimeException("공통코드를 찾을 수 없습니다.");}
		);

		return commonCodeDTO;
	}

	@Transactional
	public void removeCommonCode (Long codeId)  {
		Optional<CommonCode> optionalCommonCode = commonCodeRepository.findById(codeId);
		optionalCommonCode.ifPresentOrElse(
			commonCode -> {
				commonCode.setUseYn(false);
				commonCodeRepository.save(commonCode);
			},
			() -> {throw new RuntimeException("공통코드를 찾을 수 없습니다.");}
		);
	}

	public List<CommonCodeDTO> searchCommonCodeList (CommonCodeCondition commonCodeCondition)  {

		return commonCodeRepository.search(commonCodeCondition);
	}
}
