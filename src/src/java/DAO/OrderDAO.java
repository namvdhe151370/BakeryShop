/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Order;
import Model.Order_Details;
import Model.Order_Status;
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
 * @author ADMIN
 */
public class OrderDAO extends DBContext {

    public ArrayList<Order> getListOrders(ResultSet rs) {
        ArrayList<Order> listOrders = new ArrayList<>();
        try {
            while (rs.next()) {
                Order o = new Order();
                o.setOderID(rs.getInt("OrderId"));
                o.setSaleId(rs.getInt("SaleId"));
                o.setOrderDate(rs.getDate("OrderDate"));
                o.setSaleNote(rs.getString("SaleNote"));
//                o.setOrderStatus(rs.getNString("OrderStatus"));
                o.setOrderStatus(rs.getInt("OrderStatus"));
                o.setStatus(rs.getString("status"));
                User u = new User();
                u.setId(rs.getInt("UserId"));
                o.setUserId(u);
                OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
                o.setListOder_Details(orderDetailsDAO.getOrder_DetailsByOrderId(o.getOderID()));
                o.setUpdated(rs.getDate("LastUpdated"));
                listOrders.add(o);
            }
            return listOrders;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Order> getOrdersByUserId(int userId) {
        try {
            String sql = "select *from [Order] as a join Order_Status as b on a.OrderStatus = b.OrderStatusID where UserId=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            return getListOrders(rs);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Order> getListOrders(int page_size, int page_index, int userId) {
        try {
            String sql = "select *from (select *, ROW_NUMBER() OVER(order by OrderId asc) as row_index from [Order] as a join Order_Status as b on a.OrderStatus = b.OrderStatusID where UserId=?) as tbl\n"
                    + "where row_index>=?*(?-1)+1 and row_index<=?*?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, page_size);
            st.setInt(3, page_index);
            st.setInt(4, page_size);
            st.setInt(5, page_index);
            ResultSet rs = st.executeQuery();
            return getListOrders(rs);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
        for (Order x : o.getListOrders(2, 7, 2)) {
            System.out.println(x.getOderID() + "  " + x.getListOder_Details().size());
        }

    }

//    Get number of records by (userId, total numbers records,....)
    public int getNumberOfRecordsOrdersByCondition(String sql) {
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("total");
                return count;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int getNumberOfRecordsOrdersByUserId(int userId) {
        String sql = "Select Count (*) as total from [Order] where userId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("total");
                return count;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Order getOrdersByOrderId(int orderId) {
        try {
            String sql = "select *from [Order] as a join Order_Status as b on a.OrderStatus = b.OrderStatusID where OrderId = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            return getListOrders(rs).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Order_Details> getAllOrderID() {
        List<Order_Details> ls = new ArrayList<>();
        try {

            String sql = "select orderid from [order]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOderID(rs.getInt(1));
                Order_Details od = new Order_Details();
                od.setOrderId(o);
                ls.add(od);

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public int createReturnId(Order order) {
        try {
            String sql = "INSERT INTO [Order] \n"
                    + "(UserId, OrderStatus, TotalMoney, ShipInfoID)\n"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId().getId());
            ps.setInt(2, order.getOrderStatus());
            ps.setDouble(3, order.getTotalMoney());
            ps.setInt(4, order.getShipInfoID());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // get latest order by userid
    public int getLastestOrder(int userid) {
        try {
            String sql = "select top 1 OrderId from [Order] where UserId = ? \n"
                    + "order by OrderId desc";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //get Address to ship 
    public String getAddressShip() {
        try {
            String sql = "  select top 1 Address from Ship_Information \n"
                    + "  order by ShipInfoID desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getOrderNote() {
        try {
            String sql = " select top 1 Notes from Ship_Information\n"
                    + "order by ShipInfoID desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // insert orderid and saleid roltatesly

    public void UpdateSaleID(int saleid) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET [SaleId] = ?\n"
                + " WHERE OrderId = (select top 1 OrderId from [Order] order by OrderId desc)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, saleid);
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //update orderstatus

    public void UpdateOrderStatus(int orderId) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET [OrderStatus] = 3\n"
                + " WHERE OrderId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Order> getListOrderforSale() {
        String sql = "select o.OrderId, u.[Name] as CusName, o.TotalMoney,us.[Name] as SaleName, o.OrderDate, os.[Status] from [Order] o inner join [User] u \n"
                + "on o.UserId = u.Id\n"
                + "inner join Order_Status os\n"
                + "on os.OrderStatusID = o.OrderStatus\n"
                + "inner join [User] us\n"
                + "on o.SaleId=us.Id";
        List<Order> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setName(rs.getString("CusName"));
                Order o = new Order();
                o.setOderID(rs.getInt("OrderId"));
                o.setUserId(u);
                o.setTotalMoney(rs.getFloat("TotalMoney"));
                o.setOrderDate(rs.getDate("OrderDate"));
                o.setSaleName(rs.getString("SaleName"));
                o.setStatus(rs.getString("Status"));
                ArrayList<Order_Details> lod = new OrderDAO().getListOrderDetailsByOrderID(o.getOderID());
                o.setListOder_Details(lod);
                ls.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    public ArrayList<Order_Details> getListOrderDetailsByOrderID(int orderID) {
        String sql = "SELECT od.*, p.ProductName FROM OrderDetail od inner join Product p\n"
                + "on od.ProductID = p.ProductId\n"
                + "where od.OrderId=?";
        ArrayList<Order_Details> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderID);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                Order o = new Order();
                o.setOderID(rs.getInt("OrderId"));
                Order_Details od = new Order_Details();
                od.setOrderDetailID(rs.getInt("OrderDetailID"));
                od.setOrderId(o);
                od.setProductId(p);
                od.setDiscount(rs.getFloat("discount"));
                od.setPrice(rs.getFloat("price"));
                od.setQuantity(rs.getInt("quantity"));
                ls.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    public List<Order_Status> getAllOrderStatus() {

        String query = "select * from Order_Status";
        List<Order_Status> ls = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order_Status od = new Order_Status(rs.getInt(1), rs.getString(2));
                ls.add(od);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    //get Count Order by date and status
    public int getCountOrderByDateAndStatus(String from, String to, int status) {
        int total = 0;
        try {
            String sql = "select count(OrderId) from [Order] \n"
                    + "where OrderDate > = ? and OrderDate < = ? and OrderStatus = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, from);
            statement.setString(2, to);
            statement.setInt(3, status);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
        }
        return total;
    }

    public int getCountAllOrderByDate(String from, String to) {
        int total = 0;
        try {
            String sql = "select count(OrderId) from [Order] \n"
                    + "where OrderDate > = ? and OrderDate < = ? ";
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

    public int getCountCusBoughtByDate(String from, String to) {
        int total = 0;
        try {
            String sql = "select count(a.UserId) from \n"
                    + "(select count(OrderId) as counta, UserId from [Order]  where OrderDate > = ? and OrderDate < = ?\n"
                    + " group by UserId)a";
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

    public double getCountAllRevenuesByDate(String from, String to) {
        double total = 0;
        try {
            String sql = " select CONVERT( numeric(10,2), c.sumtotal) from\n"
                    + "(select sum(b.Price*b.Quantity*(1-b.Discount)) as sumtotal from\n"
                    + "(select a.*, o.OrderDate from \n"
                    + "(select od.*, p.CategoryID from [OrderDetail] od inner join Product p \n"
                    + "on od.ProductID = p.ProductId )a inner join [Order] o\n"
                    + "on a.OrderID = o.OrderId\n"
                    + "where OrderDate > = ? and OrderDate < = ? )b)c";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, from);
            statement.setString(2, to);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (SQLException e) {
        }
        return total;
    }

    public int getCountOrderByDate(String date) {
        int n = 0;
        try {
            String sql = "select COUNT(*) from [Order] where CONVERT(VARCHAR(25), OrderDate, 126)  like ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + date + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public double getCountRevenuesByDateAndCateId(String from, String to, int i) {
        double total = 0;
        try {
            String sql = "select CONVERT( numeric(10,2), c.sumtotal) from\n"
                    + "(select sum(b.Price*b.Quantity*(1-b.Discount)) as sumtotal from\n"
                    + "(select a.*, o.OrderDate from \n"
                    + "(select od.*, p.CategoryID from [OrderDetail] od inner join Product p \n"
                    + "on od.ProductID = p.ProductId where p.CategoryID = ?)a inner join [Order] o\n"
                    + "on a.OrderID = o.OrderId\n"
                    + "where OrderDate > = ? and OrderDate < = ? )b)c";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, i);
            statement.setString(2, from);
            statement.setString(3, to);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
        }
        return total;
    }
    
    public double getRevenuesbyDay(String saleID, String day, String status) {
        double n = 0;
        String sql = "SELECT sum(o.TotalMoney)\n"
                + "  FROM [Order] o inner join [User] u \n"
                + "  on o.SaleId = u.Id\n"
                + "  inner join Order_Status os on o.OrderStatus = os.OrderStatusID\n"
                + "  where o.SaleId like ? \n"
                + "  and CONVERT(date, OrderDate, 126) = ?\n"
                + "  and o.OrderStatus like ?";
        ArrayList<Order> ls = new ArrayList<>();

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + saleID + "%");
            stm.setString(2, day);

            stm.setString(3, "%" + status + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                n = rs.getDouble(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int getOrderbyDay(String saleID, String day, String status) {
        int n = 0;
        String sql = "SELECT count(o.OrderId)\n"
                + "  FROM [Order] o inner join [User] u \n"
                + "  on o.SaleId = u.Id\n"
                + "  inner join Order_Status os on o.OrderStatus = os.OrderStatusID\n"
                + "  where o.SaleId like ? \n"
                + "  and CONVERT(date, OrderDate, 126) = ?\n"
                + "  and o.OrderStatus like ?";
        ArrayList<Order> ls = new ArrayList<>();

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + saleID + "%");
            stm.setString(2, day);

            stm.setString(3, "%" + status + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                n = rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    // cancel order
    public void CancelOrder(int orderId) {
        try {
            String sql = "UPDATE [dbo].[Order]\n"
                    + "   SET [OrderStatus] = 1\n"
                    + " WHERE OrderId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }
    // delete product order
    public void DeleteProductOrder(int orderId, int productId) {
        try {
            String sql = "DELETE FROM [dbo].[OrderDetail]\n"
                    + "      WHERE OrderID = ? and ProductID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }
    //get ordernote by ship id

    public String getOrderNoteByShipID(int shipid) {
        try {
            String sql = "  select top 1 Notes from Ship_Information\n"
                    + "  where ShipInfoID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, shipid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //get list order status

    public List<String> getOrderStatusList() {
        String sql = "  select distinct [OrderStatusID] from [Order_Status]";
        List<String> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String a = rs.getString("OrderStatusID");
                ls.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    //get list sale

    public List<String> getSaleList() {
        String sql = "  SELECT [Id] \n"
                + "FROM\n"
                + "    [User] where RoleID = 2\n"
                + "		order by Id asc;";
        List<String> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String a = rs.getString("Id");
                ls.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    //update orderstatus and saleid

    public void UpdateOrderStatusAndSaleId(int saleid, int orderstatus, int orderId) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET [SaleId] = ?\n"
                + "      ,[OrderStatus] = ?\n"
                + " WHERE OrderId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, saleid);
            stm.setInt(2, orderstatus);
            stm.setInt(3, orderId);
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //update ordernote

    public void UpdateOrderNote(String note, int shipid) {
        String sql = "UPDATE [dbo].[Ship_Information]\n"
                + "   SET [Notes] = ?\n"
                + " WHERE ShipInfoID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, note);
            stm.setInt(2, shipid);
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
