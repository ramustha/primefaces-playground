package com.adiwisista.primeface.playground;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Account implements Serializable {
    @Id
    private Long id;
    private String accountName;
    private boolean isEscrow;
    private long saldo;

    public Account(Long id, String accountName, boolean isEscrow, long saldo) {
        this.id = id;
        this.accountName = accountName;
        this.isEscrow = isEscrow;
        this.saldo = saldo;
    }

    public Account() {
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
        id = null;
        accountName = null;
        isEscrow = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", accountName='" + accountName + '\'' +
                ", isEscrow=" + isEscrow +
                ", saldo=" + saldo +
                '}';
    }
}
