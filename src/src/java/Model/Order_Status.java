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
public class Order_Status {
    private int orderStatusID;
    private String status;

    public Order_Status() {
    }

    public Order_Status(int orderStatusID, String status) {
        this.orderStatusID = orderStatusID;
        this.status = status;
    }

    public int getOrderStatusID() {
        return orderStatusID;
    }

    public void setOrderStatusID(int orderStatusID) {
        this.orderStatusID = orderStatusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order_Status{" + "orderStatusID=" + orderStatusID + ", status=" + status + '}';
    }

    
}
