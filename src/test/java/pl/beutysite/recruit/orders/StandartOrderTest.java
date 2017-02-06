package pl.beutysite.recruit.orders;

import org.junit.Test;
import pl.beutysite.recruit.OrderFlag;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by alexander.kryvenko on 05.02.2017.
 */
public class StandartOrderTest {


    @Test
    public void testCalculatePrice() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.STANDARD);

        BigDecimal price = order.getPrice();

        assertEquals(100, price.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculateTax() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.STANDARD);

        BigDecimal price = order.getTax();

        assertEquals(23.5, price.doubleValue(), 1e-2);

    }
}