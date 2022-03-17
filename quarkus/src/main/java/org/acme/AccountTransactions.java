package org.acme;

import java.util.List;

public class AccountTransactions {

    public List<AccountTransaction> listAccountTransactions;

    public AccountTransactions() {        
    }
    
    public AccountTransactions(List<AccountTransaction> listAccountTransactions) {
        this.listAccountTransactions = listAccountTransactions;
    }

}
