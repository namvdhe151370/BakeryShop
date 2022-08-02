/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;



/**
 *
 * @author admin
 */
public class User {

    private int id;
    private String email;
    private String password;
    private int roleID;
    private String name;
    private String mobile;
    private String image;
    private boolean gender;
    private String token;
    private String expirationToken;
    private String address;
    private int status;
    private Role roleID2;
    
    private ArrayList<Feature> features = new ArrayList<>(); 

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    public Role getRoleID2() {
        return roleID2;
    }

    public void setRoleID2(Role roleID2) {
        this.roleID2 = roleID2;
    }
    

    public User(int id, String email, String password, int roleID, String name, String mobile, String image, boolean gender, String token, String expirationToken, String address, int status, Role roleID2) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.name = name;
        this.mobile = mobile;
        this.image = image;
        this.gender = gender;
        this.token = token;
        this.expirationToken = expirationToken;
        this.address = address;
        this.status = status;
        this.roleID2 = roleID2;
    }
    
    public User() {
    }

    public User(int id) {
        this.id = id;
    }
    

    public User(int id, String email, String password, int roleID, String name, String mobile, String image, boolean gender, String token, String expirationToken, String address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.name = name;
        this.mobile = mobile;
        this.image = image;
        this.gender = gender;
        this.token = token;
        this.expirationToken = expirationToken;
        this.address = address;
    }

    public User(int id, String email, String password, int roleID, String name, String mobile, String image, boolean gender, String token, String expirationToken, String address, int status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.name = name;
        this.mobile = mobile;
        this.image = image;
        this.gender = gender;
        this.token = token;
        this.expirationToken = expirationToken;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpirationToken() {
        return expirationToken;
    }

    public void setExpirationToken(String expirationToken) {
        this.expirationToken = expirationToken;
    }

 
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", roleID=" + roleID + ", name=" + name + ", mobile=" + mobile + ", image=" + image + ", gender=" + gender + ", token=" + token + ", expirationToken=" + expirationToken + ", address=" + address + ", status=" + status + ", roleID2=" + roleID2 + ", features=" + features + '}';
    }
    
}
