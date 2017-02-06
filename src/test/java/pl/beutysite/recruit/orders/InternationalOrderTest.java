package pl.beutysite.recruit.orders;

import org.junit.Test;
import pl.beutysite.recruit.OrderFlag;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by alexander.kryvenko on 06.02.2017.
 */
public class InternationalOrderTest {

    @Test
    public void testCalculatePrice() throws Exception {
        Order order = new InternationalOrder(10, 10, new BigDecimal(100), OrderFlag.INTERNATIONAL);

        BigDecimal price = order.getPrice();

        assertEquals(100, price.doubleValue(), 1e-2);
    }


    @Test
    public void testCalculateTax() throws Exception {
        Order order = new InternationalOrder(10, 10, new BigDecimal(100), OrderFlag.INTERNATIONAL);

        BigDecimal price = order.getTax();

        assertEquals(15.00, price.doubleValue(), 1e-2);
    }
}