package com.kat.dmc.controller;

import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named("dashboard")
@ViewScoped
public class DashboardController implements Serializable {
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setAnimate(true);
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);

        animatedModel2 = initBarModel();
        animatedModel2.setAnimate(true);
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Chi phí vật tư");
        boys.set("01/2018", 1200000);
        boys.set("02/2018", 1000000);
        boys.set("03/2018", 440000);
        boys.set("04/2018", 1500000);
        boys.set("05/2018", 250000);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Doanh thu bán hàng");
        girls.set("01/2018", 520000);
        girls.set("02/2018", 600000);
        girls.set("03/2018", 1100000);
        girls.set("04/2018", 1350000);
        girls.set("05/2018", 1200000);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Số lượng sản xuất");

        series1.set(1, 278);
        series1.set(2, 119);
        series1.set(3, 338);
        series1.set(4, 61);
        series1.set(5, 86);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Số lượng bán ra");

        series2.set(1, 68);
        series2.set(2, 391);
        series2.set(3, 221);
        series2.set(4, 78);
        series2.set(5, 94);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }
}
