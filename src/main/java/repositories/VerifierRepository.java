package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Verifier;

@Repository
public interface VerifierRepository extends JpaRepository<Verifier, Integer> {
	
	@Query("select c from Verifier c where c.userAccount.username = ?1")
	Verifier selectByUsername(String username);
}
