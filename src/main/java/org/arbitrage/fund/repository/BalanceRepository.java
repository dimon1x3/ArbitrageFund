package org.arbitrage.fund.repository;

/**
 * Created by dimo on 22.05.2018.
 */
import org.springframework.data.repository.CrudRepository;

import org.arbitrage.fund.model.Balance;

public interface BalanceRepository extends CrudRepository<Balance, Long> {

}