package lv.venta.service;

import lv.venta.model.Team;

import java.util.ArrayList;

public interface ITeamService {
    public abstract ArrayList<Team> getAllTeams();
    public abstract Team getTeamById(int id) throws Exception;
    public abstract Team addTeam(Team team) throws Exception;
    public abstract Team updateTeam(Team team) throws Exception;
    public abstract void deleteTeam(int id) throws Exception;

}
