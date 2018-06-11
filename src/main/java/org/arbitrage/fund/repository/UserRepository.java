package org.arbitrage.fund.repository;

import org.arbitrage.fund.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dimo on 06.06.2018.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
