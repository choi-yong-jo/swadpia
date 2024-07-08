package kr.co.swadpia.repository.jpa.system;

import kr.co.swadpia.entity.system.Menu;
import kr.co.swadpia.repository.jpa.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends GenericRepository<Menu> {
}
