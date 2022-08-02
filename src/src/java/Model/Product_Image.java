/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author admin
 */
public class Product_Image {
    private int productImageID;
    private Product productID;
    private String image;

    public Product_Image() {
    }

    public Product_Image(int productImageID, Product productID, String image) {
        this.productImageID = productImageID;
        this.productID = productID;
        this.image = image;
    }

    public int getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(int productImageID) {
        this.productImageID = productImageID;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
