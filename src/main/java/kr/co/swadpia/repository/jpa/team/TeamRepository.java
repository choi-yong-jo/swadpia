package kr.co.swadpia.repository.jpa.team;

import kr.co.swadpia.repository.jpa.GenericRepository;
import kr.co.swadpia.team.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends GenericRepository<Team> {

    Iterable<Object> findByUseYnOrderByTeamSeqDesc(String useYn);
}
