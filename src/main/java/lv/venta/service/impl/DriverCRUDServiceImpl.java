package lv.venta.service.impl;

import lv.venta.model.Driver;
import lv.venta.model.Team;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.ITeamRepo;
import lv.venta.service.IDriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class DriverCRUDServiceImpl implements IDriverCRUDService {

    @Autowired
    IDriverRepo driverRepo;

    @Autowired
    ITeamRepo teamRepo;

    @Override
    public Driver createDriver(Driver driver) throws Exception {
        if (driver == null) throw new Exception("Driver is null");

        Driver existingDriver = driverRepo.findByNameAndSurnameAndNumber(driver.getName(), driver.getSurname(), driver.getNumber());
        if (existingDriver != null) {
            throw new Exception(driver.getName() + " " + driver.getSurname() + " is already registered");
        }
        Team team = teamRepo.findByDriversNumber(driver.getNumber());
        if(team != null) throw new Exception("Driver " + driver.getName() + " is already registered");

        Driver newDriver = new Driver(driver.getName(), driver.getSurname(), driver.getNumber());
        driverRepo.save(driver);
        return newDriver;
    }

    @Override
    public void updateDriverById(int id, Driver driver) throws Exception {

    }

    @Override
    public Driver deleteDriverById(int id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Driver> getAllDrivers(){
        return (ArrayList<Driver>) driverRepo.findAll();
    }

    @Override
    public Driver getDriverById(int id) throws Exception {
        return null;
    }

    @Override
    public Driver getDriverBySurname(String surname) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Driver> getTeamDrivers() throws Exception {
        return null;
    }
}
