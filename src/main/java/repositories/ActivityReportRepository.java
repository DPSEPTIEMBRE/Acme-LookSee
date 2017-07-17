package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ActivityReport;
import domain.Actor;

@Repository
public interface ActivityReportRepository extends
		JpaRepository<ActivityReport, Integer> {
	
	
	//El mínimo, máximo y media de registros de actividad por actor.
	@Query("select min(a.activities.size),max(a.activities.size),avg(a.activities.size) from Actor a")
	public Object[] MinMaxAvgActiviesByActor();
	
	//Los actores que tienen ±10% de la media de registros de actividad por actor.
	@Query("select a from Actor a where a.activities.size between 0.9 * (select avg(a.activities.size) from Actor a) and 1.10 * (select avg(a.activities.size) from Actor a)")
	public List<Actor> actorsBetweenTenPercentActivities();
	

}
