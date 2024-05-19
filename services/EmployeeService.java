package sinerji.services;

import sinerji.models.Employee;
import sinerji.models.Seller;

import java.time.YearMonth;
import java.util.List;

public class EmployeeService {

    public double getTotalAmount(List<Employee> employees, YearMonth yearMonth) {
        return employees.stream()
                .filter(employee -> employee.getTotalMonthsWorked(yearMonth) > 0)
                .mapToDouble(employee -> employee.calculateTotalSalaryUntil(yearMonth))
                .sum();
    }

    public double getTotalSalaryByYearMonth(List<Employee> employees, YearMonth yearMonth) {
        return employees.stream()
                .mapToDouble(employee -> employee.calculateSalaryByYearMonth(yearMonth))
                .sum();
    }

    public double getTotalBenefitByYearMonth(List<Employee> employees, YearMonth yearMonth) {
        return employees.stream()
                .mapToDouble(employee -> employee.calculateBenefitByYearMonth(yearMonth.plusMonths(1)))
                .sum();
    }

    public Employee getEmployeeWhoReceivedMoreByYearMonth(List<Employee> employees, YearMonth yearMonth) {
        return employees.stream()
                .max((emp1, emp2) -> emp1.compareSalaryWithBenefitOf(emp2, yearMonth))
                .orElse(null);
    }

    public Employee getEmployeeWhoReceivedMoreBenefitByYearMonth(List<Employee> employees, YearMonth yearMonth) {
        return employees.stream()
                .max((emp1, emp2) -> emp1.compareBenefitOf(emp2, yearMonth))
                .orElse(null);
    }

    public Employee getSellerWhoSoldMoreByYearMonth(List<Employee> employees, YearMonth yearMonth) {
        return employees.stream()
                .filter(Seller.class::isInstance)
                .map(Seller.class::cast)
                .filter(seller -> seller.hasSaleByYearMonth(yearMonth))
                .max((seller1, seller2) -> seller1.compareWhoSoldMoreOf(seller2, yearMonth))
                .orElse(null);
    }
}
