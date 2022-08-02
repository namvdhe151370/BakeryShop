/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Post;
import Model.Product;
import Model.Slider;
import Model.User;
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
public class SliderDAO extends DBContext {

    public int editSliderById(int sliderId, Slider s) {
        int n = 0;
        try {
            String sql = "UPDATE Slider\n"
                    + "SET Title = ?\n"
                    + ",Backlink = ?\n"
                    + ",[Image] = ?\n"
                    + ", Notes = ?\n"
                    + ", [Status] = ?\n"
                    + "WHERE SliderId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, s.getTitle());
            statement.setString(2, s.getBacklink());
            statement.setString(3, s.getImage());
            statement.setString(4, s.getNotes());
            statement.setInt(5, s.getStatus());
            statement.setInt(6, sliderId);

            n = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return n;
    }

    public List<Slider> getProductSliderforHomepage() {
        String sql = "SELECT Top 2 *, s.Image as simage\n"
                + " from Slider s\n"
                + " inner join Product p   on s.ProductID = p.ProductId";
        List<Slider> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Slider sliders = new Slider();
                sliders.setSliderID(rs.getInt("sliderid"));
                sliders.setTitle(rs.getString("title"));
                sliders.setStatus(rs.getInt("status"));
                sliders.setBacklink(rs.getString("backlink"));
                sliders.setImage(rs.getString("simage"));
                sliders.setNotes(rs.getString(("notes")));
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                sliders.setProductID(p);
                ls.add(sliders);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    public List<Slider> getHotPostSliderforHomepage() {
        String sql = "SELECT Top 1*, s.Image as simage\n"
                + "from [user] u inner join Slider s on u.Id = s.UserID \n"
                + "              inner join Post p on p.PostID = s.PostID\n"
                + "             inner join [Role] r on u.RoleID = r.RoleID\n"
                + "               where RoleName like 'marketing'";
        List<Slider> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Slider sliders = new Slider();
                sliders.setSliderID(rs.getInt("sliderid"));
                sliders.setTitle(rs.getString("title"));
                sliders.setStatus(rs.getInt("status"));
                sliders.setBacklink(rs.getString("backlink"));
                sliders.setImage(rs.getString("simage"));
                sliders.setNotes(rs.getString(("notes")));
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                Post p = new Post();
                p.setPostID(rs.getInt("postID"));
                p.setPostTitle(rs.getString("PostTitle"));
                p.setThumbnail(rs.getString("thumbnail"));
                sliders.setPostID(p);
                ls.add(sliders);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    public List<Slider> get3SliderforHomepage() {
        List<Slider> ls = new ArrayList<>();
        SliderDAO slider = new SliderDAO();
        List<Slider> lsProductSlider = slider.getProductSliderforHomepage();
        List<Slider> lsPostSlider = slider.getHotPostSliderforHomepage();
        try {
            for (Slider l : lsProductSlider) {
                ls.add(l);
            }
            for (Slider slider1 : lsPostSlider) {
                ls.add(slider1);
            }

        } catch (Exception e) {
        }

        return ls;
    }

    public List<Slider> getAllSliderByUserId(User user) {
        String sql = "select * from Slider where UserID = ?";
        List<Slider> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Slider sliders = new Slider();
                sliders.setSliderID(rs.getInt("sliderid"));
                sliders.setTitle(rs.getString("title"));
                sliders.setStatus(rs.getInt("status"));
                sliders.setBacklink(rs.getString("backlink"));
                sliders.setImage(rs.getString("image"));
                sliders.setNotes(rs.getString(("notes")));
                User u = new User();
                u.setId(rs.getInt("UserID"));
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                Post po = new Post();
                po.setPostID(rs.getInt("postID"));
                sliders.setProductID(p);
                sliders.setPostID(po);
                ls.add(sliders);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;

    }

    public void UpdateStatus(int sId, int status) {
        try {
            String sql = "UPDATE Slider\n"
                    + "SET [Status] = ?\n"
                    + "WHERE SliderId = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(2, sId);
            statement.setInt(1, status);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Slider getSliderById(int sid) {
        String sql = "select * from Slider where SliderId = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Slider sliders = new Slider();
                sliders.setSliderID(rs.getInt("sliderid"));
                sliders.setTitle(rs.getString("title"));
                sliders.setStatus(rs.getInt("status"));
                sliders.setBacklink(rs.getString("backlink"));
                sliders.setImage(rs.getString("image"));
                sliders.setNotes(rs.getString(("notes")));
                User u = new User();
                u.setId(rs.getInt("UserID"));
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                Post po = new Post();
                po.setPostID(rs.getInt("postID"));
                sliders.setProductID(p);
                sliders.setPostID(po);
                return sliders;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void editSliderById(int sliderId, String title, String backlink, String image, String note, int statusSlider) {
        try {
            String sql = "UPDATE Slider\n"
                    + "SET Title = ?\n"
                    + ",Backlink = ?\n"
                    + ",[Image] = ?\n"
                    + ", Notes = ?\n"
                    + ", [Status] = ?\n"
                    + "WHERE SliderId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, backlink);
            statement.setString(3, image);
            statement.setString(4, note);
            statement.setInt(5, statusSlider);
            statement.setInt(6, sliderId);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Slider> getAllSlider() {
        String sql = "SELECT *\n"
                + "  FROM [Sum22_SWP391_BakeryShop].[dbo].[Slider]";

        List<Slider> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Slider sliders = new Slider();
                sliders.setSliderID(rs.getInt("sliderid"));
                sliders.setTitle(rs.getString("title"));
                sliders.setStatus(rs.getInt("status"));
                sliders.setBacklink(rs.getString("backlink"));
                sliders.setImage(rs.getString("image"));
                sliders.setNotes(rs.getString(("notes")));
                User u = new User();
                u.setId(rs.getInt("UserID"));
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                Post po = new Post();
                po.setPostID(rs.getInt("postID"));
                sliders.setProductID(p);
                sliders.setPostID(po);
                ls.add(sliders);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;

    }

    public static void main(String[] args) {
        SliderDAO s = new SliderDAO();
        List<Slider> ls = s.getAllSlider();
        List<Slider> ls2 = s.getProductSliderforHomepage();
        for (Slider l : ls2) {
            System.out.println(l);
        }
    }
}
