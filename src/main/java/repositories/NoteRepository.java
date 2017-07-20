package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;
import domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	//Notas de un verificador agrupadas por estado
	@Query("select n from Verifier v join v.notes n where v.id=?1 group by n.status")
	List<Curricula> notesGroupByState(int verifier_id);
	
	//La media de notas por verificador
	@Query("select avg(v.notes.size) from Verifier v")
	Double avgNotesByVerifier();

	//La media de notas, agrupadas por estado
	@Query("select (select count(q) from Note q where q.status = 'PENDING') * 1. / count(n), (select count(q) from Note q where q.status = 'CANCELLED') * 1. / count(n), (select count(q) from Note q where q.status = 'CORRECTED') * 1. / count(n), (select count(q) from Note q where q.status = 'REJECTED') * 1. / count(n) from Note n")
	Double avgNotesByVerifierGroupByStatus();
	
	//notes of verifier group by candidates
	@Query("select ca from Candidate ca, Verifier v join ca.curriculas cu join cu.notes n join v.notes vn where v.id = ?1 and vn.id = n.id group by ca")
	Number[] notesOfVerifierGroupByCandidates(int verifier_id);
}
