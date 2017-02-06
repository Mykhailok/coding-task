package pl.beutysite.recruit.orders;

import pl.beutysite.recruit.OrderFlag;
import pl.beutysite.recruit.SeriousEnterpriseEventBus;
import pl.beutysite.recruit.SeriousEnterpriseEventBusLookup;

import java.math.BigDecimal;

//TODO will have more taxes calculated
public class InternationalOrder extends Order {

    public InternationalOrder(int itemId, int customerId, BigDecimal price, OrderFlag type) {
        super(itemId, customerId, price, type);
    }

    public void process() {
        SeriousEnterpriseEventBus seeb = SeriousEnterpriseEventBusLookup.seeb;
        seeb.sendEvent("Order processing started");

        seeb.sendEvent("Dispatch translated order confirmation email");

        seeb.sendEvent("Initiate shipment");
        seeb.sendEvent("Order processing finished");
    }
}
