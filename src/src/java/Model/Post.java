/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Post {
    private int postID;
    private User userID;
    private Post_Category postCategoryID;
    private String thumbnail;
    private String postTitle;
    private String briefInformation;
    private String postDescription;
    private String featured;
    private String postDate;
    private String updateDate;
    private boolean postStatus;

    public Post() {
    }

 
    
    public Post(int postID, User userID, Post_Category postCategoryID, String thumbnail, String postTitle, String briefInformation, String postDescription, String featured, String postDate, String updateDate, boolean postStatus) {
        this.postID = postID;
        this.userID = userID;
        this.postCategoryID = postCategoryID;
        this.thumbnail = thumbnail;
        this.postTitle = postTitle;
        this.briefInformation = briefInformation;
       this.postDescription = postDescription;
        this.featured = featured;
        this.postDate = postDate;
        this.updateDate = updateDate;
        this.postStatus = postStatus;
    }

    public Post(User userID, Post_Category postCategoryID, String thumbnail, String postTitle, String briefInformation, String postDescription, String featured, String postDate, String updateDate, boolean postStatus) {
        this.userID = userID;
        this.postCategoryID = postCategoryID;
        this.thumbnail = thumbnail;
        this.postTitle = postTitle;
        this.briefInformation = briefInformation;
        this.postDescription = postDescription;
        this.featured = featured;
        this.postDate = postDate;
        this.updateDate = updateDate;
        this.postStatus = postStatus;
    }

  

    
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Post_Category getPostCategoryID() {
        return postCategoryID;
    }

    public void setPostCategoryID(Post_Category postCategoryID) {
        this.postCategoryID = postCategoryID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getBriefInformation() {
        return briefInformation;
    }

    public void setBriefInformation(String briefInformation) {
        this.briefInformation = briefInformation;
    }

    

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isPostStatus() {
        return postStatus;
    }

    public void setPostStatus(boolean postStatus) {
        this.postStatus = postStatus;
    }

    @Override
    public String toString() {
        return "Post{" + "postID=" + postID + ", userID=" + userID + ", postCategoryID=" + postCategoryID + ", thumbnail=" + thumbnail + ", postTitle=" + postTitle + ", briefInformation=" + briefInformation + ", postDescription=" + postDescription + ", featured=" + featured + ", postDate=" + postDate + ", updateDate=" + updateDate + ", postStatus=" + postStatus + '}';
    }

    

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    
}
