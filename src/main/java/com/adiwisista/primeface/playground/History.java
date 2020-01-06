package com.adiwisista.primeface.playground;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class History implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Date dateTime;
    private Long accountId;
    private String accountName;
    private Long saldoAwal;
    private Long debet;
    private Long kredit;
    private Long saldoAkhir;

    public History(Account account, Long saldoAwal, Long debet, Long kredit, Long saldoAkhir) {
        this.dateTime = new Date();
        this.accountId = account.getId();
        this.accountName = account.getAccountName();
        this.saldoAwal = saldoAwal;
        this.debet = debet;
        this.kredit = kredit;
        this.saldoAkhir = saldoAkhir;
    }

    public History() {
    }

    public Long getId() {
        return id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public Long getSaldoAwal() {
        return saldoAwal;
    }

    public Long getDebet() {
        return debet;
    }

    public Long getKredit() {
        return kredit;
    }

    public Long getSaldoAkhir() {
        return saldoAkhir;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setSaldoAwal(Long saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    public void setDebet(Long debet) {
        this.debet = debet;
    }

    public void setKredit(Long kredit) {
        this.kredit = kredit;
    }

    public void setSaldoAkhir(Long saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
    }

    @Override
    public String toString() {
        return "History{" +
                "dateTime=" + dateTime +
                ", accountId=" + accountId +
                ", accountName=" + accountName +
                ", saldoAwal=" + saldoAwal +
                ", debet=" + debet +
                ", kredit=" + kredit +
                ", saldoAkhir=" + saldoAkhir +
                '}';
    }
}
