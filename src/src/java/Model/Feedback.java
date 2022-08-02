/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author long4
 */
public class Feedback {
    private int feedbackId;
    private User userId;
    private Order_Details orderDetailsId;
    private float ratedStar;
    private Product productId;
    private boolean feedbackStatus;
    private Date updateDate;
    private String tittle;
    private String note;
    private ArrayList<Feedback_Images> listImages;

    public Feedback(int feedbackId, User userId, Order_Details orderDetailsId, float ratedStar, Product productId, boolean feedbackStatus, Date updateDate, String tittle, String note, ArrayList<Feedback_Images> listImages) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.orderDetailsId = orderDetailsId;
        this.ratedStar = ratedStar;
        this.productId = productId;
        this.feedbackStatus = feedbackStatus;
        this.updateDate = updateDate;
        this.tittle = tittle;
        this.note = note;
        this.listImages = listImages;
    }

    public ArrayList<Feedback_Images> getListImages() {
        return listImages;
    }

    public void setListImages(ArrayList<Feedback_Images> listImages) {
        this.listImages = listImages;
    }

    public Feedback() {
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Order_Details getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Order_Details orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public float getRatedStar() {
        return ratedStar;
    }

    public void setRatedStar(float ratedStar) {
        this.ratedStar = ratedStar;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public boolean isFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(boolean feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedbackId=" + feedbackId + ", userId=" + userId + ", orderDetailsId=" + orderDetailsId + ", ratedStar=" + ratedStar + ", productId=" + productId + ", feedbackStatus=" + feedbackStatus + ", updateDate=" + updateDate + ", tittle=" + tittle + ", note=" + note + ", listImages=" + listImages + '}';
    }
    
}
