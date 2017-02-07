package pl.beutysite.recruit;

import pl.beutysite.recruit.orders.Order;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class OrdersManagementSystemImpl implements OrdersManagementSystem {

    //external systems
    private final TaxOfficeAdapter taxOfficeAdapter;
    private final ItemsRepository itemsRepository;

//priorityOrdersQueue make two Queues because if order have a priority flag, so we add it to priorityOrdersQueue and with other Flags we add them to otherOrdersQueue
    private Queue<Order> otherOrdersQueue = new LinkedList<Order>();
    private Queue<Order> priorityOrdersQueue = new LinkedList<Order>();

    public OrdersManagementSystemImpl(TaxOfficeAdapter taxOfficeAdapter, ItemsRepository itemsRepository) {
        this.taxOfficeAdapter = taxOfficeAdapter;
        this.itemsRepository = itemsRepository;
    }

    public void createOrder(int itemId, int customerId, OrderFlag... flags) {
        //fetch price and calculate discount and taxes
        BigDecimal itemPrice = itemsRepository.fetchItemPrice(itemId);

        Order order = new Order(itemId, customerId, itemPrice, flags);

        if(Arrays.asList(flags).contains(OrderFlag.PRIORITY)) {
            priorityOrdersQueue.add(order);
        } else {
            otherOrdersQueue.add(order);
        }

        //send tax due amount
        taxOfficeAdapter.registerTax(order.getTax());
    }

    @Override
    public Order fetchNextOrder() {//check is priorityOrdersQueue empty or not

        if(!priorityOrdersQueue.isEmpty())
            return priorityOrdersQueue.poll();

        return otherOrdersQueue.poll();
    }
}
