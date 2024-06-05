package lv.venta.repo;

import lv.venta.model.TeamStandings;
import org.springframework.data.repository.CrudRepository;

public interface ITeamStandingsRepo extends CrudRepository<TeamStandings, Integer> {
}
