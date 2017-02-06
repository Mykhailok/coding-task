package pl.beutysite.recruit.orders;

import org.junit.Test;
import pl.beutysite.recruit.OrderFlag;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by alexander.kryvenko on 05.02.2017.
 */
public class PriorityOrderTest {


    @Test
    public void testCalculatePrice() throws Exception {
        Order order = new PriorityOrder(10, 10, new BigDecimal(100), OrderFlag.PRIORITY);

        BigDecimal price = order.getPrice();

        assertEquals(101.5, price.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculateTax() throws Exception {
        Order order = new PriorityOrder(10, 10, new BigDecimal(100), OrderFlag.PRIORITY);

        BigDecimal price = order.getTax();

        assertEquals(23.85, price.doubleValue(), 1e-2);

    }
}