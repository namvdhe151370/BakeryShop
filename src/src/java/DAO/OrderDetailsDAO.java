/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cart_Detail;
import Model.Order;
import Model.Order_Details;
import Model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class OrderDetailsDAO extends DBContext{
    public ArrayList<Order_Details> getOrder_Details(ResultSet rs){
        try {
            ArrayList<Order_Details> listOrder_Details = new ArrayList<>();
            while(rs.next()){
                Order_Details od = new Order_Details();
                Order o = new Order();
                o.setOderID(rs.getInt("OrderId"));
                od.setOrderId(o);
                ProductDAO productDAO = new ProductDAO();
                od.setProductId(productDAO.getProductbyID(rs.getInt("productId")));
                od.setDiscount(rs.getFloat("discount"));
                od.setPrice(rs.getFloat("price"));
                od.setQuantity(rs.getInt("quantity"));
                listOrder_Details.add(od);
            }
            return listOrder_Details;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Order_Details> getOrder_DetailsByOrderId(int id){
        try {
            String sql = "select *from OrderDetail where OrderId=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            return getOrder_Details(rs);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  
    
    
    public static void main(String[] args) {
        OrderDAO odao = new OrderDAO();
        List<Order_Details> ls = odao.getAllOrderID();
        
        OrderDetailsDAO odDao = new OrderDetailsDAO();
         ArrayList<Order_Details> lsod;
        for (Order_Details l : ls) {
            System.out.println(l.getOrderId().getOderID());
            System.out.println("-------------------------");
         
        }
       
    }

    public void saveOrderDetalsByProduct(int orderId, Cart_Detail value) {
        try {
            String sql = "INSERT INTO OrderDetail\n"
                    + "([OrderID],[ProductID],[Discount],[Price],[Quantity])\n"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            Product product = value.getProduct();
            ps.setInt(1, orderId);

            ps.setInt(2, product.getProductID());
            ps.setFloat(3, product.getDiscount());
            ps.setDouble(4, product.getPrice());
            ps.setDouble(5, value.getQuantity());
            
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // update quantity of orderinformation
    public void UpdateQuantity(int productID, int orderID, int quantity) {
        try {
            String sql = "UPDATE [dbo].[OrderDetail]\n"
                    + "   SET [Quantity] = ?\n"
                    + " WHERE ProductID =? and OrderID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, productID);
            statement.setInt(3, orderID);
            statement.executeUpdate();
        } catch (Exception e) {
        }

    }
   
}
