package kr.co.swadpia.web.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.dto.customer.CustomerCondition;
import kr.co.swadpia.dto.customer.CustomerDTO;
import kr.co.swadpia.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "customer")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v0/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "customerList", description = "고객관리 리스트")
    @GetMapping(value = "/customer-list")
    public Page<CustomerDTO> customerList(CustomerCondition condition) {
        return customerService.customerList(condition);
    }

}
