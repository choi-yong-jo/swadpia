package kr.co.swadpia.repository.elastic;

import kr.co.swadpia.test.document.EmployeeDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeElasticSearchRepository extends ElasticsearchRepository<EmployeeDocument, String> {

	List<EmployeeDocument> findByNameKr(String name);

	@Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}, {\"range\": {\"price\": {\"gte\": \"?1\", \"lte\": \"?2\"}}}]}}")
	List<EmployeeDocument> findByNameAndPriceRange(@Param("name") String name, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

	@Query("{\"bool\": {\"should\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"category\": \"?1\"}}]}}")
	List<EmployeeDocument> findByNameOrCategory(@Param("name") String name, @Param("category") String category);
}
