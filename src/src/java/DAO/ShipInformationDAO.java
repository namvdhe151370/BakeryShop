/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ShipInformation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hellb
 */
public class ShipInformationDAO extends DBContext {

    public int createReturnId(ShipInformation shipInfor) {
        try {
            String sql = "INSERT INTO Ship_Information\n"
                    + "([Address],[ReciverName],[Gender],[Mobile],[Email],[Notes])\n"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, shipInfor.getAddress());
            ps.setString(2, shipInfor.getReciverName());
            ps.setBoolean(3, shipInfor.isGender());
            ps.setString(4, shipInfor.getMobile());
            ps.setString(5, shipInfor.getEmail());
            ps.setString(6, shipInfor.getNotes());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShipInformationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        ShipInformationDAO sidao = new ShipInformationDAO();
        System.out.println(sidao.createReturnId(new ShipInformation("abc", "nam", true, "033222444", "namvd@gmail.com", "ship di")));
    }
    
}
