/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author long4
 */
public class Order_Details {

    private Order orderId;
    private Product productId;
    private float discount;
    private float price;
    private int quantity;
    private int orderDetailID;
    private boolean statusFeedback;

    public boolean isStatusFeedback() {
        return statusFeedback;
    }

    public void setStatusFeedback(boolean statusFeedback) {
        this.statusFeedback = statusFeedback;
    }

    public Order_Details() {
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Order_Details(Order orderId, Product productId, float discount, float price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order_Details{" + "orderId=" + orderId + ", productId=" + productId + ", discount=" + discount + ", price=" + price + ", quantity=" + quantity + '}';
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

}
