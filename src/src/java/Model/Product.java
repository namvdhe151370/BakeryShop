/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;



/**
 *
 * @author admin
 */
public class Product {

    private int productID;
    private String productName;
    private String title;
    private String description;
    private boolean status;
    private String statusName;
    private float price;
    private int quantity;
    private String thumbnail;
    private int weight;
    private int degree;
    private int time;
    private Date createdate;
    private float discount;
    private Category categoryID;

    public Product() {
    }

    public Product(int productID, String productName, String title, String description, boolean status, float price, int quantity, String thumbnail, int weight, int degree, int time, Date createdate, float discount, Category categoryID) {
        this.productID = productID;
        this.productName = productName;
        this.title = title;
        this.description = description;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.thumbnail = thumbnail;
        this.weight = weight;
        this.degree = degree;
        this.time = time;
        this.createdate = createdate;
        this.discount = discount;
        this.categoryID = categoryID;
    }
    
    
    public Product(int productID, String productName, String title, String description, boolean status, String statusName, float price, int quantity, String thumbnail, int weight, int degree, int time, Date createdate, float discount, Category categoryID) {
        this.productID = productID;
        this.productName = productName;
        this.title = title;
        this.description = description;
        this.status = status;
        this.statusName = statusName;
        this.price = price;
        this.quantity = quantity;
        this.thumbnail = thumbnail;
        this.weight = weight;
        this.degree = degree;
        this.time = time;
        this.createdate = createdate;
        this.discount = discount;
        this.categoryID = categoryID;
    }

    

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", title=" + title + ", description=" + description + ", status=" + status + ", statusName=" + statusName + ", price=" + price + ", quantity=" + quantity + ", thumbnail=" + thumbnail + ", weight=" + weight + ", degree=" + degree + ", time=" + time + ", createdate=" + createdate + ", discount=" + discount + ", categoryID=" + categoryID + '}';
    }
    
}