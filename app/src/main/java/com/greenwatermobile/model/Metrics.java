package com.greenwatermobile.model;

public class Metrics {

    private int daily;
    private int weekly;
    private int monthly;
    private int yearly;

    public Metrics(int daily, int weekly, int monthly, int yearly) {
        this.daily = daily;
        this.weekly = weekly;
        this.monthly = monthly;
        this.yearly = yearly;
    }

    public int getDaily() {
        return daily;
    }

    public void setDaily(int daily) {
        this.daily = daily;
    }

    public int getWeekly() {
        return weekly;
    }

    public void setWeekly(int weekly) {
        this.weekly = weekly;
    }

    public int getMonthly() {
        return monthly;
    }

    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }

    public int getYearly() {
        return yearly;
    }

    public void setYearly(int yearly) {
        this.yearly = yearly;
    }

    public double costPerLiter() {
        return 0.005f;
    }
}
