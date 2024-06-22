package lv.venta.service;

import lv.venta.model.DriverStandings;
import lv.venta.model.Race;
import lv.venta.model.RaceResult;

import java.util.ArrayList;
import java.util.List;

public interface IDriverStandingsService {
    ArrayList<DriverStandings> getAllDriverStandings();
    ArrayList<Race> getAllRaces();
    DriverStandings getDriverStandingsById(int id) throws Exception;
    void updateDriverStanding(int id, DriverStandings driverStandings) throws Exception;
    void deleteDriverStanding(int id) throws Exception;
    
    int calculateDriverTotalPointsById(int id);
	void addRaceResult(Race race, RaceResult raceResult);
	
	List<DriverStandings> getDriverStandingsByRaceId(int raceId);
	List<DriverStandings> getAllDriverStandingsWithRaceResults();

    void updateDriverPositions();
    int calculateDriverTotalWinsById(int id);
}
