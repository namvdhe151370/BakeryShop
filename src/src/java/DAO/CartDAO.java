/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cart;
import Model.Cart_Detail;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author hellb
 */
public class CartDAO extends DBContext {

    public Cart getCartByUserID(User user) {
        Cart c = new Cart();
        try {
            String sql = "select c.*, cd.ProductId, cd.Quantity from Cart c left join Cart_Detail cd\n"
                    + "on c.CartId = cd.CartId\n"
                    + "where c.UserId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            ProductDAO pdao = new ProductDAO();
            Map<Integer, Cart_Detail> carts = new LinkedHashMap<>();
            while (rs.next()) {
                c.setCartID(rs.getInt("CartId"));
                c.setUserId(user);
                Cart_Detail cartDe = new Cart_Detail();
                cartDe.setCart(c);
                cartDe.setProduct(pdao.getProductbyID(rs.getInt("ProductId")));;
                cartDe.setQuantity(rs.getInt("Quantity"));
                if(cartDe.getQuantity()!= 0){
                carts.put(rs.getInt("ProductId"), cartDe);
                }
            }
            c.setCarts(carts);
        } catch (Exception e) {
        }
        return c;
    }

    public Cart getAddToCartByProductId(User user, int productID) {
        Cart c = new Cart();
        try {
            String sql = "select c.*, cd.ProductId, cd.Quantity from Cart c left join Cart_Detail cd\n"
                    + "on c.CartId = cd.CartId\n"
                    + "where c.UserId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            ProductDAO pdao = new ProductDAO();
            Map<Integer, Cart_Detail> carts = new LinkedHashMap<>();
            while (rs.next()) {
                c.setCartID(rs.getInt("CartId"));
                c.setUserId(user);
                Cart_Detail cartDe = new Cart_Detail();
                cartDe.setCart(c);
                cartDe.setProduct(pdao.getProductbyID(rs.getInt("ProductId")));;
                cartDe.setQuantity(rs.getInt("Quantity"));
                if(cartDe.getQuantity()!= 0){
                carts.put(rs.getInt("ProductId"), cartDe);
                }
            }
            c.setCarts(carts);
        } catch (Exception e) {
        }
        return c;
    }

    public void InsertCart(int userId) {
        try {
            String sql = "insert into Cart(UserId) values(?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Cart getCartByUserIdPaging(User user, int page, int PAZE_SIZE) {
        Cart c = new Cart();
        try {
            String sql = " select c.*, cd.ProductId, cd.Quantity from Cart c left join Cart_Detail cd\n"
                    + "on c.CartId = cd.CartId\n"
                    + "where c.UserId = ?\n"
                    + "order by cd.ProductId asc \n"
                    + "offset (?-1)*? row fetch next ? rows only";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setInt(2, page);
            statement.setInt(3, PAZE_SIZE);
            statement.setInt(4, PAZE_SIZE);
            ResultSet rs = statement.executeQuery();
            ProductDAO pdao = new ProductDAO();
            Map<Integer, Cart_Detail> carts = new LinkedHashMap<>();
            while (rs.next()) {
                c.setCartID(rs.getInt("CartId"));
                c.setUserId(user);
                Cart_Detail cartDe = new Cart_Detail();
                cartDe.setCart(c);
                cartDe.setProduct(pdao.getProductbyID(rs.getInt("ProductId")));;
                cartDe.setQuantity(rs.getInt("Quantity"));
                if(cartDe.getQuantity()!= 0){
                carts.put(rs.getInt("ProductId"), cartDe);
                }
            }
            c.setCarts(carts);
        } catch (Exception e) {
        }
        return c;
    }

    public Cart getCartByCidAndCartIdPaging(User user, int keywordInt, int page, int PAZE_SIZE) {
        Cart c = new Cart();
        try {
            String sql = " select a.*, p.CategoryID from\n"
                    + "(select c.*, cd.ProductId, cd.Quantity from Cart c left join Cart_Detail cd\n"
                    + "on c.CartId = cd.CartId\n"
                    + "where c.UserId = ?)a left join Product p \n"
                    + "on a.ProductId = p.ProductId\n"
                    + "where p.CategoryID = ?\n"
                    + "order by p.ProductId asc \n"
                    + "offset (?-1)* ? row fetch next ? rows only";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setInt(2, keywordInt);
            statement.setInt(3, page);
            statement.setInt(4, PAZE_SIZE);
            statement.setInt(5, PAZE_SIZE);
            ResultSet rs = statement.executeQuery();
            ProductDAO pdao = new ProductDAO();
            Map<Integer, Cart_Detail> carts = new LinkedHashMap<>();
            while (rs.next()) {
                c.setCartID(rs.getInt("CartId"));
                c.setUserId(user);
                Cart_Detail cartDe = new Cart_Detail();
                cartDe.setCart(c);
                cartDe.setProduct(pdao.getProductbyID(rs.getInt("ProductId")));;
                cartDe.setQuantity(rs.getInt("Quantity"));
                if(cartDe.getQuantity()!= 0){
                carts.put(rs.getInt("ProductId"), cartDe);
                }
            }
            c.setCarts(carts);
        } catch (Exception e) {
        }
        return c;
    }

    public Cart getCartBySearchNamAndCartIdPaging(User user, String skeyword, int page, int PAZE_SIZE) {
        Cart c = new Cart();
        try {
            String sql = "select b.*,cg.CategoryName from \n"
                    + "(select a.*, p.CategoryID, p.ProductName from\n"
                    + "(select c.*, cd.ProductId, cd.Quantity from Cart c left join Cart_Detail cd\n"
                    + "on c.CartId = cd.CartId\n"
                    + "where c.UserId = ?)a left join Product p \n"
                    + "on a.ProductId = p.ProductId)b left join Category cg\n"
                    + "on b.CategoryID = cg.CategoryID\n"
                    + "where b.ProductName like ? or cg.CategoryName like ?\n"
                    + "order by b.ProductId asc \n"
                    + "offset (?-1)* ? row fetch next ? rows only";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setString(2, "%" + skeyword + "%");
            statement.setString(3, "%" + skeyword + "%");
            statement.setInt(4, page);
            statement.setInt(5, PAZE_SIZE);
            statement.setInt(6, PAZE_SIZE);
            ResultSet rs = statement.executeQuery();
            ProductDAO pdao = new ProductDAO();
            Map<Integer, Cart_Detail> carts = new LinkedHashMap<>();
            while (rs.next()) {
                c.setCartID(rs.getInt("CartId"));
                c.setUserId(user);
                Cart_Detail cartDe = new Cart_Detail();
                cartDe.setCart(c);
                cartDe.setProduct(pdao.getProductbyID(rs.getInt("ProductId")));;
                cartDe.setQuantity(rs.getInt("Quantity"));
                if(cartDe.getQuantity()!= 0){
                carts.put(rs.getInt("ProductId"), cartDe);
                }
            }
            c.setCarts(carts);
        } catch (Exception e) {
        }
        return c;
    }
    
public Cart getCartByCidAndCartId(User user, int keywordInt) {
        Cart c = new Cart();
        try {
            String sql = " select a.*, p.CategoryID from\n"
                    + "(select c.*, cd.ProductId, cd.Quantity from Cart c left join Cart_Detail cd\n"
                    + "on c.CartId = cd.CartId\n"
                    + "where c.UserId = ?)a left join Product p \n"
                    + "on a.ProductId = p.ProductId\n"
                    + "where p.CategoryID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setInt(2, keywordInt);
            
            ResultSet rs = statement.executeQuery();
            ProductDAO pdao = new ProductDAO();
            Map<Integer, Cart_Detail> carts = new LinkedHashMap<>();
            while (rs.next()) {
                c.setCartID(rs.getInt("CartId"));
                c.setUserId(user);
                Cart_Detail cartDe = new Cart_Detail();
                cartDe.setCart(c);
                cartDe.setProduct(pdao.getProductbyID(rs.getInt("ProductId")));;
                cartDe.setQuantity(rs.getInt("Quantity"));
                if(cartDe.getQuantity()!= 0){
                carts.put(rs.getInt("ProductId"), cartDe);
                }
            }
            c.setCarts(carts);
        } catch (Exception e) {
        }
        return c;
    }

    public Cart getCartBySearchNamAndCartId(User user, String skeyword) {
        Cart c = new Cart();
        try {
            String sql = "select b.*,cg.CategoryName from \n"
                    + "(select a.*, p.CategoryID, p.ProductName from\n"
                    + "(select c.*, cd.ProductId, cd.Quantity from Cart c left join Cart_Detail cd\n"
                    + "on c.CartId = cd.CartId\n"
                    + "where c.UserId = ?)a left join Product p \n"
                    + "on a.ProductId = p.ProductId)b left join Category cg\n"
                    + "on b.CategoryID = cg.CategoryID\n"
                    + "where b.ProductName like ? or cg.CategoryName like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setString(2, "%" + skeyword + "%");
            statement.setString(3, "%" + skeyword + "%");
            
            ResultSet rs = statement.executeQuery();
            ProductDAO pdao = new ProductDAO();
            Map<Integer, Cart_Detail> carts = new LinkedHashMap<>();
            while (rs.next()) {
                c.setCartID(rs.getInt("CartId"));
                c.setUserId(user);
                Cart_Detail cartDe = new Cart_Detail();
                cartDe.setCart(c);
                cartDe.setProduct(pdao.getProductbyID(rs.getInt("ProductId")));;
                cartDe.setQuantity(rs.getInt("Quantity"));
                if(cartDe.getQuantity()!= 0){
                carts.put(rs.getInt("ProductId"), cartDe);
                }
            }
            c.setCarts(carts);
        } catch (Exception e) {
        }
        return c;
    }

    public Cart getCartByUserId(User user) {
        Cart c = new Cart();
        try {
            String sql = " select c.*, cd.ProductId, cd.Quantity from Cart c left join Cart_Detail cd\n"
                    + "on c.CartId = cd.CartId\n"
                    + "where c.UserId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            
            ResultSet rs = statement.executeQuery();
            ProductDAO pdao = new ProductDAO();
            Map<Integer, Cart_Detail> carts = new LinkedHashMap<>();
            while (rs.next()) {
                c.setCartID(rs.getInt("CartId"));
                c.setUserId(user);
                Cart_Detail cartDe = new Cart_Detail();
                cartDe.setCart(c);
                cartDe.setProduct(pdao.getProductbyID(rs.getInt("ProductId")));;
                cartDe.setQuantity(rs.getInt("Quantity"));
                if(cartDe.getQuantity()!= 0){
                carts.put(rs.getInt("ProductId"), cartDe);
                }
            }
            c.setCarts(carts);
        } catch (Exception e) {
        }
        return c;
    }
    
    

    public void removeCartById(int cartID) {
        try {
            String sql = "Delete from Cart where CartID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cartID);

            statement.executeUpdate();
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        CartDAO cdao = new CartDAO();
        System.out.println(cdao.getCartBySearchNamAndCartIdPaging(new UserDAO().getUserByEmail("namvdhe151370@fpt.edu.vn"), "b", 1, 4));
    }

}
