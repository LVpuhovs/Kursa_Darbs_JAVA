package lv.venta.service;

import lv.venta.model.TeamStandings;

import java.util.ArrayList;

public interface ITeamStandingsService {
    ArrayList<TeamStandings> getAllTeamStandings();
    TeamStandings getTeamStandingById(int id) throws Exception;
    TeamStandings addTeamStanding(TeamStandings teamStandings) throws Exception;
    void updateTeamStanding(int id, TeamStandings teamStandings) throws Exception;
    void deleteTeamStanding(int id) throws Exception;
    void calculateAndUpdateAllTeamPoints();
    
	int calculateTeamTotalPointsById(int id);
	void updateTeamPositions();
}
