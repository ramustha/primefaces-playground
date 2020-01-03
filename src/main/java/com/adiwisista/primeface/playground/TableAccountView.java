package com.adiwisista.primeface.playground;

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
        if (selectedAccount != null
                && !selectedAccount.getAccountId().equals(accountId)
                && accountService.isAccountExist(accountId)) {

            Long senderId = selectedAccount.getAccountId();
            accountService.transfer(senderId, accountId, amount);

            FacesUtil.addMessage("Transfer from " + senderId + " To " + accountId + " " + amount, FacesMessage.SEVERITY_INFO);
        } else {
            FacesUtil.addMessage("Failed Transfer to " + accountId, FacesMessage.SEVERITY_ERROR);
        }
    }
}
