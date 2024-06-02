package lv.venta.repo;

import lv.venta.model.Driver;
import lv.venta.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface ITeamRepo extends CrudRepository<Team, Integer> {

    Team findByDriversNumber(int number);
}
