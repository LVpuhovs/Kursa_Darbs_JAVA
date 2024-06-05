package lv.venta.service.impl;

import lv.venta.model.Driver;
import lv.venta.model.Team;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.ITeamRepo;
import lv.venta.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class TeamServiceImpl implements ITeamService {
    
    @Autowired
    private ITeamRepo teamRepo;
    
    @Autowired
    private IDriverRepo driverRepo;
    
    @Override
    public ArrayList<Team> getAllTeams() {
        return (ArrayList<Team>) teamRepo.findAll();
    }

    @Override
    public Team getTeamById(int id) throws Exception {
        return teamRepo.findById(id).get();
    }

    @Override
    public Team addTeam(Team team) throws Exception {
        if(team == null) throw new Exception("team is null");
        Team existingTeam = teamRepo.findByTeamName(team.getTeamName());
        if(existingTeam != null) throw new Exception("team already exists");

        if (team.getDriver1() != null && team.getDriver1().getIdD() == 0) {
            driverRepo.save(team.getDriver1());
        }
        if (team.getDriver2() != null && team.getDriver2().getIdD() == 0) {
            driverRepo.save(team.getDriver2());
        }
        teamRepo.save(team);

        if (team.getDriver1() != null) {
            team.getDriver1().setTeam(team);
            driverRepo.save(team.getDriver1());
        }
        if (team.getDriver2() != null) {
            team.getDriver2().setTeam(team);
            driverRepo.save(team.getDriver2());
        }

        return team;
    }

    @Override
    public void updateTeam(int id, Team team) throws Exception {
        Team existingTeam = getTeamById(id);
        if (existingTeam == null) throw new Exception("team not found");

        Driver replacedDriver1 = existingTeam.getDriver1();
        Driver replacedDriver2 = existingTeam.getDriver2();

        Driver newDriver1 = team.getDriver1();
        Driver newDriver2 = team.getDriver2();


        existingTeam.setDriver1(null);
        existingTeam.setDriver2(null);

        existingTeam.setDriver1(newDriver1);
        existingTeam.setDriver2(newDriver2);

        existingTeam.setTeamName(team.getTeamName());

        teamRepo.save(existingTeam);

        if (newDriver1 != null && (replacedDriver1 == null || !newDriver1.equals(replacedDriver1))) {
            newDriver1.setTeam(existingTeam);
            driverRepo.save(newDriver1);
        }
        if (newDriver2 != null && (replacedDriver2 == null || !newDriver2.equals(replacedDriver2))) {
            newDriver2.setTeam(existingTeam);
            driverRepo.save(newDriver2);
        }
        if (replacedDriver1 != null && !replacedDriver1.equals(newDriver1)) {
            replacedDriver1.setTeam(null);
            driverRepo.save(replacedDriver1);
        }
        if (replacedDriver2 != null && !replacedDriver2.equals(newDriver2)) {
            replacedDriver2.setTeam(null);
            driverRepo.save(replacedDriver2);
        }

    }

    @Override
    public void deleteTeam(int id) throws Exception {
        Team existingTeam = getTeamById(id);
        if(existingTeam == null) throw new Exception("team is null");

        Driver driver1 = existingTeam.getDriver1();
        Driver driver2 = existingTeam.getDriver2();

        if (driver1 != null) {
            driver1.setTeam(null);
            driverRepo.save(driver1);
        }
        if (driver2 != null) {
            driver2.setTeam(null);
            driverRepo.save(driver2);
        }
        existingTeam.setDriver1(null);
        existingTeam.setDriver2(null);
        teamRepo.save(existingTeam);
        teamRepo.delete(existingTeam);
    }
}
