package lv.venta.service.impl;

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
        return null;
    }

    @Override
    public Team addTeam(Team team) throws Exception {
        if(team == null) throw new Exception("team is null");
        Team existingTeam = teamRepo.findByTeamName(team.getTeamName());
        if(existingTeam != null) throw new Exception("team already exists");
        Team newTeam = new Team(team.getTeamName(), team.getDriver1(), team.getDriver2());
        return teamRepo.save(newTeam);
    }

    @Override
    public Team updateTeam(Team team) throws Exception {
        return null;
    }

    @Override
    public void deleteTeam(int id) throws Exception {

    }
}
