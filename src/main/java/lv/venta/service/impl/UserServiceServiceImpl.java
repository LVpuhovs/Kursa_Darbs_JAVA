package lv.venta.service.impl;

import lv.venta.model.MyUser;
import lv.venta.repo.IUserRepo;
import lv.venta.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceServiceImpl implements IUserService {
    @Autowired
    private IUserRepo userRepo;

    private PasswordEncoder password;

    public void save(MyUser user) {
        user.setPassword(password.encode(user.getPassword()));
        userRepo.save(user);
    }
}
