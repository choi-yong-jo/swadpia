package kr.co.swadpia.service.common;

import kr.co.swadpia.dto.common.CommonGroupCodeCondition;
import kr.co.swadpia.dto.common.CommonGroupCodeDTO;
import kr.co.swadpia.dto.common.CommonGroupCodeParam;
import kr.co.swadpia.entity.common.CommonGroupCode;
import kr.co.swadpia.repository.jpa.common.CommonGroupCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonGroupCodeService {

	private final CommonGroupCodeRepository commonGroupCodeRepository;

	public void insertCommonGroupCode(CommonGroupCodeParam commonGroupCodeParam)  {

		CommonGroupCode commonGroupCode = new CommonGroupCode();
		BeanUtils.copyProperties(commonGroupCodeParam, commonGroupCode);
		commonGroupCode.setGroupId(0L);

		commonGroupCodeRepository.save(commonGroupCode);

	}

	public void modifyCommonGroupCode(CommonGroupCodeParam commonGroupCodeParam)  {
		Optional<CommonGroupCode> optionalCommonCode = commonGroupCodeRepository.findById(commonGroupCodeParam.getGroupId());
		optionalCommonCode.ifPresentOrElse(
			commonGroupCode -> {
				BeanUtils.copyProperties(commonGroupCodeParam, commonGroupCode);
				commonGroupCodeRepository.save(commonGroupCode);
			},
			() -> {throw new RuntimeException("공통그룹코드를 찾을 수 없습니다.");}
		);
	}

	public CommonGroupCodeDTO getCommonGroupCode(Long groupCodeId)  {
		Optional<CommonGroupCode> optionalCommonGroupCode = commonGroupCodeRepository.findById(groupCodeId);
		CommonGroupCodeDTO commonGroupCodeDTO = new CommonGroupCodeDTO();
		optionalCommonGroupCode.ifPresentOrElse(
			commonGroupCode -> {
				BeanUtils.copyProperties(commonGroupCode, commonGroupCodeDTO);
			},
			() -> {throw new RuntimeException("공통그룹코드를 찾을 수 없습니다.");}
		);

		return commonGroupCodeDTO;
	}

	public void removeCommonGroupCode(Long groupCodeId)  {
		Optional<CommonGroupCode> optionalCommonGroupCode = commonGroupCodeRepository.findById(groupCodeId);
		optionalCommonGroupCode.ifPresentOrElse(
			commonGroupCode -> {
				commonGroupCode.setUseYn(false);
				commonGroupCodeRepository.save(commonGroupCode);
			},
			() -> {throw new RuntimeException("공통그룹코드를 찾을 수 없습니다.");}
		);
	}

	public List<CommonGroupCodeDTO> searchCommonGroupCodeList(CommonGroupCodeCondition commonGroupCodeCondition)  {

		return commonGroupCodeRepository.search(commonGroupCodeCondition);
	}
}
