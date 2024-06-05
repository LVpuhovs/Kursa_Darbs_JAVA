package lv.venta.service.impl;

import lv.venta.model.TeamStandings;
import lv.venta.repo.ITeamStandingsRepo;
import lv.venta.service.ITeamStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class TeamStandingsServiceImpl implements ITeamStandingsService {
    @Autowired
    private ITeamStandingsRepo teamStandingsRepo;

    @Override
    public ArrayList<TeamStandings> getAllTeamStandings() {
        return (ArrayList<TeamStandings>) teamStandingsRepo.findAll();
    }

    @Override
    public TeamStandings getTeamStandingById(int id) throws Exception {
        return teamStandingsRepo.findById(id).get();
    }

    @Override
    public TeamStandings addTeamStanding(TeamStandings teamStandings) throws Exception {
        if (teamStandings == null) throw new Exception("Team standings is null");

        if (teamStandings.getPoints() < 0) {
            throw new Exception("Points cannot be negative");
        }
        return teamStandingsRepo.save(teamStandings);
    }

    @Override
    public void updateTeamStanding(int id, TeamStandings teamStandings) throws Exception {
        TeamStandings existingTeamStandings = getTeamStandingById(id);
        if (existingTeamStandings == null) throw new Exception("Team standing not found");

        existingTeamStandings.setTeam(teamStandings.getTeam());
        existingTeamStandings.setPoints(teamStandings.getPoints());
        teamStandingsRepo.save(existingTeamStandings);
    }

    @Override
    public void deleteTeamStanding(int id) throws Exception {
        TeamStandings existingTeamStandings = getTeamStandingById(id);
        if (existingTeamStandings == null) throw new Exception("Team standing not found");

        teamStandingsRepo.delete(existingTeamStandings);
    }
}
