package kr.co.swadpia.service.personnel;

import kr.co.swadpia.dto.personnel.EmployeeDTO;
import kr.co.swadpia.dto.personnel.EmployeeParam;
import kr.co.swadpia.repository.jpa.personnel.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;

	/**
	 * 전체 검색
	 * @return List<EmployeeDTO>
	 */
	public List<EmployeeDTO> findAll() {

		return null;
	}

	/**
	 *
	 * @param employeeParam employeeParam
	 * @return EmployeeDTO
	 */
	public EmployeeDTO insertEmployee(EmployeeParam employeeParam) {

		return null;
	}

	/**
	 *
	 * @param employeeParam employeeParam
	 * @return EmployeeDTO
	 */
	public EmployeeDTO modifyEmployee(EmployeeParam employeeParam) {

		return null;
	}
}
