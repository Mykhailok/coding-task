package pl.beutysite.recruit.orders;

public interface OrderProcessor {

    void process(Order order);
//for each order type there is his own processor, orders was typical, so settings changing their behavior

}
