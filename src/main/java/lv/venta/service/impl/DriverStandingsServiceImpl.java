package lv.venta.service.impl;

import lv.venta.model.*;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IDriverStandingsRepo;
import lv.venta.repo.IRaceRepo;
import lv.venta.repo.IRaceResultRepo;
import lv.venta.repo.ITeamStandingsRepo;
import lv.venta.service.IDriverStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DriverStandingsServiceImpl implements IDriverStandingsService {

    @Autowired
    private IDriverStandingsRepo driverStandingsRepo;
    
    @Autowired
    private IDriverRepo driverRepo;

    @Autowired
    private IRaceRepo raceRepo;

    @Autowired
    private IRaceResultRepo raceResultRepo;

    @Autowired
    private ITeamStandingsRepo teamStandingsRepo;

    @Override
    public ArrayList<DriverStandings> getAllDriverStandings() {
        return (ArrayList<DriverStandings>) driverStandingsRepo.findAll();
    }
       

    @Override
    public ArrayList<Race> getAllRaces() {
        return (ArrayList<Race>) raceRepo.findAll();
    }

    @Override
    public DriverStandings getDriverStandingsById(int id) throws Exception {
    	if(id < 0) throw new Exception("Wrong Input - Id should be positive!");
        return driverStandingsRepo.findById(id).orElse(null);
    }
    
   @Override
    public void addRaceResult(Race race, RaceResult raceResult) {
        if (!raceRepo.existsById(race.getIdR()))
            raceRepo.save(race);
        if (!raceResultRepo.existsById(raceResult.getId())) 
            raceResultRepo.save(raceResult);

        Driver driver = raceResult.getDriver();
        DriverStandings driverStandings = driverStandingsRepo.findByDriverIdD(driver.getIdD());
        if (driverStandings == null) 
            driverStandings = new DriverStandings(driver, raceResult, race.getIdR());
        else {
            driverStandings.setRaceResult(raceResult);
            driverStandings.setNumberOfTheRace(race.getIdR());
        }
        driverStandingsRepo.save(driverStandings);
        
        try {
            int totalPoints = calculateDriverTotalPointsById(driver.getIdD());
            driver.setTotalPoints(totalPoints);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driverRepo.save(driver);

        Team team = driver.getTeam();
        if (team != null) {
            TeamStandings teamStandings = teamStandingsRepo.findByTeamIdT(team.getIdT());
            if (teamStandings != null) {
                teamStandings.setPoints(teamStandings.getPoints() + driverStandings.getPointsPerRace());
                teamStandingsRepo.save(teamStandings);
            }
        }
       updateDriverPositions();
    }

   @Override
   public void updateDriverStanding(int id, DriverStandings updatedDriverStandings) throws Exception {
   	DriverStandings existingDriverStandings = getDriverStandingsById(id);
   	if(existingDriverStandings != null) {
   		existingDriverStandings.getRaceResult().setPosition(updatedDriverStandings.getRaceResult().getPosition());
           driverStandingsRepo.save(existingDriverStandings);
   	} else 
           throw new IllegalArgumentException("Driver standings with ID " + id + " not found.");
   }

    @Override
    public void deleteDriverStanding(int id) throws Exception {
        DriverStandings existingDriverStandings = getDriverStandingsById(id);
        if (existingDriverStandings == null) throw new Exception("Driver standing not found");

        driverStandingsRepo.delete(existingDriverStandings);
    }

	@Override
	public int calculateDriverTotalPointsById(int id){
        return driverStandingsRepo.sumPointsPerRaceByDriverIdD(id);
	}

    @Override
    public int calculateDriverTotalWinsById(int id) {
        return driverStandingsRepo.sumWinsByDriverIdD(id);
    }

	@Override
	public List<DriverStandings> getDriverStandingsByRaceId(int raceId) {
		return driverStandingsRepo.findByRaceResultRaceIdR(raceId);
	}
	@Override
	public List<DriverStandings> getAllDriverStandingsWithRaceResults() {
        List<DriverStandings> standings = getAllDriverStandings();
        Map<Integer, DriverStandings> standingsMap = new LinkedHashMap<>();

        for (DriverStandings standing : standings) {
            int driverId = standing.getDriver().getIdD();
            if (!standingsMap.containsKey(driverId)) {
                standingsMap.put(driverId, standing);
            }
        }
        return new ArrayList<>(standingsMap.values());
	}

    @Override
    public void updateDriverPositions() {
        List<Driver> drivers = (List<Driver>) driverRepo.findAll();
        drivers.sort((d1, d2) -> Integer.compare(d2.getTotalPoints(), d1.getTotalPoints()));

        for(int i = 0; i < drivers.size(); i++)
            drivers.get(i).setDriverTotalPosition(i + 1);
        driverRepo.saveAll(drivers);
    }

    @Override
    public Race getRaceById(int id) {
        return raceRepo.findById(id).get();
    }
}