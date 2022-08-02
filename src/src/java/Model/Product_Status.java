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
public class Product_Status {
    private int productStatusID;
    private String status;

    public Product_Status() {
    }

    public Product_Status(int productStatusID, String status) {
        this.productStatusID = productStatusID;
        this.status = status;
    }

    public int getProductStatusID() {
        return productStatusID;
    }

    public void setProductStatusID(int productStatusID) {
        this.productStatusID = productStatusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product_Status{" + "productStatusID=" + productStatusID + ", status=" + status + '}';
    }
    
}
