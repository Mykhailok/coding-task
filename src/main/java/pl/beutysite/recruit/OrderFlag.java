package pl.beutysite.recruit;

import java.math.BigDecimal;

public enum OrderFlag {
    PRIORITY(new BigDecimal("23.5"), new BigDecimal(100 + 1.5)),
    DISCOUNTED(new BigDecimal("23.5"), new BigDecimal(100 - 11)),
    INTERNATIONAL(new BigDecimal("15.0"), new BigDecimal(100)),
    STANDARD(new BigDecimal("23.5"), new BigDecimal(100));

    private BigDecimal taxPercentage;
    private BigDecimal pricePercentage;

    OrderFlag(BigDecimal taxPercentage, BigDecimal pricePercentage) {
        this.taxPercentage = taxPercentage;
        this.pricePercentage = pricePercentage;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public BigDecimal getPricePercentage() {
        return pricePercentage;
    }
}
