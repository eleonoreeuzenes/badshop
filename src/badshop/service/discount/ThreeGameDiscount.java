package badshop.service.discount;

import antipattern.DataStore;
import antipattern.Order;
import antipattern.Product;

class ThreeGameDiscount extends Discount {
    ThreeGameDiscount(Discount nextDiscount) {
        super(nextDiscount);
    }

    @Override
    protected double handle(Order order) {
        double discount = 0;

        for (OrderLine line : order.lines) {
            Product product = DataStore.findProductById(line.productId);
            if (product.name.toLowerCase().contains("jeu") && line.quantity >= 3) {
                discount += 0.05 * product.price * line.quantity;
            }
        }

        return discount + next(order);
    }
}
