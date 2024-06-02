package lv.venta.repo;

import lv.venta.model.Driver;
import org.springframework.data.repository.CrudRepository;

public interface IDriverRepo extends CrudRepository<Driver, Integer> {

    Driver findByNameAndSurnameAndNumber(String name, String surname, int number);
}
