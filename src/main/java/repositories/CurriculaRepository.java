package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;

@Repository
public interface CurriculaRepository extends JpaRepository<Curricula, Integer> {
	@Query("select c from Candidate ca join ca.curriculas c where ca.id = ?1")
	List<Curricula> getCurriculasByCandidate(int candidate_id);
	
	@Query("select avg(ca.curriculas.size) from Candidate ca")
	Double avgCurriculasByCandidate();
	
}
