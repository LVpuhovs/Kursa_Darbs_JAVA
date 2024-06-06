package lv.venta.service.impl;

import lv.venta.model.*;
import lv.venta.repo.IDriverStandingsRepo;
import lv.venta.repo.IRaceRepo;
import lv.venta.repo.IRaceResultRepo;
import lv.venta.repo.ITeamStandingsRepo;
import lv.venta.service.IDriverStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class DriverStandingsServiceImpl implements IDriverStandingsService {

    @Autowired
    private IDriverStandingsRepo driverStandingsRepo;

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
        return driverStandingsRepo.findById(id).orElse(null);
    }

    public void addRaceResult(Race race, ArrayList<RaceResult> raceResults) {
        if (!raceRepo.existsById(race.getIdR())) {
            raceRepo.save(race);
        }
        for (RaceResult result : raceResults) {
            raceResultRepo.save(result);

            DriverStandings driverStandings = driverStandingsRepo.findByDriverIdD(result.getDriver().getIdD());
            driverStandings.getRaceResults().add(result.getPosition());
            driverStandings.calculatePoints();
            driverStandingsRepo.save(driverStandings);

            Team team = result.getDriver().getTeam();
            TeamStandings teamStandings = teamStandingsRepo.findByTeamIdT(team.getIdT());
            teamStandings.setPoints(teamStandings.getPoints() + driverStandings.getPoints());
            teamStandingsRepo.save(teamStandings);
        }
    }

    @Override
    public void updateDriverStanding(int id, DriverStandings driverStandings) throws Exception {
        DriverStandings existingDriverStandings = driverStandingsRepo.findById(id).orElse(null);
        if (existingDriverStandings != null) {
            existingDriverStandings.setDriver(driverStandings.getDriver());
            existingDriverStandings.setPoints(driverStandings.getPoints());
            driverStandingsRepo.save(existingDriverStandings);
        } else {

            throw new Exception("Driver standings not found for id: " + id);
        }

    }

    @Override
    public void deleteDriverStanding(int id) throws Exception {
        DriverStandings existingDriverStandings = getDriverStandingsById(id);
        if (existingDriverStandings == null) throw new Exception("Driver standing not found");

        driverStandingsRepo.delete(existingDriverStandings);
    }
}
