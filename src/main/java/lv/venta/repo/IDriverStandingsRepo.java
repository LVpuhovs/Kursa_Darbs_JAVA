package lv.venta.repo;

import lv.venta.model.DriverStandings;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IDriverStandingsRepo extends CrudRepository<DriverStandings, Integer> {

    DriverStandings findByDriverIdD(int idD);

    @Query("SELECT SUM(ds.pointsPerRace) FROM DriverStandings ds WHERE ds.driver.idD = :idD")
	int sumPointsPerRaceByDriverIdD(int idD);

	List<DriverStandings> findByRaceResultRaceIdR(int raceId);

	@Query("SELECT SUM(ds.wins) FROM DriverStandings ds WHERE ds.driver.idD = :idD")
	int sumWinsByDriverIdD(int idD);
}
