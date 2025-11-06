package badshop.service.discount;

import antipattern.Customer;
import antipattern.DataStore;
import antipattern.Order;

class VipDiscount extends Discount {
    VipDiscount(Discount nextDiscount) {
        super(nextDiscount);
    }

    @Override
    protected double handle(Order order) {
        Customer customer = DataStore.findCustomerById(order.customerId);
        if (customer.email != null && customer.email.toLowerCase().contains("vip")) {
            return 30.0 + next(order);
        }

        return next(order);
    }
}
