package software.ulpgc.moneycalculator.model;

public record Money(double amount, Currency currency) {
    @Override
    public String toString() {
        return round(amount) + " " + currency;
    }

    private double round(double value) {
        return Math.round(value * 100) / 100D;
    }
}
