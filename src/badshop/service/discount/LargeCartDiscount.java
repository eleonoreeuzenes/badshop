package badshop.service.discount;

import antipattern.Order;

class LargeCartDiscount extends Discount {
    LargeCartDiscount(Discount nextDiscount) {
        super(nextDiscount);
    }

    @Override
    protected double handle(Order order) {
        double rawPrice = order.computeTotalRaw();

        if (rawPrice > 500) {
            return rawPrice * 0.10 + next(order);
        } 
        
        if (order.computeTotalRaw() > 300) {
            return 20.0 + next(order);
        }

        if (rawPrice > 200) {
            return 15.0 + next(order);
        }

        return next(order);
    }
}
