package lv.venta.service.impl;

import lv.venta.model.DriverStandings;
import lv.venta.repo.IDriverStandingsRepo;
import lv.venta.service.IDriverStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class DriverStandingsServiceImpl implements IDriverStandingsService {

    @Autowired
    private IDriverStandingsRepo driverStandingsRepo;

    @Override
    public ArrayList<DriverStandings> getAllDriverStandings() {
        return (ArrayList<DriverStandings>) driverStandingsRepo.findAll();
    }

    @Override
    public DriverStandings getDriverStandingsById(int id) {
        return driverStandingsRepo.findById(id).get();
    }

    @Override
    public DriverStandings addDriverStanding(DriverStandings driverStandings) throws Exception {
        if (driverStandings == null) throw new Exception("Driver standings is null");

        return driverStandingsRepo.save(driverStandings);
    }

    @Override
    public void updateDriverStanding(int id, DriverStandings driverStandings) throws Exception {
        DriverStandings existingDriverStandings = getDriverStandingsById(id);
        if (existingDriverStandings == null) throw new Exception("Driver standing not found");

        existingDriverStandings.setDriver(driverStandings.getDriver());
        existingDriverStandings.setPoints(driverStandings.getPoints());
        driverStandingsRepo.save(existingDriverStandings);

    }

    @Override
    public void deleteDriverStanding(int id) throws Exception {
        DriverStandings existingDriverStandings = getDriverStandingsById(id);
        if (existingDriverStandings == null) throw new Exception("Driver standing not found");

        driverStandingsRepo.delete(existingDriverStandings);
    }
}
