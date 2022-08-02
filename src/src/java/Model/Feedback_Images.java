/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class Feedback_Images {
    private int feedbackId;
    private int feedbackImageId;
    private boolean status;
    private String imageName;

    public Feedback_Images() {
    }

    public Feedback_Images(int feedbackId, int feedbackImageId, boolean status, String imageName) {
        this.feedbackId = feedbackId;
        this.feedbackImageId = feedbackImageId;
        this.status = status;
        this.imageName = imageName;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getFeedbackImageId() {
        return feedbackImageId;
    }

    public void setFeedbackImageId(int feedbackImageId) {
        this.feedbackImageId = feedbackImageId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Feedback_Images{" + "feedbackId=" + feedbackId + ", feedbackImageId=" + feedbackImageId + ", status=" + status + ", imageName=" + imageName + '}';
    }  
}
