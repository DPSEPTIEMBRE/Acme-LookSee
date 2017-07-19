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

	//La media de notas, agrupadas por estado. PENDING
	@Query("select count(n)/(select count(no)*1.0 from Note no) from Note n where n.status = 'PENDING'")
	Double avgNotesByVerifierGroupByStatusPending();

	//La media de notas, agrupadas por estado. CANCELLED
	@Query("select count(n)/(select count(no)*1.0 from Note no) from Note n where n.status = 'CANCELLED'")
	Double avgNotesByVerifierGroupByStatusCancelled();

	//La media de notas, agrupadas por estado. CORRECTED
	@Query("select count(n)/(select count(no)*1.0 from Note no) from Note n where n.status = 'CORRECTED'")
	Double avgNotesByVerifierGroupByStatusCorrected();

	//La media de notas, agrupadas por estado. REJECTED
	@Query("select count(n)/(select count(no)*1.0 from Note no) from Note n where n.status = 'REJECTED'")
	Double avgNotesByVerifierGroupByStatusRejected();



}
