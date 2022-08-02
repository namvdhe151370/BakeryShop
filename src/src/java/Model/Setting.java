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
public class Setting {
    private int settingId;
    private Setting_Type typeId;
    private int value;
    private String order;
    private Setting_Status status;
    private String description;

    public Setting() {
    }

    public Setting(int settingId, Setting_Type typeId, int value, String order, Setting_Status Status, String description) {
        this.settingId = settingId;
        this.typeId = typeId;
        this.value = value;
        this.order = order;
        this.status = Status;
        this.description = description;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public Setting_Type getTypeId() {
        return typeId;
    }

    public void setTypeId(Setting_Type typeId) {
        this.typeId = typeId;
    }

  

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Setting_Status getStatus() {
        return status;
    }

    public void setStatus(Setting_Status Status) {
        this.status = Status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Setting{" + "settingId=" + settingId + ", typeId=" + typeId + ", value=" + value + ", order=" + order + ", Status=" + status + ", description=" + description + '}';
    }

    
    
    
}
