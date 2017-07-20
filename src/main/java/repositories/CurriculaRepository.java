package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;

@Repository
public interface CurriculaRepository extends JpaRepository<Curricula, Integer> {

	//Curriculums por candidato
	@Query("select c.curriculas from Candidate c where c.id = ?1")
	List<Curricula> getCurriculasByCandidate(int candidate_id);

	//Curriculums  agrupado por candidato
	@Query("select c.curriculas from Candidate c group by c")
	List<Curricula> getCurriculasGroupByCandidate();

	//Media de curriculums por candidato
	@Query("select avg(ca.curriculas.size) from Candidate ca")
	Double avgCurriculasByCandidate();

	//Curriculums por Oferta
	@Query("select a.curricula from Application a group by a.offer")
	List<Curricula> curriculasGroupByOffer();

	//Curriculums por Compañia
	@Query("select a.curricula from Application a group by a.offer.company")
	List<Curricula> curriculasGroupByCompany();


}
