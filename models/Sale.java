package sinerji.models;

import java.time.YearMonth;

public class Sale {
    private YearMonth yearMonth;
    private double value;

    public Sale(YearMonth yearMonth, double value) {
        this.yearMonth = yearMonth;
        this.value = value;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
