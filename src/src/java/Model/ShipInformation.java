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
public class ShipInformation {
    private int shipInfoID;
    private String address;
    private String ReciverName;
    private boolean gender;
    private String mobile;
    private String email;
    private String notes;

    public ShipInformation() {
    }

    public ShipInformation(String address, String ReciverName, boolean gender, String mobile, String email, String notes) {
        this.address = address;
        this.ReciverName = ReciverName;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.notes = notes;
    }
    
    public ShipInformation(int shipInfoID, String address, String ReciverName, boolean gender, String mobile, String email, String notes) {
        this.shipInfoID = shipInfoID;
        this.address = address;
        this.ReciverName = ReciverName;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.notes = notes;
    }

    public int getShipInfoID() {
        return shipInfoID;
    }

    public void setShipInfoID(int shipInfoID) {
        this.shipInfoID = shipInfoID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReciverName() {
        return ReciverName;
    }

    public void setReciverName(String ReciverName) {
        this.ReciverName = ReciverName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ShipInformation{" + "shipInfoID=" + shipInfoID + ", address=" + address + ", ReciverName=" + ReciverName + ", gender=" + gender + ", mobile=" + mobile + ", email=" + email + ", notes=" + notes + '}';
    }
    
    
}
