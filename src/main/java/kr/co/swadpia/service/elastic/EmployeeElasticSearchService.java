package kr.co.swadpia.service.elastic;

import kr.co.swadpia.document.EmployeeDocument;
import kr.co.swadpia.repository.elastic.EmployeeElasticSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeElasticSearchService {

	private final EmployeeElasticSearchRepository employeeElasticSearchRepository;
	private final RestTemplate restTemplate;

	@Value("${spring.elasticsearch.uris}")
	private String elasticsearchUrl;

	public EmployeeDocument save(EmployeeDocument employeeDocument) {

		return employeeElasticSearchRepository.save(employeeDocument);
	}

	public Iterable<EmployeeDocument> findAll() {
		return employeeElasticSearchRepository.findAll();
	}

	public void deleteById(String id) {
		employeeElasticSearchRepository.deleteById(id);
	}

	public List<EmployeeDocument> findByName(String name) {
		return employeeElasticSearchRepository.findByNameKr(name);
	}

	public List<EmployeeDocument> findByNameAndPriceRange(String name, double minPrice, double maxPrice) {
		return employeeElasticSearchRepository.findByNameAndPriceRange(name, minPrice, maxPrice);
	}

	public List<EmployeeDocument> findByNameOrCategory(String name, String category) {
		return employeeElasticSearchRepository.findByNameOrCategory(name, category);
	}

	public String getAllIndices() {
		return restTemplate.getForObject(elasticsearchUrl + "/_cat/indices?v&format=json", String.class);
	}
}
