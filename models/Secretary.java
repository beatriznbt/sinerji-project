package sinerji.models;

import java.time.YearMonth;

public class Secretary extends Employee {

    private static final double BENEFIT = 0.2;

    public Secretary(String name, YearMonth yearMonthHire) {
        super(name, yearMonthHire, 7000, 1000);
    }

    @Override
    public double calculateBenefitByYearMonth(YearMonth yearMonth) {
        return calculateSalaryByYearMonth(yearMonth) * BENEFIT;
    }
}
