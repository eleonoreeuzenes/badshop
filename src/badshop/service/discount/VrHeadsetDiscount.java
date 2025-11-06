package badshop.service.discount;

import antipattern.DataStore;
import antipattern.Order;
import antipattern.Product;

class VrHeadsetDiscount extends Discount {
    VrHeadsetDiscount(Discount nextDiscount) {
        super(nextDiscount);
    }

    @Override
    protected double handle(Order order) {
        double rawPrice = order.totalRawPrice();

        if (rawPrice <= 1000) {
            return next(order);
        }

        for (OrderLine line : order.lines) {
            Product product = DataStore.findProductById(line.productId);
            if (product.name.toLowerCase().contains("vr")) {
                return rawPrice * 0.2;
            }
        }

        return next(order);
    }    
}
