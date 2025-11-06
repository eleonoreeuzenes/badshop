package badshop.service.discount;

public class DiscountService {
    Discount discount;

    DiscountService() {
        this.discount = new VrHeadsetDiscount(new ThreeGameDiscount(
            new VipDiscount(new LargeCartDiscount(null))
        ));
    }

    public double computeDiscount(Order order) {
        return this.discount.handle(order);
    }
}