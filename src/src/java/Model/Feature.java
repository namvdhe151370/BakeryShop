/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author long4
 */
public class Feature {

    private int featureID;
    private String url;
    private String description;
    private int roleID;
    private String roleName;

    public Feature() {
    }

    public Feature(int featureID, String url, String description, int roleID, String roleName) {
        this.featureID = featureID;
        this.url = url;
        this.description = description;
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFeatureID() {
        return featureID;
    }

    public void setFeatureID(int featureID) {
        this.featureID = featureID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Feature{" + "featureID=" + featureID + ", url=" + url + ", description=" + description + ", roleID=" + roleID + ", roleName=" + roleName + '}';
    }

}
