package sinerji.models;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Seller extends Employee {

    private static final double BENEFIT = 0.3;

    public Seller(String name, YearMonth yearMonthHire, List<Sale> sales) {
        super(name, yearMonthHire, 12000, 1800);
        this.sales = Optional.ofNullable(sales).orElse(new ArrayList<>());
    }

    private List<Sale> sales;

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public double calculateBenefitByYearMonth(YearMonth yearMonth) {
        if (yearMonth.isAfter(yearMonthHire)) {
            return sales.stream()
                    .filter(sale -> sale.getYearMonth().equals(yearMonth.minusMonths(1)))
                    .mapToDouble(sale -> sale.getValue() * BENEFIT)
                    .sum();
        }
        return 0;
    }

    public boolean hasSaleByYearMonth(YearMonth yearMonth) {
        return sales.stream()
                .anyMatch(sale -> sale.getYearMonth().equals(yearMonth));
    }

    public double getTotalSoldByYearMonth(YearMonth yearMonth) {
        return sales.stream()
                .filter(sale -> sale.getYearMonth().equals(yearMonth))
                .mapToDouble(Sale::getValue)
                .sum();
    }

    public int compareWhoSoldMoreOf(Seller other, YearMonth yearMonth) {
        return Double.compare(getTotalSoldByYearMonth(yearMonth),
                other.getTotalSoldByYearMonth(yearMonth));
    }
}
