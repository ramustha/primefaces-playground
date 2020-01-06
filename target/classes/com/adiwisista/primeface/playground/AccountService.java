package com.adiwisista.primeface.playground;

import com.adiwisista.primeface.playground.repository.AccountRepository;

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
    @Inject
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        accounts = new ArrayList<>();

        accounts.addAll(accountRepository.findAll());
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean isAccountExist(Long receivedAccountId) {
        for (Account account : accounts) {
            if (account.getId().equals(receivedAccountId)) {
                return true;
            }
        }
        return false;
    }

    public boolean transfer(Account selectedAccount, Long receivedAccountId, Long amount) {
        if (selectedAccount != null
                && !selectedAccount.getId().equals(receivedAccountId)
                && isAccountExist(receivedAccountId)
                && amount != null
                && amount > 0) {

            Account senderAccount = null;
            Account receivedAccount = null;
            for (Account account : accounts) {
                if (account.getId().equals(receivedAccountId)) {
                    receivedAccount = account;
                }

                if (account.getId().equals(selectedAccount.getId())) {
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
                accountRepository.save(senderAccount);

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
                accountRepository.save(receivedAccount);

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
                account.getId(),
                account.getAccountName(),
                account.isEscrow(),
                0
        );

        if (accounts.contains(newAccount)) {
            return false;
        }

        accountRepository.save(account);
        return accounts.add(newAccount);
    }
}
