package com.adiwisista.primeface.playground;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    private Long accountId;
    private String accountName;
    private boolean isEscrow;
    private long saldo;

    public Account(Long accountId, String accountName, boolean isEscrow, long saldo) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.isEscrow = isEscrow;
        this.saldo = saldo;
    }

    public Account() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public boolean isEscrow() {
        return isEscrow;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setEscrow(boolean escrow) {
        isEscrow = escrow;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public void reset() {
        accountId = null;
        accountName = null;
        isEscrow = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", isEscrow=" + isEscrow +
                ", saldo=" + saldo +
                '}';
    }
}
