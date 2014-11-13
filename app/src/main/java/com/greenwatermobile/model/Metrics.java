package com.greenwatermobile.model;

public class Metrics {

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
        return daily;
    }

    public void setDaily(int daily) {
        this.daily = daily;
    }

    public double getWeekly() {
        return weekly;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }

    public double getMonthly() {
        return monthly;
    }

    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }

    public double getYearly() {
        return yearly;
    }

    public void setYearly(int yearly) {
        this.yearly = yearly;
    }

    public double costPerLiter() {
        return 0.005f;
    }
}
