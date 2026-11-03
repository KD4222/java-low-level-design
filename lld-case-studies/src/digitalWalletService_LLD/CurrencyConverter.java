import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private static final Map<Currency,BigDecimal> exchangeRates= new HashMap<>();

    static{
        // Rates are illustrative units of each currency per USD.
        exchangeRates.put(Currency.getInstance("USD"), BigDecimal.ONE);
        exchangeRates.put(Currency.getInstance("INR"), new BigDecimal("100.00"));
        exchangeRates.put(Currency.getInstance("GBP"), new BigDecimal("0.72"));
        exchangeRates.put(Currency.getInstance("JPY"), new BigDecimal("110.00"));
    }

    public static BigDecimal convert(BigDecimal amount, Currency sourceCurrency, Currency targetCurrency){
        if (amount == null || sourceCurrency == null || targetCurrency == null) {
            throw new IllegalArgumentException("Amount and currencies are required.");
        }

        BigDecimal sourceRate= exchangeRates.get(sourceCurrency);
        BigDecimal targetRate= exchangeRates.get(targetCurrency);
        if (sourceRate == null || targetRate == null) {
            throw new IllegalArgumentException("Unsupported currency conversion.");
        }

        BigDecimal amountInUsd = amount.divide(sourceRate, 10, RoundingMode.HALF_UP);
        return amountInUsd.multiply(targetRate)
                .setScale(targetCurrency.getDefaultFractionDigits(), RoundingMode.HALF_UP);
    }
}
