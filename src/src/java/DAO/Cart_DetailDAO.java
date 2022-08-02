/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cart;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hellb
 */
public class Cart_DetailDAO extends DBContext {

    public void UpdateCartDetail(int productID, int cartID, int quantity) {
        try {
            String sql = "UPDATE Cart_Detail\n"
                    + "SET Quantity = ?\n"
                    + "WHERE ProductId = ? and CartId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, productID);
            statement.setInt(3, cartID);
            statement.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void UpdateCartDetailNegative(int productId, int cartID) {
        try {
            String sql = "UPDATE Cart_Detail\n"
                    + "SET Quantity = (select Quantity from Cart_Detail where ProductId = ? and CartId = ?)\n"
                    + "WHERE ProductId = ? and CartId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, cartID);
            statement.setInt(3, productId);
            statement.setInt(4, cartID);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getMaxQuantityByProDuctId(int productId, int cartID) {
        try {
            String sql = "select p.Quantity from Cart_Detail cd left join Product p\n"
                    + "on cd.ProductId = p.ProductId \n"
                    + "where cd.ProductId = ? and cd.CartId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, cartID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void InsertCartDetail(int productID, int cartID, int quantity) {
        try {
            String sql = "insert into Cart_Detail(ProductId,CartId,Quantity) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productID);
            statement.setInt(2, cartID);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void DeleteCartDetail(int productId) {
        try {
            String sql = "DELETE FROM Cart_Detail WHERE ProductId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);

            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        Cart_DetailDAO cd = new Cart_DetailDAO();
        System.out.println(cd.getMaxQuantityByProDuctId(1, 1));
    }

    public void removeAllByCartId(int cartID) {
        try {
            String sql = "Delete from Cart_Detail where CartID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cartID);

            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

}
