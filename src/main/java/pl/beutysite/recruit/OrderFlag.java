package pl.beutysite.recruit;

import pl.beutysite.recruit.orders.*;

import java.math.BigDecimal;

public enum OrderFlag {
    PRIORITY(0, +1.5, new PriorityOrderProcessor()),
    DISCOUNTED(0, -11, new DiscountedOrderProcessor()),
    INTERNATIONAL(-8.5, 0, new InternationalOrderProcessor()),
    STANDARD(0, 0, new StandardOrderProcessor());

    private BigDecimal taxPercentage;
    private BigDecimal pricePercentage;
    private final OrderProcessor orderProcessor;

    OrderFlag(double taxPercentage, double pricePercentage, OrderProcessor orderProcessor) {
        this.taxPercentage = new BigDecimal(taxPercentage);
        this.pricePercentage = new BigDecimal(pricePercentage);
        this.orderProcessor = orderProcessor;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public BigDecimal getPricePercentage() {
        return pricePercentage;
    }

    public OrderProcessor getOrderProcessor() {
        return orderProcessor;
    }
}
