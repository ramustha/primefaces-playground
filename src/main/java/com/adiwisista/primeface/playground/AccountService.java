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

    public boolean transfer(Account selectedAccount, Long receivedAccountId, Long amount) {
        if (selectedAccount != null
                && !selectedAccount.getAccountId().equals(receivedAccountId)
                && isAccountExist(receivedAccountId)
                && amount != null
                && amount > 0) {

            Account senderAccount = null;
            Account receivedAccount = null;
            for (Account account : accounts) {
                if (account.getAccountId().equals(receivedAccountId)) {
                    receivedAccount = account;
                }

                if (account.getAccountId().equals(selectedAccount.getAccountId())) {
                    senderAccount = account;
                }
            }

            if (senderAccount != null) {
                Long saldoAwal = senderAccount.getSaldo();
                long saldoAkhir = saldoAwal - amount;

                if (!selectedAccount.isEscrow() && saldoAkhir < 0) {
                    return false;
                }

                senderAccount.setSaldo(saldoAkhir);

                accounts.set(accounts.indexOf(senderAccount), senderAccount);

                historyService.addHistory(new History(
                        senderAccount,
                        saldoAwal,
                        null,
                        amount,
                        saldoAkhir
                ));

                chartService.addSeriesData(senderAccount);
            }

            if (receivedAccount != null) {
                Long saldoAwal = receivedAccount.getSaldo();
                long saldoAkhir = saldoAwal + amount;

                receivedAccount.setSaldo(saldoAkhir);

                accounts.set(accounts.indexOf(receivedAccount), receivedAccount);

                historyService.addHistory(new History(
                        receivedAccount,
                        saldoAwal,
                        amount,
                        null,
                        saldoAkhir
                ));

                chartService.addSeriesData(receivedAccount);
            }
            return true;
        } else {
            return false;
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
