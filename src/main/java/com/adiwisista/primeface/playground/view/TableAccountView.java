package com.adiwisista.primeface.playground.view;

import com.adiwisista.primeface.playground.Account;
import com.adiwisista.primeface.playground.AccountService;
import com.adiwisista.primeface.playground.FacesUtil;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TableAccountView implements Serializable {

    @Inject
    private AccountService accountService;
    private Long accountId;
    private Long amount;
    private Account selectedAccount;

    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void transfer() {
        boolean success = accountService.transfer(selectedAccount, accountId, amount);
        if (success) {
            FacesUtil.addMessage("Transfer from " + selectedAccount.getId() + " To " + accountId + " " + amount, FacesMessage.SEVERITY_INFO);
        } else {
            FacesUtil.addMessage("Failed Transfer to " + accountId, FacesMessage.SEVERITY_ERROR);
        }
    }
}
