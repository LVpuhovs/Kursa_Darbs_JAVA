package lv.venta.repo;

import lv.venta.model.Race;
import org.springframework.data.repository.CrudRepository;

public interface IRaceRepo extends CrudRepository<Race, Integer> {
}
