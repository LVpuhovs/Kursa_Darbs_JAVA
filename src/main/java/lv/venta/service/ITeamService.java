package lv.venta.service;

import lv.venta.model.Team;

import java.util.ArrayList;

public interface ITeamService {
    public abstract ArrayList<Team> getAllTeams() throws Exception;
}
