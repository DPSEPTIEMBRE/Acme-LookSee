package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Verifier;

@Repository
public interface VerifierRepository extends JpaRepository<Verifier, Integer> {
	
}
