/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Feedback;
import Model.Feedback_Images;
import Model.Product;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author long4
 */
public class FeedbackDAO extends DBContext {

    /**
     * set data for feedback of each product
     *
     * @param pid
     * @return
     */
    public List<Feedback> getProductFeedback(int pid) {
        String sql = "select f.FeedbackId ,f.RatedStar, f.UpdateDate, f.Note, u.Name, u.Image from Feedback f inner join [User] u\n"
                + "on f.UserId = u.Id\n"
                + "where f.ProductId = ?";
        List<Feedback> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {

            PreparedStatement stm = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            // loop all Result set to excute query and set data for feedback of each product
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackId(rs.getInt("FeedbackId"));
                feedback.setRatedStar(rs.getFloat("ratedStar"));
                feedback.setUpdateDate(rs.getDate("updateDate"));
                feedback.setNote(rs.getString("note"));
                User users = new User();
                users.setName(rs.getString("name"));
                users.setImage(rs.getString("image"));
                feedback.setUserId(users);
                ArrayList<Feedback_Images> lfi = new FeedbackDAO().getFeedbackImagesList(feedback.getFeedbackId());
                feedback.setListImages(lfi);
                ls.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public ArrayList<Feedback_Images> getFeedbackImagesList(int fid) {
        String sql = "select * from Feedback_Images\n"
                + "where FeedbackId=?";
        ArrayList<Feedback_Images> ls = new ArrayList<>();
        //check preparedStatement not be failed
        try {

            PreparedStatement stm = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stm.setInt(1, fid);
            ResultSet rs = stm.executeQuery();
            // loop all Result set to excute query and set data for feedback of each product
            while (rs.next()) {
                Feedback_Images feedbackimg = new Feedback_Images();
                feedbackimg.setFeedbackId(rs.getInt("FeedbackId"));
                feedbackimg.setFeedbackImageId(rs.getInt("FeedbackImageId"));
                feedbackimg.setImageName(rs.getString("ImageName"));
                feedbackimg.setStatus(rs.getBoolean("Status"));
                ls.add(feedbackimg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    /**
     * get the average of rating of product
     *
     * @param productId
     * @return
     */
    public float getAvgRateOfProduct(int productId) {
        String sql = "SELECT AVG(RatedStar)\n"
                + "FROM Feedback\n"
                + "WHERE ProductId=?";
        //check preparedStatement not be failed
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();
            // loop all Result set to excute query and get the average of rating of product
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Feedback> getListFeedbacks(ResultSet rs) {
        try {
            ArrayList<Feedback> listFeedbacks = new ArrayList<>();
            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setFeedbackId(rs.getInt("FeedbackId"));
                fb.setRatedStar(rs.getInt("RatedStar"));
                fb.setFeedbackStatus(rs.getBoolean("FeedbackStatus"));
                fb.setUpdateDate(rs.getDate("UpdateDate"));
                fb.setNote(rs.getString("Note"));
                fb.setTittle(rs.getString("Tittle"));
                ProductDAO productDAO = new ProductDAO();
                fb.setProductId(productDAO.getProductbyID(rs.getInt("ProductId")));
                UserDAO userDAO = new UserDAO();
                fb.setUserId(userDAO.GetUserById(rs.getInt("UserId")));
                OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
                String sql_image = "select *from [Feedback_Images] where FeedbackId=?";
                PreparedStatement stm = connection.prepareStatement(sql_image);
                stm.setInt(1, fb.getFeedbackId());
                ResultSet rs_image = stm.executeQuery();
                ArrayList<Feedback_Images> listImages = new ArrayList<>();
                while (rs_image.next()) {
                    Feedback_Images feedback_Images = new Feedback_Images();
                    feedback_Images.setFeedbackId(fb.getFeedbackId());
                    feedback_Images.setFeedbackImageId(rs_image.getInt("FeedbackImageId"));
                    feedback_Images.setStatus(rs_image.getBoolean("Status"));
                    feedback_Images.setImageName(rs_image.getString("ImageName"));
                    listImages.add(feedback_Images);
                }
                fb.setListImages(listImages);
                listFeedbacks.add(fb);
            }
            return listFeedbacks;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Feedback getFeedbackById(int feedbackId) {
        try {
            String sql = "select *from Feedback where FeedbackId=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, feedbackId);
            ResultSet rs = st.executeQuery();
            return getListFeedbacks(rs).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateStatusFeedback(int feedbackId, boolean status) {
        try {
            String sql = "UPDATE [Feedback]\n"
                    + "   SET [FeedbackStatus] = ?\n"
                    + " WHERE FeedbackId=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, status);
            st.setInt(2, feedbackId);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStatusImages(int imageId, boolean imageStatus) {
        try {
            String sql = "UPDATE [Feedback_Images]\n"
                    + "   SET [Status] = ?\n"
                    + " WHERE FeedbackImageId = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, imageStatus);
            st.setInt(2, imageId);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Feedback> getListFeedback() {
        List<Feedback> ls = new ArrayList<Feedback>();
        try {

            String sql = "SELECT f.* , p.ProductName, u.[Name] as username\n"
                    + "  FROM [Feedback] f inner join Product p\n"
                    + "  on f.ProductId = p.ProductId\n"
                    + "  inner join [User] u on f.UserId = u.id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackId(rs.getInt("feedbackid"));
                feedback.setRatedStar(rs.getFloat("ratedstar"));
                feedback.setTittle(rs.getString("tittle"));
                feedback.setFeedbackStatus(rs.getBoolean("feedbackstatus"));
                feedback.setUpdateDate(rs.getDate("updatedate"));
                Product product = new Product();
                product.setProductID(rs.getInt("productID"));
                product.setProductName(rs.getString("productName"));
                feedback.setProductId(product);
                User u = new User();
                u.setId(rs.getInt("userid"));
                u.setName(rs.getString("username"));
                feedback.setUserId(u);
                ls.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;

    }

    public List<String> getRatedStarList() {
        List<String> rateStar = new ArrayList<>();
        String sql = "SELECT Distinct [RatedStar] FROM [Sum22_SWP391_BakeryShop].[dbo].[Feedback]";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                rateStar.add(rs.getString("ratedstar"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rateStar;
    }

    public List<String> getStatusList() {
        List<String> rateStar = new ArrayList<>();
        String sql = "SELECT Distinct [feedbackstatus] FROM [Sum22_SWP391_BakeryShop].[dbo].[Feedback]";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                rateStar.add(rs.getString("feedbackstatus"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rateStar;
    }

    public int updateStatus(int id, int status) {
        int n = 0;
        try {
            String sql = "UPDATE [Feedback]\n"
                    + "SET [FeedbackStatus] = ?\n"
                    + "WHERE [FeedbackId] = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(2, id);
            statement.setInt(1, status);
            n = statement.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int getOrderDetailIdByOrderIdAndProductId(int orderId, int productId) {
        try {
            String sql = "select OrderDetailId from OrderDetail where OrderID = ? and ProductId=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            st.setInt(2, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("OrderDetailId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int getLastIndexOfFeedback_Images() {
        try {
            String sql = "select MAX(FeedbackImageId) as id from Feedback_Images";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public boolean isGivenFeedback(int userId, int productId, int orderId) {
        try {
            String sql = "select *from Feedback as f join OrderDetail as od on f.OrderDetailID = od.OrderDetailID\n"
                    + "where f.ProductId = ? and UserId = ? and OrderId=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.setInt(2, userId);
            st.setInt(3, orderId);
            ResultSet rs = st.executeQuery();
            int count = 0;
            while (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isBought(int productId, int userId, int orderId) {
        try {
            String sql = "select *from [Order]  as o join OrderDetail as od on o.OrderId = od.OrderId\n"
                    + "where UserId=? and ProductId=? and o.OrderId=? and OrderStatus = 4";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, productId);
            st.setInt(3, orderId);
            ResultSet rs = st.executeQuery();
            int count = 0;
            while (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public void insertFeedback(Feedback fb) {
        try {
            String sql = "INSERT INTO [Feedback]\n"
                    + "           ([UserId]\n"
                    + "           ,[OrderDetailID]\n"
                    + "           ,[RatedStar]\n"
                    + "           ,[ProductId]\n"
                    + "           ,[FeedbackStatus]\n"
                    + "           ,[UpdateDate]\n"
                    + "           ,[Note])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE()\n"
                    + "           ,?)";
//            connection.setAutoCommit(false);
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, fb.getUserId().getId());
            st.setInt(2, fb.getOrderDetailsId().getOrderDetailID());
            st.setInt(3, (int) fb.getRatedStar());
            st.setInt(4, fb.getProductId().getProductID());
            st.setInt(5, 1);
            st.setNString(6, fb.getNote());
            st.executeUpdate();

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    fb.setFeedbackId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            sql = "INSERT INTO [Feedback_Images]\n"
                    + "           ([FeedbackId]\n"
                    + "           ,[ImageName]\n"
                    + "           ,[Status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,1)";
            for (Feedback_Images listImage : fb.getListImages()) {
                PreparedStatement stimg = connection.prepareStatement(sql);
                stimg.setInt(1, fb.getFeedbackId());
                stimg.setString(2, listImage.getImageName());
                stimg.executeUpdate();
            }
//            connection.commit();

        } catch (SQLException ex) {

        } finally {

        }
    }

    public int getCountAllStarByDate(String from, String to) {
        int total = 0;
        try {
            String sql = "select sum(c.RatedStar) from\n"
                    + "(select b.*, o.OrderDate from\n"
                    + "(select a.*, p.CategoryID from\n"
                    + "(select od.OrderDetailID, f.RatedStar, od.ProductID, od.OrderID from Feedback f inner join OrderDetail od on f.OrderDetailID = od.OrderDetailID)a inner join Product p \n"
                    + "on a.ProductID = p.ProductId)b inner join [Order] o on b.OrderID= o.OrderId \n"
                    + "where o.OrderDate > = ? and o.OrderDate < = ? )c";
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

    public int getTotalAllFeebackByDate(String from, String to) {
        int total = 0;
        try {
            String sql = "select count(c.OrderDetailID) from\n"
                    + "(select b.*, o.OrderDate from\n"
                    + "(select a.*, p.CategoryID from\n"
                    + "(select od.OrderDetailID, f.RatedStar, od.ProductID, od.OrderID from Feedback f inner join OrderDetail od on f.OrderDetailID = od.OrderDetailID)a inner join Product p \n"
                    + "on a.ProductID = p.ProductId)b inner join [Order] o on b.OrderID= o.OrderId \n"
                    + "where o.OrderDate > = ? and o.OrderDate < = ? )c";
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

    public int getCountStarByDateAndCate(String from, String to, int i) {
        int total = 0;
        try {
            String sql = "select sum(c.RatedStar) from\n"
                    + "(select b.*, o.OrderDate from\n"
                    + "(select a.*, p.CategoryID from\n"
                    + "(select od.OrderDetailID, f.RatedStar, od.ProductID, od.OrderID from Feedback f inner join OrderDetail od on f.OrderDetailID = od.OrderDetailID)a inner join Product p \n"
                    + "on a.ProductID = p.ProductId)b inner join [Order] o on b.OrderID= o.OrderId \n"
                    + "where o.OrderDate > = ? and o.OrderDate < = ? and b.CategoryID = ? )c";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, from);
            statement.setString(2, to);
            statement.setInt(3, i);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
        }
        return total;
    }

    public int getTotalFeebackByDateAndCate(String from, String to, int i) {
        int total = 0;
        try {
            String sql = "select count(c.OrderDetailID) from\n"
                    + "(select b.*, o.OrderDate from\n"
                    + "(select a.*, p.CategoryID from\n"
                    + "(select od.OrderDetailID, f.RatedStar, od.ProductID, od.OrderID from Feedback f inner join OrderDetail od on f.OrderDetailID = od.OrderDetailID)a inner join Product p \n"
                    + "on a.ProductID = p.ProductId)b inner join [Order] o on b.OrderID= o.OrderId \n"
                    + "where o.OrderDate > = ? and o.OrderDate < = ? and b.CategoryID = ?)c";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, from);
            statement.setString(2, to);
            statement.setInt(3, i);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
        }
        return total;
    }

    public int getCountFeedbackInRange(String before, String then) {
        int n = 0;
        try {
            String sql = "SELECT count(*)\n"
                    + "  FROM [Feedback]\n"
                    + "  where UpdateDate between ? and ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, before);
            stm.setString(2, then);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getCountFeedbackInRange(String status, String before, String then) {
        int n = 0;
        try {
            String sql = "SELECT count(*)\n"
                    + "  FROM [Feedback]\n"
                    + "  where UpdateDate between ? and ? and FeedbackStatus = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(3, status);
            stm.setString(1, before);
            stm.setString(2, then);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getCountFeedback() {
        int n = 0;
        try {
            String sql = "SELECT count(*)\n"
                    + "FROM [Feedback] ";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        FeedbackDAO p = new FeedbackDAO();
//        List<Feedback> pt = p.getProductFeedback(1);
//        for (Feedback post_Category : pt) {
//            System.out.println(post_Category);
//        }
        List<Feedback> ls = p.getListFeedback();
        for (Feedback l : ls) {
            System.out.println(l);
        }

        System.out.println(p.isBought(1, 2, 5));

    }
}
