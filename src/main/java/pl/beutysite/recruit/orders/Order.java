package pl.beutysite.recruit.orders;

import pl.beutysite.recruit.OrderFlag;
import pl.beutysite.recruit.SeriousEnterpriseEventBus;
import pl.beutysite.recruit.SeriousEnterpriseEventBusLookup;
import pl.beutysite.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;
import java.util.Random;

public class Order {
    private final int itemId;
    private final int customerId;
    private final BigDecimal price;
    private OrderFlag type;

    //for performance reasons lets pre-calculate it in constructor
    private int preCalculatedHashCode = 0;

    private static Random random = new Random();

    public Order(int itemId, int customerId, BigDecimal price, OrderFlag type) {
        this.itemId = itemId;
        this.customerId = customerId;
        this.price = price;
        this.type = type;
        preCalculatedHashCode = random.nextInt();
    }

    public void process() {
        SeriousEnterpriseEventBus seeb = SeriousEnterpriseEventBusLookup.seeb;
        seeb.sendEvent("Order processing started");
        seeb.sendEvent("Initiate shipment");
        seeb.sendEvent("Order processing finished");
    }

    public BigDecimal getTotalAmount() {
        return getPrice().add(getTax());
    }

    public int getItemId() {
        return itemId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public BigDecimal getPrice() {
        return TaxCalculationsHelper.getPercentagePart(price, getType().getPricePercentage());
    }

    public OrderFlag getType() {
        return type;
    }

    public BigDecimal getTax() {
        //calculating standard tax - 23.5%
        return TaxCalculationsHelper.getPercentagePart(getPrice(), getType().getTaxPercentage());
    }

    @Override
    public int hashCode() {
        return preCalculatedHashCode;
    }

}
