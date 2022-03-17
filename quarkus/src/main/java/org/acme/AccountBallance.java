package org.acme;

import java.math.BigDecimal;

public class AccountBallance {

    public BigDecimal availableAmount;
    public BigDecimal blockedAmount;

    public AccountBallance(BigDecimal availableAmount, BigDecimal blockedAmount) {
        this.availableAmount = availableAmount;
        this.blockedAmount = blockedAmount;
    }

}
