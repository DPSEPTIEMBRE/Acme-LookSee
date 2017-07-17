package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ActivityReport;

@Repository
public interface ActivityReportRepository extends
		JpaRepository<ActivityReport, Integer> {

}
