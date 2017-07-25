package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	//Los candidatos ordenados por número de curriculums
	@Query("select c from Candidate c order by c.curriculas.size DESC")
	List<Candidate> orderByNumCurriculas();

	//Los candidatos que han registrado mas curriculums
	@Query("select c from Candidate c where c.curriculas.size=(select max(c.curriculas.size) from Candidate c)")
	List<Candidate> CandidateWithMoreCurriculas();
	
	//Encuentra un candidato por su cuenta de usuario
	@Query("select c from Candidate c where c.userAccount.id = ?1") 
	Candidate findByUserAccount(int id);




}
