package kr.co.swadpia.web.payroll;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.dto.customer.CustomerCondition;
import kr.co.swadpia.dto.customer.CustomerDTO;
import kr.co.swadpia.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "payroll")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v0/payroll")
public class PayrollController {

    private final CustomerService customerService;

    @Operation(summary = "payrollList", description = "급여대장")
    @RequestMapping(value = "/payroll-list", method = RequestMethod.GET)
    public Page<CustomerDTO> customerList(CustomerCondition condition) {
        return customerService.customerList(condition);
    }

}
