package com.adiwisista.primeface.playground;

import java.io.Serializable;
import java.util.Date;

public class History implements Serializable {
    private Date dateTime;
    private Account account;
    private Long saldoAwal;
    private Long debet;
    private Long kredit;
    private Long saldoAkhir;

    public History(Account account, Long saldoAwal, Long debet, Long kredit, Long saldoAkhir) {
        this.dateTime = new Date();
        this.account = account;
        this.saldoAwal = saldoAwal;
        this.debet = debet;
        this.kredit = kredit;
        this.saldoAkhir = saldoAkhir;
    }

    public History() {
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Account getAccount() {
        return account;
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

    @Override
    public String toString() {
        return "History{" +
                "dateTime=" + dateTime +
                ", account=" + account +
                ", saldoAwal=" + saldoAwal +
                ", debet=" + debet +
                ", kredit=" + kredit +
                ", saldoAkhir=" + saldoAkhir +
                '}';
    }
}
