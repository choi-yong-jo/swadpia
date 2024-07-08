package kr.co.swadpia.web.elastic;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swadpia.document.EmployeeDocument;
import kr.co.swadpia.service.elastic.EmployeeElasticSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "elastic-search")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/elastic")
public class ElasticSearchController {

	private final EmployeeElasticSearchService employeeElasticSearchService;

	/**
	 * 엘라스틱서치 저장및 수정은 put으로.. 포스트도 사용하는데 put이 요래저래 쓰기좋음
	 * @param employeeDocument employee 데이터
	 * @return 저장된 employee데이터
	 */
	
	@PutMapping("/employee")
	public EmployeeDocument insertEmployee(@RequestBody EmployeeDocument employeeDocument) {
		return employeeElasticSearchService.save(employeeDocument);
	}

	@PostMapping("/employee")
	public Iterable<EmployeeDocument> getAllEmployeeDocument() {
		return employeeElasticSearchService.findAll();
	}

	@DeleteMapping("/employee/{id}")
	public void deleteProduct(@PathVariable String id) {
		employeeElasticSearchService.deleteById(id);
	}

	@GetMapping("/employee/search/{name}")
	public List<EmployeeDocument> getProductsByName(@PathVariable String name) { 
		return employeeElasticSearchService.findByName(name);
	}

	@GetMapping("/employee/search/nameAndPrice")
	public List<EmployeeDocument> getProductsByNameAndPriceRange(@RequestParam String name, @RequestParam double minPrice, @RequestParam double maxPrice) {
		return employeeElasticSearchService.findByNameAndPriceRange(name, minPrice, maxPrice);
	}

	@GetMapping("/employee/search/nameOrCategory")
	public List<EmployeeDocument> getProductsByNameOrCategory(@RequestParam String name, @RequestParam String category) {
		return employeeElasticSearchService.findByNameOrCategory(name, category);
	}

	/**
	 * 엘라스틱서치 현재 클러스터 모든 인덱스 조회
	 * 
	 * @return 현재 클러스터 모든 인덱스 정보
	 */
	@GetMapping("/indices")
	//@RequiredLogin
	public String getAllIndices() {
		return employeeElasticSearchService.getAllIndices();
	}
}

