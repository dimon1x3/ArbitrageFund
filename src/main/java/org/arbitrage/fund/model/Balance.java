package org.arbitrage.fund.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Balance {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private long exchange_id;
    private double balance_usd;

    public Balance(){};

    public void setExchange_id(long exchange_id){
        this.exchange_id = exchange_id;
    }
    public long getExchange_id() {
        return exchange_id;
    }

    public void setBalance_usd(double balance_usd){
        this.balance_usd = balance_usd;
    }
    public double getBalance() {
        return balance_usd;
    }
}