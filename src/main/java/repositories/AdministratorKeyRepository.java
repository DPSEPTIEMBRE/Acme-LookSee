package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AdministratorKey;

@Repository
public interface AdministratorKeyRepository extends JpaRepository<AdministratorKey, Integer> {
	
	@Query("select c from AdministratorKey c where c.keyName = ?1")
	public AdministratorKey selectByKy(String key);
}
