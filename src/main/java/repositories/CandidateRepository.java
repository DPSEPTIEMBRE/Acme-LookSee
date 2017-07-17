package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
	
	@Query("select c from Candidate c order by c.curriculas.size DESC")
	List<Candidate> orderByNumCurriculas();

}
