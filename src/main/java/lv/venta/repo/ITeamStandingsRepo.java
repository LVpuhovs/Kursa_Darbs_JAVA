package lv.venta.repo;

import lv.venta.model.TeamStandings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ITeamStandingsRepo extends CrudRepository<TeamStandings, Integer> {
    TeamStandings findByTeamIdT(long idT);

    @Query("SELECT SUM(ts.points) FROM TeamStandings ts WHERE ts.team.idT = :idT")
    int sumPointsByTeamIdT(int idT);
}
