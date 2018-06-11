package org.arbitrage.fund.repository;

import org.arbitrage.fund.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dimo on 06.06.2018.
 */
public interface RoleRepository extends CrudRepository<Role, Long>{
   // Role findByRole(String role);
}
