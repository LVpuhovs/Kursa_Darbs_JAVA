package lv.venta.repo;

import lv.venta.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface ITeamRepo extends CrudRepository<Team, Integer> {

    Team findByTeamName(String teamName);

    Team findByDriver1NumberAndDriver2Number(int driver1Number, int driver2Number);

}
