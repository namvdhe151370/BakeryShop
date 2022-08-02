/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.Common.PasswordProcessingController;
import Model.Customer_History;
import Model.Feature;
import Model.Role;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class UserDAO extends DBContext {

    PasswordProcessingController pp = new PasswordProcessingController();

    public static User getUsers(ResultSet rs) {
        try {
            while (rs.next()) {
                User a = new User();
                a.setId(rs.getInt("Id"));
                a.setEmail(rs.getString("email"));
                a.setPassword(rs.getString("password"));
                a.setRoleID(rs.getInt("RoleID"));
                a.setName(rs.getString("name"));
                a.setMobile(rs.getString("mobile"));
                a.setGender(rs.getBoolean("gender"));
                a.setImage(rs.getString("image"));
                a.setToken(rs.getString("token"));
                a.setExpirationToken(rs.getString("expiration"));
                a.setAddress(rs.getString("address"));
//                a.setStatus(rs.getInt("status"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User checkLogin(String email, String password) {
        try {
            String sql = "select u.*, f.FeatureID, f.url, r.RoleID, r.RoleName from [User] u left join [Role] r\n"
                    + "on u.RoleID= r.RoleID\n"
                    + "left join Feature_Role fr\n"
                    + "on fr.RoleID = r.RoleID\n"
                    + "left join Feature f\n"
                    + "on f.FeatureID = fr.FeatureID\n"
                    + "where u.Email =? and u.[Password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User a = new User();
                a.setId(rs.getInt("Id"));
                a.setEmail(rs.getString("email"));
                a.setPassword(rs.getString("password"));
                a.setRoleID(rs.getInt("RoleID"));
                a.setName(rs.getString("name"));
                a.setMobile(rs.getString("mobile"));
                a.setGender(rs.getBoolean("gender"));
                a.setImage(rs.getString("image"));
                a.setToken(rs.getString("token"));
                a.setExpirationToken(rs.getString("expiration"));
                a.setAddress(rs.getString("address"));
//                    a.setStatus(rs.getInt("status"));
                int featureID = rs.getInt("featureID");

                if (featureID != 0) {

                    for (Feature f : new UserDAO().getListFeatureByRoleID(a.getRoleID())) {
                        a.getFeatures().add(f);
                    }

                }
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int countFeature(int RoleID) {
        int n = 0;
        try {

            String sql = " select count(*) \n"
                    + " from Feature_Role where RoleID =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, RoleID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<Feature> getListFeatureByRoleID(int RoleID) {
        List<Feature> ls = new ArrayList<>();
        String sql = " SELECT *\n"
                + "FROM Feature f inner join Feature_Role fr\n"
                + "on f.FeatureID = fr.FeatureID\n"
                + "where RoleID = ?";;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, RoleID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feature f = new Feature();
                f.setFeatureID(rs.getInt("featureID"));
                f.setUrl(rs.getString("url"));
                ls.add(f);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public User checkUserExist(String email) {
        try {
            String sql = "select * from [User] where email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            return UserDAO.getUsers(rs);
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void Register(String Email, String Password, String Name, String Mobile, int Gender, String address) {
        String sql = "  INSERT INTO [dbo].[User]\n"
                + "           ([Email]\n"
                + "           ,[Password]\n"
                + "           ,[RoleID]\n"
                + "           ,[Name]\n"
                + "           ,[Mobile]\n"
                + "           ,[Gender]\n"
                + "           ,[Image]\n"
                + "           ,[Address])\n"
                + "     VALUES\n"
                + "           (?,?,'4',?,?,?,'NULL',?)";;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, Email);
            stm.setString(2, pp.encoding(Password));
            stm.setString(3, Name);
            stm.setString(4, Mobile);
            stm.setInt(5, Gender);
            stm.setString(6, address);

            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User GetUserProfileById(int Id) {
        try {

            String sql = "SELECT * FROM \n"
                    + "[User] u inner join [Role] r\n"
                    + "on u.RoleID = r.RoleID\n"
                    + "where u.Id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setGender(rs.getBoolean("gender"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getInt("statusId"));
                user.setMobile(rs.getString("mobile"));
                user.setImage(rs.getString("image"));
                Role role = new Role();
                role.setRoleID(rs.getInt("roleid"));
                role.setRoleName(rs.getString("rolename"));
                user.setRoleID2(role);
                return user;
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User GetUserById(int Id) {
        try {
            String sql = "select * from [User] where Id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Id);
            ResultSet rs = stm.executeQuery();
            return UserDAO.getUsers(rs);
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void UpdateUserInformation(String Name, String Mobile, boolean Gender, String Address, String Image, int Id) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Mobile] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[Address] = ?\n";

        if (!Image.equals("")) {
            sql += "      ,[Image] = '" + Image + "'";
        }
        sql += " WHERE Id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, Name);
            stm.setString(2, Mobile);
            stm.setBoolean(3, Gender);
            stm.setString(4, Address);
            stm.setInt(5, Id);
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    Change password for a account.
    public void changePassword(User account, String newPassword) {
        try {
            String sql = "UPDATE [User] set Password = ? where ID=?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, pp.encoding(newPassword));
            st.setInt(2, account.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//      Get User by email.
    public User getUserByEmail(String email) {
        try {
            String sql = "select *from [User] where email = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return UserDAO.getUsers(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    Set token and expirationtime for reset password link.
    public void setTokenResetPassword(String token, String email) {
        try {
            String sql = "UPDATE [User]\n"
                    + "   SET [token] = ?\n"
                    + "      ,[expiration] = GETDATE()\n"
                    + " WHERE email = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, token);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    Get user by token.
    public User getUserByToken(String token) {
        try {
            String sql = "select *from [user] where token = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, token);
            ResultSet rs = st.executeQuery();
            return UserDAO.getUsers(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    Destroy token and expiration time when time out or reset password successfully.
    public void destroyToken(User u) {
        try {
            String sql = "UPDATE [User]\n"
                    + "   SET [token] = null\n"
                    + "      ,[expiration] = null\n"
                    + " WHERE email = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getEmail());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Add New User By Admin 
    public int AddNewUser(String Email, String Password, int RoleID, String Name, String Mobile, int Gender, String address) {
        int n = 0;
        String sql = "  INSERT INTO [dbo].[User]\n"
                + "           ([Email]\n"
                + "           ,[Password]\n"
                + "           ,[RoleID]\n"
                + "           ,[Name]\n"
                + "           ,[Mobile]\n"
                + "           ,[Gender]\n"
                + "           ,[Image]\n"
                + "           ,[address]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,'NULL',?,'1')";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, Email);
            stm.setString(2, pp.encoding(Password));
            stm.setInt(3, RoleID);
            stm.setString(4, Name);
            stm.setString(5, Mobile);
            stm.setInt(6, Gender);
            stm.setString(7, address);
            n = stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

// Edit User Details
    public void EditUserDtails(int RoleID, int status, int Id) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [RoleID] =	?\n"
                + "      ,[status] =	?\n"
                + " WHERE Id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, RoleID);
            stm.setInt(2, status);
            stm.setInt(3, Id);
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// Get list role

    public List<Role> getALLRole() {
        List<Role> listRole = new ArrayList<>();
        try {
            String sql = "select * from Role";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt("roleID"), rs.getString("roleName"));
                listRole.add(role);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRole;
    }

    public List<Role> getRoleNameList() {
        String sql = "select * from role";
        List<Role> ls = new ArrayList<>();

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleID(rs.getInt(1));
                role.setRoleName(rs.getString(2));
                ls.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public List<String> getUserStatusList() {
        String sql = "select distinct [statusid] from [user]";
        List<String> ls = new ArrayList<>();

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String a = rs.getString("statusid");
                ls.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public List<String> getUserGenderList() {
        String sql = "select distinct [Gender] from [user]";
        List<String> ls = new ArrayList<>();

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String a = rs.getString("gender");
                ls.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    // Get User by Phone
    public User getUserByPhone(String Phone) {
        try {
            String sql = "select *from [User] where Mobile = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Phone);
            ResultSet rs = st.executeQuery();
            return UserDAO.getUsers(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //get All Users with RoleName
    public List<User> getAllWithRole() {
        List<User> ls = new ArrayList<>();
        try {
            String sql = "SELECT u.*, r.RoleName\n"
                    + "from [User] u inner join [Role] r\n"
                    + "on u.RoleID = r.RoleID";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setGender(rs.getBoolean("gender"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getInt("statusid"));
                user.setMobile(rs.getString("mobile"));
                user.setImage(rs.getString("image"));
                Role role = new Role();
                role.setRoleID(rs.getInt("roleid"));
                role.setRoleName(rs.getString("rolename"));
                user.setRoleID2(role);
                ls.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    //get lastest saler
    public int GetLastestSaler() {
        try {
            String sql = "select top 1 SaleId from [Order] group by SaleId \n"
                    + "order by MAX(OrderDate) ASC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<User> getSaleList() {

        String query = "select Id,[Name] from [User]\n"
                + "where RoleID =2";
        List<User> ls = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("Id"));
                u.setName(rs.getString("Name"));
                ls.add(u);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<Feature> getALLFeature() {
        List<Feature> ls = new ArrayList<>();
        try {
            String sql = "select r.*, f.* from [role] r inner join Feature_Role fr \n"
                    + "on r.RoleID = fr.RoleID\n"
                    + "inner join Feature f\n"
                    + "on f.FeatureID= fr.FeatureID";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feature f = new Feature();
                f.setRoleID(rs.getInt("roleID"));
                f.setRoleName(rs.getString("roleName"));
                f.setFeatureID(rs.getInt("featureID"));
                f.setUrl(rs.getString("url"));
                f.setDescription(rs.getString("description"));
                ls.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public List<User> getListCustomers(int page_index, int page_size, String fullname, String email, String mobile, int status, String orderBy, String direction) {
        try {
            String sql = "select *from (select *, ROW_NUMBER()  OVER(order by ";
            Hashtable<Integer, Object> params = new Hashtable<>();
            int index = 1;
            if (!orderBy.equals("")) {
                sql += (orderBy + " " + direction);
            } else {
                sql += (orderBy + "Id" + direction);
            }
            sql += ") as row_index from [User] where roleid=4 ";
            if (!fullname.equals("")) {
                sql += " and Name like ? ";
                params.put(index, fullname);
                index += 1;
            }
            if (!email.equals("")) {
                sql += " and Email like ? ";
                params.put(index, email);
                index += 1;
            }
            if (!mobile.equals("")) {
                sql += " and Mobile like ? ";
                params.put(index, mobile);
                index += 1;
            }
            if (status != -1) {
                sql += " and StatusId = ? ";
                params.put(index, status);
                index += 1;
            }
            sql += ") as tbl where (1=1) and row_index>=?*(?-1)+1 and row_index<=?*? ";
            PreparedStatement st = connection.prepareStatement(sql);

            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object value = entry.getValue();
                if (value.getClass() == String.class) {
                    st.setString(key, "%" + value + "%");
                } else if (value.getClass() == Integer.class) {
                    st.setInt(key, (Integer) value);
                }
            }
            st.setInt(index, page_size);
            st.setInt(index + 1, page_index);
            st.setInt(index + 2, page_size);
            st.setInt(index + 3, page_index);
            ResultSet rs = st.executeQuery();
            List<User> listCustomers = new ArrayList<>();
            while (rs.next()) {
                User a = new User();
                a.setId(rs.getInt("Id"));
                a.setEmail(rs.getString("email"));
                a.setPassword(rs.getString("password"));
                a.setRoleID(rs.getInt("RoleID"));
                a.setName(rs.getString("name"));
                a.setMobile(rs.getString("mobile"));
                a.setGender(rs.getBoolean("gender"));
                a.setImage(rs.getString("image"));
                a.setToken(rs.getString("token"));
                a.setStatus(rs.getInt("statusID"));
                a.setExpirationToken(rs.getString("expiration"));
                a.setAddress(rs.getString("address"));
                listCustomers.add(a);
            }
            System.out.println(sql);
            return listCustomers;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getNumberOfRecords(String fullname, String email, String mobile, int status) {
        try {
            String sql = "select COUNT(*) as total from (select *, ROW_NUMBER()  OVER(order by Email asc) as row_index from [User] where roleid=4) as tbl where (1=1) ";
            Hashtable<Integer, Object> params = new Hashtable<>();
            int index = 1;
            if (!fullname.equals("")) {
                sql += " and Name like ? ";
                params.put(index, fullname);
                index += 1;
            }
            if (!email.equals("")) {
                sql += " and Email like ? ";
                params.put(index, email);
                index += 1;
            }
            if (!mobile.equals("")) {
                sql += " and Mobile like ? ";
                params.put(index, mobile);
                index += 1;
            }
            if (status != -1) {
                sql += " and StatusId = ? ";
                params.put(index, status);
                index += 1;
            }
            PreparedStatement st = connection.prepareStatement(sql);

            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object value = entry.getValue();
                if (value.getClass() == String.class) {
                    st.setString(key, "%" + value + "%");
                } else if (value.getClass() == Integer.class) {
                    st.setInt(key, (Integer) value);
                }
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int getCountCusByDate(String from, String to) {
        int total = 0;
        try {
            String sql = "select count(Id) from [User]\n"
                    + "where dateCreated > = ? and dateCreated < = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, from);
            statement.setString(2, to);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
        }
        return total;
    }

    public int getTotalCustomer() {
        try {
            String sql = "select COUNT(Id) from [User]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getCountCustomersInRange(String roleid, String before, String then) {
        int n = 0;
        try {
            String sql = "SELECT count( *) \n"
                    + "FROM [User] u inner join [Role] r on u.RoleID = r.RoleID \n"
                    + "where u.roleid = ? and dateCreated between ? and ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, roleid);
            stm.setString(2, before);
            stm.setString(3, then);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getCountCustomerInRange(String status, String roleid, String before, String then) {
        int n = 0;
        try {
            String sql = "SELECT count(*) \n"
                    + "FROM [User] u inner join [Role] r on u.RoleID = r.RoleID \n"
                    + "inner join User_Status us on u.StatusID = us.UserStatusID\n"
                    + "where u.roleid = ? and dateCreated between ? and ? and StatusID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, roleid);
            stm.setString(2, before);
            stm.setString(3, then);
            stm.setString(4, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getCountCustomers(int role) {
        int n = 0;
        try {
            String sql = "SELECT count(*)\n"
                    + "FROM [User] u inner join [Role] r \n"
                    + "on u.RoleID = r.RoleID\n"
                    + "where u.roleid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, role);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getCountCustomersByDate(String date, String role) {
        int n = 0;
        try {
            String sql = "SELECT count(*)\n"
                    + "  from [User]\n"
                    + "  where RoleID = ?\n"
                    + "  and dateCreated = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, role);
            stm.setString(2, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    // add new customer
    public int AddNewCustomer(String Email, String Name, String Mobile, boolean Gender, String address) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([Email]\n"
                + "           ,[Password]\n"
                + "           ,[RoleID]\n"
                + "           ,[Name]\n"
                + "           ,[Mobile]\n"
                + "           ,[Gender]\n"
                + "           ,[address]\n"
                + "           ,[StatusID])\n"
                + "     VALUES\n"
                + "           (?,'123456',4,?,?,?,?,1)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, Email);
            stm.setString(2, Name);
            stm.setString(3, Mobile);
            stm.setBoolean(4, Gender);
            stm.setString(5, address);
            n = stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    //update information of customer
    public void UpdateCustomer(String Name, boolean Gender, String Email, String Mobile, String Address, int Id) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Gender] = ?\n"
                + "	  ,[Email] = ?\n"
                + "	  ,[Mobile] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[StatusID] = 1\n"
                + " WHERE Id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, Name);
            stm.setBoolean(2, Gender);
            stm.setString(3, Email);
            stm.setString(4, Mobile);
            stm.setString(5, Address);
            stm.setInt(6, Id);
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //insert customer change history
    public int InsertCustomerChangesHistory(int id, String email, String name, boolean gender, String mobile, String address) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customer_Changes_History]\n"
                + "           ([Id]\n"
                + "           ,[Email]\n"
                + "           ,[Name]\n"
                + "           ,[Gender]\n"
                + "           ,[Mobile]\n"
                + "           ,[address]\n"
                + "           ,[UpdateBy]\n"
                + "           ,[UpdateDate])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,'nam',GETDATE())";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, email);
            stm.setString(3, name);
            stm.setBoolean(4, gender);
            stm.setString(5, mobile);
            stm.setString(6, address);
            n = stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    //get list customer change history
    public List<Customer_History> getAllCustomerHistoryByID(int id) {
        List<Customer_History> ls = new ArrayList<>();
        try {
            String sql = "  select * from Customer_Changes_History where Id = ?\n"
                    + "  order by UpdateDate desc";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer_History user = new Customer_History();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setGender(rs.getBoolean("gender"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setAddress(rs.getString("address"));
                user.setUpdateby(rs.getString("updateby"));
                user.setUpdateDate(rs.getDate("updateDate"));
                ls.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    
    public static void main(String[] args) {
        UserDAO udb = new UserDAO();
        List<User> listUsers = udb.getListCustomers(1, 3, "", "", "", 1, "Email", "desc");
        for (User listUser : listUsers) {
            System.out.println(listUser);
        }
        System.out.println(udb.getALLFeature());
    }
}
