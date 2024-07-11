package kr.co.swadpia.repository.jpa;


import kr.co.swadpia.common.constant.EmailType;
import kr.co.swadpia.common.entity.EmailTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends GenericRepository<EmailTemplate> {

	Optional<EmailTemplate> findByType(EmailType emailType);
}
