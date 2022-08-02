/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author HuyThai
 */
public class Customer_History {
    private int Id;
    private String email;
    private String name;
    private boolean gender;
    private String mobile;
    private String address;
    private String updateby;
    private Date updateDate;

    public Customer_History() {
    }

    public Customer_History(int Id, String email, String name, boolean gender, String mobile, String address, String updateby, Date updateDate) {
        this.Id = Id;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.mobile = mobile;
        this.address = address;
        this.updateby = updateby;
        this.updateDate = updateDate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Customer_History{" + "Id=" + Id + ", email=" + email + ", name=" + name + ", gender=" + gender + ", mobile=" + mobile + ", address=" + address + ", updateby=" + updateby + ", updateDate=" + updateDate + '}';
    }
    
    
}