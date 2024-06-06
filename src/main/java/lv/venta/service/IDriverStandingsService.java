package lv.venta.service;

import lv.venta.model.DriverStandings;
import lv.venta.model.Race;

import java.util.ArrayList;

public interface IDriverStandingsService {
    ArrayList<DriverStandings> getAllDriverStandings();
    ArrayList<Race> getAllRaces();
    DriverStandings getDriverStandingsById(int id) throws Exception;
    void updateDriverStanding(int id, DriverStandings driverStandings) throws Exception;
    void deleteDriverStanding(int id) throws Exception;

}
