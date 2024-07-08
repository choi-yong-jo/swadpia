package kr.co.swadpia.repository.jpa.customer.custom;

import kr.co.swadpia.dto.customer.CustomerCondition;
import kr.co.swadpia.dto.customer.CustomerDTO;
import org.springframework.data.domain.Page;

public interface CustomerRepositoryCustom {

	Page<CustomerDTO> customerList(CustomerCondition condition);

}
