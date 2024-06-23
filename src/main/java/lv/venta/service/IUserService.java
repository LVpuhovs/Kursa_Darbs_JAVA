package lv.venta.service;

import lv.venta.model.MyUser;

public interface IUserService {
    public abstract void save(MyUser user);
    MyUser findByUsername(String username);
}
