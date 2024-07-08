package kr.co.swadpia.repository.jpa.customer;

import kr.co.swadpia.entity.customer.Customer;
import kr.co.swadpia.repository.jpa.GenericRepository;
import kr.co.swadpia.repository.jpa.customer.custom.CustomerRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends GenericRepository<Customer>, CustomerRepositoryCustom {

}
