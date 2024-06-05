package lv.venta.repo;

import lv.venta.model.DriverStandings;
import org.springframework.data.repository.CrudRepository;

public interface IDriverStandingsRepo extends CrudRepository<DriverStandings, Integer> {
}
