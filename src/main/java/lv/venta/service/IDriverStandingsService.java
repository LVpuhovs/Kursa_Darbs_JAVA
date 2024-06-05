package lv.venta.service;

import lv.venta.model.DriverStandings;

import java.util.ArrayList;

public interface IDriverStandingsService {
    ArrayList<DriverStandings> getAllDriverStandings();
    DriverStandings getDriverStandingsById(int id);
    DriverStandings addDriverStanding(DriverStandings driverStandings) throws Exception;
    void updateDriverStanding(int id, DriverStandings driverStandings) throws Exception;
    void deleteDriverStanding(int id) throws Exception;

}
