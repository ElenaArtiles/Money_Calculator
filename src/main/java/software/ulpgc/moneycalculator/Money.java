package software.ulpgc.moneycalculator;

public record Money(double amount, Currency currency) {
    @Override
    public String toString() {
        return round(amount) + " " + currency;
    }

    private double round(double value) {
        return Math.round(value * 100) / 100D;
    }
}
