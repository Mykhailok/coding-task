package pl.beutysite.recruit;

import pl.beutysite.recruit.orders.DiscountedOrder;
import pl.beutysite.recruit.orders.InternationalOrder;
import pl.beutysite.recruit.orders.Order;
import pl.beutysite.recruit.orders.PriorityOrder;

import java.math.BigDecimal;
import java.util.*;

public class OrdersManagementSystemImpl implements OrdersManagementSystem {

    //external systems
    private final TaxOfficeAdapter taxOfficeAdapter;
    private final ItemsRepository itemsRepository;


    private Queue<Order> otherOrdersQueue = new LinkedList<Order>();
    private Queue<Order> priorityOrdersQueue = new LinkedList<Order>();


    public OrdersManagementSystemImpl(TaxOfficeAdapter taxOfficeAdapter, ItemsRepository itemsRepository) {
        this.taxOfficeAdapter = taxOfficeAdapter;
        this.itemsRepository = itemsRepository;
    }


    public void createOrder(int itemId, int customerId, OrderFlag... flags) {
        //fetch price and calculate discount and taxes
        BigDecimal itemPrice = itemsRepository.fetchItemPrice(itemId);

        Order newOrder = null;

        //create and queue order
        OrderFlag flag = flags[0];
        switch (flag) {
            case STANDARD:
                newOrder = new Order(itemId, customerId, itemPrice);
                otherOrdersQueue.add(newOrder);
                break;
            case PRIORITY:
                newOrder = new PriorityOrder(itemId, customerId, itemPrice);
                priorityOrdersQueue.add(newOrder);
                break;
            case INTERNATIONAL:
                newOrder = new InternationalOrder(itemId, customerId, itemPrice);
                otherOrdersQueue.add(newOrder);
                break;
            case DISCOUNTED:
                newOrder = new DiscountedOrder(itemId, customerId, itemPrice);
                otherOrdersQueue.add(newOrder);
                break;
        }

        //send tax due amount
        taxOfficeAdapter.registerTax(newOrder.getTax());
    }

    @Override
    public Order fetchNextOrder() {

        if(!priorityOrdersQueue.isEmpty())
            return priorityOrdersQueue.poll();

        return otherOrdersQueue.poll();
    }


    private class OrdersComparator implements Comparator<Order> {

        public int compare(Order o1, Order o2) {

            int p1 = 1;
            int p2 = 1;

            if (o1 instanceof PriorityOrder) p1 = 0;
            if (o2 instanceof PriorityOrder) p2 = 0;

            return Integer.compare(p1, p2);
        }
    }
}
