package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.domain.User;

public class UserServiceImpl implements UserService {

    @Override
    public boolean saveUser( User user ) {
        return true;
    }

    @Override
    public User getUserByDni( String dni ) {
        return null;
    }

}
