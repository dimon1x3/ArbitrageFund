package org.arbitrage.fund.service;

import org.arbitrage.fund.model.User;

/**
 * Created by dimo on 06.06.2018.
 */

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}