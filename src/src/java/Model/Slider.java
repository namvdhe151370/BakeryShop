/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hellb
 */
public class Slider {
    private int sliderID;
    private Product productID;
    private Post postID;
    private User userID;
    private String title;
    private String backlink;
    private String image;
    private String notes;
    private int status;
    
    public Slider() {
    }

    public Slider(int sliderID, Product productID, Post postID, User userID, String title, String backlink, String image, String notes, int status) {
        this.sliderID = sliderID;
        this.productID = productID;
        this.postID = postID;
        this.userID = userID;
        this.title = title;
        this.backlink = backlink;
        this.image = image;
        this.notes = notes;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
    public int getSliderID() {
        return sliderID;
    }

    public void setSliderID(int sliderID) {
        this.sliderID = sliderID;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public Post getPostID() {
        return postID;
    }

    public void setPostID(Post postID) {
        this.postID = postID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Slider{" + "sliderID=" + sliderID + ", productID=" + productID + ", postID=" + postID + ", userID=" + userID + ", title=" + title + ", backlink=" + backlink + ", image=" + image + ", notes=" + notes + ", status=" + status + '}';
    }

   

  
    
}
