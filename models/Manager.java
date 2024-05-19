package sinerji.models;

import java.time.YearMonth;

public class Manager extends Employee {
    public Manager(String name, YearMonth yearMonthHire) {
        super(name, yearMonthHire, 20000, 3000);
    }

    @Override
    public double calculateBenefitByYearMonth(YearMonth yearMonth) {
        return 0;
    }
}
