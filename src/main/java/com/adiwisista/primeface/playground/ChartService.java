package com.adiwisista.primeface.playground;

import com.adiwisista.primeface.playground.repository.AccountRepository;
import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ChartService {
    private ChartSeries chartSeries;

    @Inject
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        chartSeries = new ChartSeries();
        chartSeries.setLabel("Saldo");

        for (Account account : accountRepository.findAll()) {
            addSeriesData(account);
        }
    }

    public ChartSeries getChartSeries() {
        return chartSeries;
    }

    public void addSeriesData(Account account) {
        chartSeries.set(account.getAccountName() + "[" + account.getId() + "]", account.getSaldo());
    }
}
