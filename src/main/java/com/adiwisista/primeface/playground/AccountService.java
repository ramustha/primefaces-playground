package com.adiwisista.primeface.playground;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class AccountService {
    private List<Account> accounts;

    @Inject
    private HistoryService historyService;
    @Inject
    private ChartService chartService;

    @PostConstruct
    public void init() {
        accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean isAccountExist(Long receivedAccountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(receivedAccountId)) {
                return true;
            }
        }
        return false;
    }

    public void transfer(Long senderAccountId, Long receivedAccountId, Long amount) {
        Account senderAccount = null;
        Account receivedAccount = null;
        for (Account account : accounts) {
            if (account.getAccountId().equals(receivedAccountId)) {
                receivedAccount = account;
            }

            if (account.getAccountId().equals(senderAccountId)) {
                senderAccount = account;
            }
        }

        if (senderAccount != null) {
            Long saldo = senderAccount.getSaldo();
            senderAccount.setSaldo(saldo - amount);

            accounts.set(accounts.indexOf(senderAccount), senderAccount);

            historyService.addHistory(new History(
               senderAccount,
               saldo,
               amount,
               null,
               senderAccount.getSaldo()
            ));

            chartService.addSeriesData(senderAccount);
        }

        if (receivedAccount != null) {
            Long saldo = receivedAccount.getSaldo();
            receivedAccount.setSaldo(saldo + amount);

            accounts.set(accounts.indexOf(receivedAccount), receivedAccount);

            historyService.addHistory(new History(
                    receivedAccount,
                    saldo,
                    null,
                    amount,
                    receivedAccount.getSaldo()
            ));

            chartService.addSeriesData(receivedAccount);
        }
    }

    public boolean addAccount(Account account) {
        Account newAccount = new Account(
                account.getAccountId(),
                account.getAccountName(),
                account.isEscrow(),
                0
        );

        if (accounts.contains(newAccount)) {
            return false;
        }
        return accounts.add(newAccount);
    }
}
