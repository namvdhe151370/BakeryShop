/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Category;

/**
 *
 * @author admin
 */
public class CategoryDAO extends DBContext {

     public List<Category> getHomepageCategory() {

        String query = "SELECT top 3 * FROM category where status = 1";
        List<Category> ls = new ArrayList<>();

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                ls.add(category);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<Category> getAll(boolean status) {

        String query = "SELECT * FROM category where status = ?";
        List<Category> ls = new ArrayList<>();

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                ls.add(category);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    public List<Category> getAllCategory() {

        String query = "SELECT * FROM category";
        List<Category> ls = new ArrayList<>();

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
         
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                ls.add(category);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public Category getOne(int CategoryID) {

        String query = "SELECT * FROM category WHERE CategoryID = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, CategoryID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Category p = new Category();
                p.setCategoryID(rs.getInt(1));
                p.setCategoryName(rs.getString(2));
                p.setStatus(rs.getBoolean(3));
                return p;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;

    }

    public int UpdateCategory(Category c, int CategoryID) {
        int n = 0;
        try {

            String sql = "update Category set CategoryName = ?, status = ? where CategoryID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(3, CategoryID);
            stm.setString(1, c.getCategoryName());
            stm.setBoolean(2, c.isStatus());

            n = stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int add(Category c, String CategoryName) {
        int n = 0;
        try {
            if (checkCate(CategoryName) != null) {
                System.out.println("Category existed");
            } else {

                String sql = "INSERT INTO category(CategoryName,status)"
                        + " VALUES(?, ?)";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, CategoryName);
                stm.setBoolean(2, c.isStatus());
                n = stm.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changeStatus(int CategoryID) {
        int n = 0;
        String preSql = "update category set status= 0 where CategoryID=?";

        try {
            PreparedStatement pre = connection.prepareStatement(preSql);

            pre.setInt(1, CategoryID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Category checkCate(String CategoryName) {
        String sql = "select * from Category where CategoryName=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, CategoryName);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                return c;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public int getTotalCategory(boolean b) {
        try {
            String sql = "select COUNT(CategoryID) from Category";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    public List<Category> getListCategoryPaging(boolean b, int pageCategory, int PAZE_CA_SIZE) {

        String query = "SELECT * FROM Category WHERE Status = ?\n"
                + "order by CategoryID asc\n"
                + "offset (?-1)*? row fetch next ? rows only";
        List<Category> list = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, b);
            ps.setInt(2, pageCategory);
            ps.setInt(3, PAZE_CA_SIZE);
            ps.setInt(4, PAZE_CA_SIZE);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Category p = new Category();
                p.setCategoryID(rs.getInt(1));
                p.setCategoryName(rs.getString(2));
                p.setStatus(rs.getBoolean(3));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    public int getTotalCategoryByCartId(boolean status, int cartID) {
        try {
            String sql = "select count(a.CategoryID) from\n"
                    + "(select count(cd.ProductId) as totalbyCategory, p.CategoryID from Cart_Detail cd left join Product p\n"
                    + "on cd.ProductId =p.ProductId where p.StatusID = ? and cd.CartId = ?\n"
                    + "group by p.CategoryID\n"
                    + ") a";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, cartID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Category> getListCategoryPagingByCartId(boolean b, int cartID, int pageCategory, int CA_PAZE_SIZE) {
        String query = "select * from\n"
                + "(select c.* from\n"
                + "(select count(cd.ProductId) as totalbyCategory, p.CategoryID from Cart_Detail cd left join Product p\n"
                + "on cd.ProductId =p.ProductId where p.StatusID = ? and cd.CartId = ?\n"
                + "group by p.CategoryID\n"
                + ") a left join Category c on a.CategoryID = c.CategoryID )b\n"
                + "order by b.CategoryID asc\n"
                + "offset (?-1)*? row fetch next ? rows only";
        List<Category> list = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, b);
            ps.setInt(2, cartID);
            ps.setInt(3, pageCategory);
            ps.setInt(4, CA_PAZE_SIZE);
            ps.setInt(5, CA_PAZE_SIZE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category p = new Category();
                p.setCategoryID(rs.getInt("categoryID"));
                p.setCategoryName(rs.getString("categoryName"));
                p.setStatus(rs.getBoolean("Status"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
    public List<Category> getListCategoryByCartId(boolean b, int cartID) {
        String query = "select * from\n"
                + "(select c.* from\n"
                + "(select count(cd.ProductId) as totalbyCategory, p.CategoryID from Cart_Detail cd left join Product p\n"
                + "on cd.ProductId =p.ProductId where p.StatusID = ? and cd.CartId = ?\n"
                + "group by p.CategoryID)\n"
                + "a left join Category c on a.CategoryID = c.CategoryID )b\n";
        List<Category> list = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, b);
            ps.setInt(2, cartID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category p = new Category();
                p.setCategoryID(rs.getInt("categoryID"));
                p.setCategoryName(rs.getString("categoryName"));
                p.setStatus(rs.getBoolean("Status"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
    public static void main(String[] args) {

        System.out.println(new CategoryDAO().getListCategoryPagingByCartId(true, 2, 1, 3));
    }
    
}
