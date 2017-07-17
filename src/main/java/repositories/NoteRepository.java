package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("select avg(v.notes.size) from Verifier v")
	public Double avgNotesByVerifier();


	//La media de notas, agrupadas por estado. PENDING
	@Query("select count(n)/(select count(no)*1.0 from Note no) from Note n where n.status = 'PENDING'")
	public Double avgNotesByVerifierGroupByStatusPending();

	//La media de notas, agrupadas por estado. CANCELLED
	@Query("select count(n)/(select count(no)*1.0 from Note no) from Note n where n.status = 'CANCELLED'")
	public Double avgNotesByVerifierGroupByStatusCancelled();

	//La media de notas, agrupadas por estado. CORRECTED
	@Query("select count(n)/(select count(no)*1.0 from Note no) from Note n where n.status = 'CORRECTED'")
	public Double avgNotesByVerifierGroupByStatusCorrected();

	//La media de notas, agrupadas por estado. REJECTED
	@Query("select count(n)/(select count(no)*1.0 from Note no) from Note n where n.status = 'REJECTED'")
	public Double avgNotesByVerifierGroupByStatusRejected();



}
