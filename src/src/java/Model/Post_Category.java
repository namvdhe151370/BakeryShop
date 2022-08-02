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
public class Post_Category {
    private int postCategoryID;
    private String postCategoryName;
    private boolean status;
    private Post postID;

    public Post_Category() {
    }

    public Post_Category(int postCategoryID) {
        this.postCategoryID = postCategoryID;
    }
    

    public Post_Category(int postCategoryID, String postCategoryName, boolean status, Post postID) {
        this.postCategoryID = postCategoryID;
        this.postCategoryName = postCategoryName;
        this.status = status;
        this.postID = postID;
    }

    public int getPostCategoryID() {
        return postCategoryID;
    }

    public void setPostCategoryID(int postCategoryID) {
        this.postCategoryID = postCategoryID;
    }

    public String getPostCategoryName() {
        return postCategoryName;
    }

    public void setPostCategoryName(String postCategoryName) {
        this.postCategoryName = postCategoryName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Post getPostID() {
        return postID;
    }

    public void setPostID(Post postID) {
        this.postID = postID;
    }

    @Override
    public String toString() {
        return "Post_Category{" + "postCategoryID=" + postCategoryID + ", postCategoryName=" + postCategoryName + ", status=" + status + ", postID=" + postID + '}';
    }
    
}