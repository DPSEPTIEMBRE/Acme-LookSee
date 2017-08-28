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
	@Query("select distinct c from Curricula c, Offer u where c.copy = false group by u")
	List<Curricula> curriculasGroupByCompany();
	
	@Query("select u from Candidate c join c.curriculas u where u.copy = false and c.userAccount.username = ?1")
	List<Curricula> curriculasOfSelf(String username);
	
	@Query("select u from Candidate c join c.curriculas u where u.copy = false and c.id = ?1")
	List<Curricula> curriculasOfSelf(Integer id);
	
	@Query("select u from Candidate c join c.curriculas u where u.copy = false and c.userAccount.username = ?1")
	List<Curricula> selectCurriculasNotCopy(String username);

	@Query("select c from Curricula c where c.copy = false")
	List<Curricula> findAllNotCopy();

}
