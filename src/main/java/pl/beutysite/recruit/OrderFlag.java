package pl.beutysite.recruit;

import pl.beutysite.recruit.orders.*;

import java.math.BigDecimal;
//Universal enumerator where you can create so many OrderFlags as you want, without changing the code
public enum OrderFlag {//contains info how to calculate tax
    PRIORITY(0, +1.5, new PriorityOrderProcessor()),//1 parameter - Standard tax 23,5% and 0 tell us that we don't want to change it, 2 parameter tells us how much percent we should + or - to/from price
    DISCOUNTED(0, -11, new DiscountedOrderProcessor()),//23,5%, price-11%
    INTERNATIONAL(-8.5, 0, new InternationalOrderProcessor()),//23,5%-8,5%
    STANDARD(0, 0, new StandardOrderProcessor());//Standart tax 23,5%, price without changes

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
