package lv.venta.repo;

import lv.venta.model.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IDriverRepo extends CrudRepository<Driver, Integer> {
    @Query("SELECT d FROM Driver d WHERE d.team IS NULL")
    ArrayList<Driver> findAvailableDrivers();

    Driver findByNumber(int number);

    Driver findBySurname(String surname);

	
}
