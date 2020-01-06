package com.adiwisista.primeface.playground;

import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ChartService {
    private ChartSeries chartSeries;

    @PostConstruct
    public void init() {
        chartSeries = new ChartSeries();
        chartSeries.setLabel("Saldo");
    }

    public ChartSeries getChartSeries() {
        return chartSeries;
    }

    public void addSeriesData(Account account) {
        chartSeries.set(account.getAccountName() + "[" + account.getId() + "]", account.getSaldo());
    }
}
