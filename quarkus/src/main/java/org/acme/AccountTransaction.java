package org.acme;

import java.math.BigDecimal;

public class AccountTransaction {

    public String transactionId;
    public String type;
    public String creditDebitType;
    public BigDecimal amount;

    public AccountTransaction(String transactionId, String type, String creditDebitType, BigDecimal amount) {
        this.transactionId = transactionId;
        this.type = type;
        this.creditDebitType = creditDebitType;
        this.amount = amount;
    }

}
