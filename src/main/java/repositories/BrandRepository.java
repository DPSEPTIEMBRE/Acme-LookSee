package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
