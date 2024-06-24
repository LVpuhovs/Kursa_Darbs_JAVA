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

    @Autowired
    private PasswordEncoder password;

    public void save(MyUser user) {
        String plaintextPassword = user.getPassword();

        if (plaintextPassword != null && (plaintextPassword.length() < 3 || plaintextPassword.length() > 20)) 
            throw new IllegalArgumentException("Password must be between 3 and 20 characters long");
        
        String encodedPassword = password.encode(plaintextPassword);
        user.setPassword(encodedPassword);
        userRepo.save(user);
    }

    @Override
    public MyUser findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
