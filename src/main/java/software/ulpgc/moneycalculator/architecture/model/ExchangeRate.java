package software.ulpgc.moneycalculator.architecture.model;

import java.time.LocalDate;

public record ExchangeRate(Currency from, Currency to, LocalDate date, double rate) {
}