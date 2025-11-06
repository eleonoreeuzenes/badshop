package badshop.state;

import java.util.List;

public class Order {
    public String id;
    public String customerId;
    public List<OrderLine> lines;
    public Date createdAt;
    public boolean shipped;
    public String shippingNote 
    public Double totalPrice = 0.0;

    public Order() {
        this.lines = new ArrayList();
    }

    public void addLine(OrderLine line) {
        this.lines.add(line);
    }

    public Double totalRawPrice() {
        // for (int i = 0; i <= lines.size(); i++) {
        //     totalPrice += lines[i].price * line[i].quantity;
        // }
        return totalPrice;
    }



}