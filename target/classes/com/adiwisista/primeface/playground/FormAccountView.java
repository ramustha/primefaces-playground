package com.adiwisista.primeface.playground;

import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class FormAccountView implements Serializable {
    private Account account;

    @Inject
    private AccountService accountService;

    @PostConstruct
    public void init() {
        account = new Account();
    }

    public Long getAccountId() {
        return account.getAccountId();
    }

    public String getAccountName() {
        return account.getAccountName();
    }

    public boolean isEscrow() {
        return account.isEscrow();
    }

    public void setAccountId(Long accountId) {
        account.setAccountId(accountId);
    }

    public void setAccountName(String accountName) {
        account.setAccountName(accountName);
    }

    public void setEscrow(boolean escrow) {
        account.setEscrow(escrow);
    }

    public void create() {
        boolean added = accountService.addAccount(account);

        if (added) {
            FacesUtil.addMessage("Saved " + account.getAccountName(), FacesMessage.SEVERITY_INFO);
            reset();
        } else {
            FacesUtil.addMessage("Already exists", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void reset() {
        account.reset();
        PrimeFaces.current().resetInputs("form1:panel");
    }
}