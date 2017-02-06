package pl.beutysite.recruit.orders;

import pl.beutysite.recruit.OrderFlag;
import pl.beutysite.recruit.SeriousEnterpriseEventBus;
import pl.beutysite.recruit.SeriousEnterpriseEventBusLookup;
import pl.beutysite.recruit.TaxCalculationsHelper;

import java.math.BigDecimal;

public class DiscountedOrder extends Order {

    public DiscountedOrder(int itemId, int customerId, BigDecimal price, OrderFlag type) {
        super(itemId, customerId, price, type);
    }

    public void process() {
        SeriousEnterpriseEventBus seeb = SeriousEnterpriseEventBusLookup.seeb;
        seeb.sendEvent("Order processing started");

        seeb.sendEvent("Run fraud detection and revenue integrity check");

        seeb.sendEvent("Initiate shipment");
        seeb.sendEvent("Order processing finished");
    }
}
