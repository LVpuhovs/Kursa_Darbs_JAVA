package lv.venta.repo;

import lv.venta.model.DriverStandings;
import lv.venta.model.RaceResult;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRaceResultRepo extends CrudRepository<RaceResult, Integer> {

	RaceResult findByDriverStandings(DriverStandings standing);

    List<RaceResult> findByDriverStandingsDriverIdD(int idD);
}
