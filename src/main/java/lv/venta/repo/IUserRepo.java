package lv.venta.repo;

import lv.venta.model.MyUser;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepo extends CrudRepository<MyUser, Integer> {
    MyUser findByUsername(String username);
}
