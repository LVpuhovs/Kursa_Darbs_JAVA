package lv.venta.service;

import lv.venta.model.Driver;

import java.util.ArrayList;

public interface IDriverCRUDService {
    public abstract Driver createDriver(Driver driver) throws Exception;

    public abstract void updateDriverById(int id, Driver driver) throws Exception;

    public abstract void deleteDriverById(int id) throws Exception;

    public abstract ArrayList<Driver> getAllDrivers();

    public abstract Driver getDriverById(int id) throws Exception;

    public abstract Driver getDriverBySurname(String surname) throws Exception;
    public abstract ArrayList<Driver> getAvailableDrivers();

}
