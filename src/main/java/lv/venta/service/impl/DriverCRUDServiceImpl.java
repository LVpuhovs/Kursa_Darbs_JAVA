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
    private ITeamRepo teamRepo;

    @Override
    public Driver createDriver(Driver driver) throws Exception {
        if (driver == null) throw new Exception("Driver is null");

        Driver existingDriver = driverRepo.findByNumber(driver.getNumber());
        if (existingDriver != null) throw new Exception(driver.getName() + " " + driver.getSurname() + " is already registered");
        return driverRepo.save(driver);
    }

    @Override
    public void updateDriverById(int id, Driver driver) throws Exception {
    	if (driver == null) throw new Exception("Driver is null");
    	
        Driver existingDriver = getDriverById(id);
        if (existingDriver == null) throw new Exception("Driver not found");
        
        if(driver.getNumber() != existingDriver.getNumber()) {		//when updating driver num, checks if a driver with that num already exists
        	Driver driverWithSameNum = driverRepo.findByNumber(driver.getNumber());
        	if(driverWithSameNum != null && driverWithSameNum.getNumber() == driver.getNumber()) 
        		throw new Exception("Driver with number " + driver.getNumber() + " is already registered");
        }
        
        existingDriver.setName(driver.getName());
        existingDriver.setSurname(driver.getSurname());
        existingDriver.setNumber(driver.getNumber());
        driverRepo.save(existingDriver);
    }

    @Override
    public void deleteDriverById(int id) throws Exception {    	
        Driver existingDriver = getDriverById(id);
        if (existingDriver == null) throw new Exception("Driver not found");
        
        Team team = existingDriver.getTeam();
        if(team != null) {
        	if(team.getDriver1() != null && team.getDriver1().equals(existingDriver)) team.setDriver1(null);
        	else if(team.getDriver2() != null && team.getDriver2().equals(existingDriver)) team.setDriver2(null);
        	teamRepo.save(team);
        }
        
        driverRepo.delete(existingDriver);
    }

    @Override
    public ArrayList<Driver> getAllDrivers() throws Exception{
    	if(driverRepo.count() == 0) throw new Exception("Driver list is empty");
        return (ArrayList<Driver>) driverRepo.findAll();
    }

    @Override
    public Driver getDriverById(int id) throws Exception {
    	if(id < 0) throw new Exception("Wrong Input - Id should be positive!");
        return driverRepo.findById(id).get();
    }

    @Override
    public Driver getDriverBySurname(String surname) throws Exception {
    	if (surname == null) throw new Exception("Surname is null");
        Driver existingDriver = driverRepo.findBySurname(surname);
        if (existingDriver == null) throw new Exception("Driver not found");

        return existingDriver;
    }

    @Override
    public ArrayList<Driver> getAvailableDrivers(){
        return driverRepo.findAvailableDrivers();
    }

}
