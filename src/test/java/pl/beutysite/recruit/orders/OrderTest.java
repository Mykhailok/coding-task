package pl.beutysite.recruit.orders;

import org.junit.Test;
import pl.beutysite.recruit.OrderFlag;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void testCalculatePriceForDiscountedOrder() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.DISCOUNTED);

        BigDecimal price = order.getPrice();

        assertEquals(89.0, price.doubleValue(), 1e-2);
    }


    @Test
    public void testCalculateTaxForDiscountedOrder() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.DISCOUNTED);

        BigDecimal price = order.getTax();

        assertEquals(20.92, price.doubleValue(), 1e-2);//23% от 89
    }

    @Test
    public void testCalculatePriceForInternationalOrder() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.INTERNATIONAL);

        BigDecimal price = order.getPrice();

        assertEquals(100, price.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculateTaxForInternationalOrder() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.INTERNATIONAL);

        BigDecimal price = order.getTax();

        assertEquals(15.00, price.doubleValue(), 1e-2);//23%-8,5%
    }

    @Test
    public void testCalculatePriceForPriorityOrder() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.PRIORITY);

        BigDecimal price = order.getPrice();

        assertEquals(101.5, price.doubleValue(), 1e-2);//100+1,5%, получилось 23,85
    }

    @Test
    public void testCalculateTaxForPriorityOrder() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.PRIORITY);

        BigDecimal price = order.getTax();

        assertEquals(23.85, price.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculatePriceForStandardOrder() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.STANDARD);
        BigDecimal price = order.getPrice();
        assertEquals(100, price.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculateTaxForStandardOrder() throws Exception {
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.STANDARD);
        BigDecimal price = order.getTax();
        assertEquals(23.5, price.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculatePriceForDiscountedPriorityOrder() throws Exception {//обговорить с заказчиком
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.DISCOUNTED, OrderFlag.PRIORITY);
        BigDecimal price = order.getPrice();
        assertEquals(90.5, price.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculateTaxForDiscountedPriorityOrder() throws Exception {//обговорить с заказчиком
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.DISCOUNTED, OrderFlag.PRIORITY);
        BigDecimal price = order.getTax();
        assertEquals(21.27, price.doubleValue(), 1e-2);
    }
    @Test
    public void testCalculatePriceForDiscounterInternationalOrder() throws Exception {//обговорить с заказчиком
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.DISCOUNTED, OrderFlag.INTERNATIONAL);
        BigDecimal price = order.getPrice();
        assertEquals(89, price.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculateTaxForDiscounterInternationalOrder() throws Exception {//обговорить с заказчиком
        Order order = new Order(10, 10, new BigDecimal(100), OrderFlag.DISCOUNTED, OrderFlag.INTERNATIONAL);
        BigDecimal price = order.getTax();
        assertEquals(13.35, price.doubleValue(), 1e-2);
    }


}