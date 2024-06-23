package lv.venta.service.impl;

import lv.venta.model.Team;
import lv.venta.model.TeamStandings;
import lv.venta.repo.ITeamRepo;
import lv.venta.repo.ITeamStandingsRepo;
import lv.venta.service.ITeamStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TeamStandingsServiceImpl implements ITeamStandingsService {
    @Autowired
    private ITeamStandingsRepo teamStandingsRepo;
    @Autowired
    private ITeamRepo teamRepo;
    
    @Override
    public ArrayList<TeamStandings> getAllTeamStandings() {
        return (ArrayList<TeamStandings>) teamStandingsRepo.findAll();
    }

    @Override
    public TeamStandings getTeamStandingById(int id) throws Exception {
    	if(id < 0) throw new Exception("Wrong Input - Id should be positive!");
        return teamStandingsRepo.findById(id).get();
    }

    @Override
    public TeamStandings addTeamStanding(TeamStandings teamStandings) throws Exception {
        if (teamStandings == null) throw new Exception("Team standings is null");
        if (teamStandings.getPoints() < 0)
            throw new Exception("Points cannot be negative");
        
        return teamStandingsRepo.save(teamStandings);
    }

    @Override
    public void updateTeamStanding(int id, TeamStandings teamStandings) throws Exception {
        TeamStandings existingTeamStandings = getTeamStandingById(id);
        if (existingTeamStandings == null) throw new Exception("Team standing not found");

        existingTeamStandings.setTeam(teamStandings.getTeam());
        existingTeamStandings.setRace(teamStandings.getRace());
        existingTeamStandings.setPoints(teamStandings.getPoints());
        teamStandingsRepo.save(existingTeamStandings);
    }

    @Override
    public void deleteTeamStanding(int id) throws Exception {
        TeamStandings existingTeamStandings = getTeamStandingById(id);
        if (existingTeamStandings == null) throw new Exception("Team standing not found");

        teamStandingsRepo.delete(existingTeamStandings);
    }
    
    public void calculateAndUpdateAllTeamPoints() {
    	ArrayList<TeamStandings> teamStandList = getAllTeamStandings();
    	for(TeamStandings teamStand : teamStandList) {
    		teamStand.calculateTeamPoints();
    		teamStandingsRepo.save(teamStand);
    	}
    }
    
	@Override
	public int calculateTeamTotalPointsById(int id) {
		return teamStandingsRepo.sumPointsByTeamIdT(id);		
	}
	@Override
	public void updateTeamPositions() {
		List<Team> teams = (List<Team>) teamRepo.findAll();
		teams.sort((t1, t2) -> Integer.compare(t2.getTotalTeamPoints(), t1.getTotalTeamPoints()));
		
		for(int i = 0; i < teams.size(); i++)
			teams.get(i).setTeamTotalPosition(i + 1);
		teamRepo.saveAll(teams);
	}

	
}
