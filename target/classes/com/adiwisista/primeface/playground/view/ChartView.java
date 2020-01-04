package com.adiwisista.primeface.playground.view;

import com.adiwisista.primeface.playground.ChartService;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ChartView implements Serializable {
    private BarChartModel barChartModel;

    @Inject
    private ChartService chartService;

    @PostConstruct
    public void init() {
        barChartModel = new BarChartModel();

        ChartSeries chartSeries = chartService.getChartSeries();
        barChartModel.addSeries(chartSeries);

        Axis xAxis = barChartModel.getAxis(AxisType.X);
        xAxis.setLabel("Saldo");
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }
}