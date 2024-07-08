package kr.co.swadpia.service.customer;

import kr.co.swadpia.dto.customer.CustomerCondition;
import kr.co.swadpia.dto.customer.CustomerDTO;
import kr.co.swadpia.repository.jpa.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	public Page<CustomerDTO> customerList(CustomerCondition condition) {
		return customerRepository.customerList(condition);
	}

}
