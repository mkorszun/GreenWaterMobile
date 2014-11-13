package com.greenwatermobile.storage;

public class MetricsStorage {

    public int saveUsage(int usage, MetricsMode mode) {

        switch (mode) {
            case DAY:
                return usage;
            case WEEK:
                return usage + 300;
            case MONTH:
                return usage + 3000;
            case YEAR:
                return usage + 10000;
            default:
                return 0;
        }
    }
}
