package org.arbitrage.fund.service;

/**
 * Created by dimo on 06.06.2018.
 */
import java.util.Arrays;
import java.util.HashSet;

import org.arbitrage.fund.model.Role;
import org.arbitrage.fund.model.User;
import org.arbitrage.fund.repository.RoleRepository;
import org.arbitrage.fund.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setUserpass (bCryptPasswordEncoder.encode(user.getUserpass()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        System.out.println("saved!");
    }

}