package kr.co.swadpia.repository.jpa;

import kr.co.swadpia.common.entity.EmailQueue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailQueueRepository extends GenericRepository<EmailQueue> {

	List<EmailQueue> findAllByCompleteOrderByEmailQueueIdAsc(Boolean complete);
}
