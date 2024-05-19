package sinerji.models;

import java.time.YearMonth;
import java.util.stream.Stream;

public abstract class Employee {

    protected String name;
    protected YearMonth yearMonthHire;
    protected double salary;
    protected double bonusPerYear;

    public Employee(String name, YearMonth yearMonthHire, double salary, double bonusPerYear) {
        this.name = name;
        this.yearMonthHire = yearMonthHire;
        this.salary = salary;
        this.bonusPerYear = bonusPerYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public YearMonth getYearMonthHire() {
        return yearMonthHire;
    }

    public void setYearMonthHire(YearMonth yearMonthHire) {
        this.yearMonthHire = yearMonthHire;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonusPerYear() {
        return bonusPerYear;
    }

    public void setBonusPerYear(double bonusPerYear) {
        this.bonusPerYear = bonusPerYear;
    }

    public int getTotalMonthsWorked(YearMonth yearMonth) {
        int yearMonthDiff = 12 * (yearMonth.getYear() - yearMonthHire.getYear());
        int monthDiff = yearMonth.getMonthValue() - yearMonthHire.getMonthValue();
        return yearMonthDiff + monthDiff;
    }

    public double calculateSalaryByYearMonth(YearMonth yearMonth) {
        int totalMonthsWorked = getTotalMonthsWorked(yearMonth);
        if (totalMonthsWorked < 0) {
            return 0;
        }
        double bonus = totalMonthsWorked % 12 == 0 ? bonusPerYear : 0;
        return salary + bonus;
    }

    public abstract double calculateBenefitByYearMonth(YearMonth yearMonth);

    public double calculateTotalSalaryUntil(YearMonth yearMonth) {
        int totalMonthsWorked = getTotalMonthsWorked(yearMonth);
        if (totalMonthsWorked <= 0) {
            return 0;
        }
        double sum = Stream.iterate(0, i -> i + 1).limit(totalMonthsWorked)
                .mapToDouble(i -> calculateSalaryWithBenefitByYearMonth(yearMonth.minusMonths(i)))
                .sum();
        System.out.println(name + ": " + sum);
        return sum;
    }

    public double calculateSalaryWithBenefitByYearMonth(YearMonth yearMonth) {
        return calculateSalaryByYearMonth(yearMonth) + calculateBenefitByYearMonth(yearMonth);
    }

    public int compareSalaryWithBenefitOf(Employee other, YearMonth yearMonth) {
        return Double.compare(calculateSalaryWithBenefitByYearMonth(yearMonth),
                other.calculateSalaryWithBenefitByYearMonth(yearMonth));
    }

    public int compareBenefitOf(Employee other, YearMonth yearMonth) {
        return Double.compare(calculateBenefitByYearMonth(yearMonth),
                other.calculateBenefitByYearMonth(yearMonth));
    }
}
