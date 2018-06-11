package org.arbitrage.fund.controller;

import org.arbitrage.fund.model.Balance;
import org.arbitrage.fund.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfitStatsController {
    @Autowired
    private BalanceRepository balanceRepository;
    @RequestMapping("/uploadbalance")
    public Balance saveBalance(@RequestParam(value = "exchange_id", defaultValue = "-1") long exchange_id,
                           @RequestParam(value = "balance", defaultValue = "-1") double balance) {
        Balance bal = new Balance();
        bal.setExchange_id(exchange_id);
        bal.setBalance_usd(balance);
        balanceRepository.save(bal);
        return bal;
    }
}
