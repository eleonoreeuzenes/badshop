package badshop.service.discount;

/**
 * The Discount class uses a `Chain of Responsability` pattern
 * {@link https://refactoring.guru/design-patterns/behavioral-patterns}.
 * <p>
 * Each discount is responsible of calling (or not) the next discount handler
 * via the `next` method.
 */
abstract class Discount {
    private Discount nextDiscount;

    protected Discount(Discount nextDiscount) {
        this.nextDiscount = nextDiscount;
    }

    public abstract double handle(Order order);

    protected final double next(Order order) {
        if (nextDiscount == null) {
            return 0;
        }

        return nextDiscount.handle(order);
    }
}
