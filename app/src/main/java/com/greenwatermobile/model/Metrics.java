package com.greenwatermobile.model;

public class Metrics {

    private static final double FACTOR = 10;
    private static final double COST_PER_LITER = 0.05;

    private double daily;
    private double weekly;
    private double monthly;
    private double yearly;

    public Metrics(double daily, double weekly, double monthly, double yearly) {
        this.daily = daily;
        this.weekly = weekly;
        this.monthly = monthly;
        this.yearly = yearly;
    }

    public double getDaily() {
        return daily / FACTOR;
    }

    public double getWeekly() {
        return weekly / FACTOR;
    }

    public double getMonthly() {
        return monthly / FACTOR;
    }

    public double getYearly() {
        return yearly / FACTOR;
    }

    public double getDayCost() {
        return getDaily() * COST_PER_LITER;
    }

    public double getWeekCost() {
        return getWeekly() * COST_PER_LITER;
    }

    public double getMonthCost() {
        return getMonthly() * COST_PER_LITER;
    }

    public double getYearCost() {
        return getYearly() * COST_PER_LITER;
    }
}
