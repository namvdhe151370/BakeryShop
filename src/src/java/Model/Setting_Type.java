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
public class Setting_Type {
    private int TypeID;
    private String TypeName;

    public Setting_Type() {
    }

    public Setting_Type(int TypeID, String TypeName) {
        this.TypeID = TypeID;
        this.TypeName = TypeName;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    @Override
    public String toString() {
        return "Setting_Type{" + "TypeID=" + TypeID + ", TypeName=" + TypeName + '}';
    }
   
}
