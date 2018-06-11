package org.arbitrage.fund.repository;

import org.arbitrage.fund.model.User;
import org.arbitrage.fund.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dimo on 06.06.2018.
 */
public interface UserProfileRepository extends CrudRepository<UserProfile, Long>{
   // UserProfile findByUser(User user);
}
