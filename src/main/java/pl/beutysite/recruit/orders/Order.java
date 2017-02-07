package pl.beutysite.recruit.orders;

import pl.beutysite.recruit.OrderFlag;
import pl.beutysite.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;
import java.util.Random;

public class Order {

    public static final double STANDARD_TAX = 23.5;

    private final int itemId;
    private final int customerId;
    private final BigDecimal price;
    private OrderFlag[] types;

    //for performance reasons lets pre-calculate it in constructor
    private int preCalculatedHashCode = 0;

    private static Random random = new Random();

    public Order(int itemId, int customerId, BigDecimal price, OrderFlag... types) {
        this.itemId = itemId;
        this.customerId = customerId;
        this.price = price;
        this.types = types;
        preCalculatedHashCode = random.nextInt();//don't know why need this, because system already calculate hash.
    }

    public void process() {//get Orders and for each type of order calling this process, like sending emails
        for (OrderFlag type : types) {
            type.getOrderProcessor().process(this);
        }
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
        BigDecimal percentage = new BigDecimal(100);
        for (OrderFlag t : types) {//gathering all values for tax and price per each order, if there is a multi-ordering, so we add taxes from each OrderFlag
            percentage = percentage.add(t.getPricePercentage());
        }

        return TaxCalculationsHelper.getPercentagePart(price, percentage);
    }

    public BigDecimal getTax() {
        //calculating standard tax - 23.5%
        BigDecimal percentage = new BigDecimal(STANDARD_TAX);
        for (OrderFlag t : types) {
            percentage = percentage.add(t.getTaxPercentage());
        }
        return TaxCalculationsHelper.getPercentagePart(getPrice(), percentage);
    }

    @Override
    public int hashCode() {
        return preCalculatedHashCode;
    }

}
