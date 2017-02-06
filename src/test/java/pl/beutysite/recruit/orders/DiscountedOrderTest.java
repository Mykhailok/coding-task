package pl.beutysite.recruit.orders;

import org.junit.Test;
import pl.beutysite.recruit.OrderFlag;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by alexander.kryvenko on 05.02.2017.
 */
public class DiscountedOrderTest {

    @Test
    public void testCalculatePrice() throws Exception {
        Order order = new DiscountedOrder(10, 10, new BigDecimal(100), OrderFlag.DISCOUNTED);

        BigDecimal price = order.getPrice();

        assertEquals(89.0, price.doubleValue(), 1e-2);
    }


    @Test
    public void testCalculateTax() throws Exception {
        Order order = new DiscountedOrder(10, 10, new BigDecimal(100), OrderFlag.DISCOUNTED);

        BigDecimal price = order.getTax();

        assertEquals(20.92, price.doubleValue(), 1e-2);
    }
}