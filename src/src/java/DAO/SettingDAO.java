/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Setting;
import Model.Setting_Status;
import Model.Setting_Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hellb
 */
public class SettingDAO extends DBContext {

    public List<Setting> getSettingList() {
        List<Setting> ls = new ArrayList<>();
        try {
            String sql = "select * \n"
                    + "from Settings se inner join SettingStatus ss on se.SettingStatusID = ss.StatusID\n"
                    + "inner join SettingType st on se.SettingTypeID = st.TypeID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting se = new Setting();
                se.setSettingId(rs.getInt("settingID"));
                se.setValue(rs.getInt("settingvalue"));
                se.setOrder(rs.getString("settingOrder"));
                Setting_Status ss = new Setting_Status();
                ss.setStatusID(rs.getInt("settingStatusID"));
                ss.setStatus(rs.getString("status"));
                se.setStatus(ss);
                Setting_Type st = new Setting_Type();
                st.setTypeID(rs.getInt("TypeID"));
                st.setTypeName(rs.getString("TypeName"));
                se.setTypeId(st);
                ls.add(se);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public List<Setting_Status> getSettingStatusList() {
        List<Setting_Status> ls = new ArrayList<>();
        try {
            String sql = "select * \n"
                    + "from SettingStatus";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Setting_Status ss = new Setting_Status();
                ss.setStatusID(rs.getInt("StatusID"));
                ss.setStatus(rs.getString("status"));

                ls.add(ss);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public List<Setting_Type> getSettingTypeList() {
        List<Setting_Type> ls = new ArrayList<>();
        try {
            String sql = "select * \n"
                    + "from SettingType";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Setting_Type st = new Setting_Type();
                st.setTypeID(rs.getInt("TypeID"));
                st.setTypeName(rs.getString("TypeName"));

                ls.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public int updateStatus(int id, int status) {
        int n = 0;
        try {
            String sql = "UPDATE [Settings]\n"
                    + "SET SettingStatusID = ?\n"
                    + "WHERE settingid = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(2, id);
            statement.setInt(1, status);
            n = statement.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public Setting getSettingById(int settingId) {
        String sql = "select a.*, ss.status from SettingStatus ss inner join\n"
                + "(select s.*, st.TypeName from Settings s inner join SettingType st\n"
                + "on s.SettingTypeID = st.TypeID)a\n"
                + "on ss.statusID = a.SettingStatusID\n"
                + "where a.SettingID = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, settingId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Setting setting = new Setting();
                setting.setSettingId(rs.getInt("settingId"));
                Setting_Type set = new Setting_Type();
                set.setTypeID(rs.getInt("SettingTypeID"));
                set.setTypeName(rs.getString("TypeName"));
                setting.setTypeId(set);
                setting.setValue(rs.getInt("SettingValue"));
                setting.setOrder(rs.getString("SettingOrder"));
                Setting_Status ses = new Setting_Status();
                ses.setStatusID(rs.getInt("SettingStatusID"));
                ses.setStatus(rs.getString("status"));
                setting.setStatus(ses);
                setting.setDescription(rs.getString("SettingDescription"));
                return setting;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void UpdateSettingById(int settingId, int typeId, int value, String order, int statusId, String description) {
        try {
            String sql = "UPDATE Settings\n"
                    + "set SettingTypeID = ? , SettingValue = ? , SettingOrder = ? ,\n"
                    + "SettingStatusID = ?, SettingDescription = ?\n"
                    + "where SettingID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, typeId);
            statement.setInt(2, value);
            statement.setString(3, order);
            statement.setInt(4, statusId);
            statement.setString(5, description);
            statement.setInt(6, settingId);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Setting_Status> getListSettingStatus() {
        String sql = "select * from SettingStatus";
        List<Setting_Status> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stm.executeQuery();
            //// loop all ResultSet and set data for each post 
            while (rs.next()) {

                Setting_Status seStatus = new Setting_Status();
                seStatus.setStatusID(rs.getInt("statusID"));
                seStatus.setStatus(rs.getString("status"));

                ls.add(seStatus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    public List<Setting_Type> getListSettingType() {
        String sql = "select * from SettingType";
        List<Setting_Type> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stm.executeQuery();
            //// loop all ResultSet and set data for each post 
            while (rs.next()) {

                Setting_Type seType = new Setting_Type();
                seType.setTypeID(rs.getInt("TypeID"));
                seType.setTypeName(rs.getString("TypeName"));

                ls.add(seType);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    public void InsertSettingById(int typeId, int value, String order, int statusId, String description) {
        try {
            String sql = "INSERT INTO Settings(SettingTypeID, SettingValue, SettingOrder, SettingStatusID,SettingDescription)\n"
                    + "VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, typeId);
            statement.setInt(2, value);
            statement.setString(3, order);
            statement.setInt(4, statusId);
            statement.setString(5, description);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        List<Setting_Type> ls = new ArrayList<>();
        SettingDAO dao = new SettingDAO();
//        ls = dao.getSettingTypeList();
//        for (Setting_Type l : ls) {
//            System.out.println(l);
//        }
        System.out.println(dao.updateStatus(7, 1));
    }

}
