package sinerji;

import sinerji.models.*;
import sinerji.services.EmployeeService;

import java.text.NumberFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        callAndPrintMethods(getEmployees(), YearMonth.of(2022, 3));
    }

    private static void callAndPrintMethods(List<Employee> employees, YearMonth yearMonth) {
        EmployeeService service = new EmployeeService();

        double totalAmount = service.getTotalAmount(employees, yearMonth);
        System.out.printf("Salário total até o Mês/Ano %s: %s%n", yearMonth,
                NumberFormat.getCurrencyInstance().format(totalAmount));

        double totalSalaryByYearMonth = service.getTotalSalaryByYearMonth(employees, yearMonth);
        System.out.printf("Salário total para o Mês/Ano %s: %s%n", yearMonth,
                NumberFormat.getCurrencyInstance().format(totalSalaryByYearMonth));

        double totalBenefitByYearMonth = service.getTotalBenefitByYearMonth(employees, yearMonth);
        System.out.printf("Benefício total para o Mês/Ano %s: %s%n", yearMonth,
                NumberFormat.getCurrencyInstance().format(totalBenefitByYearMonth));

        Employee employeeWhoReceivedMore = service.getEmployeeWhoReceivedMoreByYearMonth(employees, yearMonth);
        System.out.printf("Empregado que recebeu mais para o Mês/Ano %s: %s%n", yearMonth,
                getEmployeeNameForLog(employeeWhoReceivedMore));

        Employee employeeWhoReceivedMoreBenefitByYearMonth = service.getEmployeeWhoReceivedMoreBenefitByYearMonth(employees, yearMonth);
        System.out.printf("Empregado que recebeu mais benefício para o Mês/Ano %s: %s%n", yearMonth,
                getEmployeeNameForLog(employeeWhoReceivedMoreBenefitByYearMonth));

        Employee sellerWhoSoldMoreByYearMonth = service.getSellerWhoSoldMoreByYearMonth(employees, yearMonth);
        System.out.printf("Vendedor que vendeu para o Mês/Ano %s: %s", yearMonth,
                getEmployeeNameForLog(sellerWhoSoldMoreByYearMonth));
    }

    private static String getEmployeeNameForLog(Employee employee) {
        if (employee == null) {
            return "Nenhum";
        }
        return employee.getName();
    }

    private static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Secretary("Jorge Carvalho", YearMonth.of(2018, 1)));
        employees.add(new Secretary("Maria Souza", YearMonth.of(2015, 12)));
        employees.add(new Seller("Ana Silva", YearMonth.of(2021, 12),
                Arrays.asList(new Sale(YearMonth.of(2021, 12), 5200),
                        new Sale(YearMonth.of(2022, 1), 4000),
                        new Sale(YearMonth.of(2022, 2), 4200),
                        new Sale(YearMonth.of(2022, 3), 5850),
                        new Sale(YearMonth.of(2022, 4), 7000))));
        employees.add(new Seller("João Mendes", YearMonth.of(2021, 12),
                Arrays.asList(new Sale(YearMonth.of(2021, 12), 3400),
                        new Sale(YearMonth.of(2022, 1), 7700),
                        new Sale(YearMonth.of(2022, 2), 5000),
                        new Sale(YearMonth.of(2022, 3), 5900),
                        new Sale(YearMonth.of(2022, 4), 6500))));
        employees.add(new Manager("Juliana Alves", YearMonth.of(2017, 7)));
        employees.add(new Manager("Bento Albino", YearMonth.of(2014, 3)));

        return employees;
    }

}
