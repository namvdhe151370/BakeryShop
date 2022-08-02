/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Category;
import Model.Product;
import Model.Product_Image;
import Model.Product_Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ProductDAO extends DBContext {

    public void editProduct(String productName, String title, String description, String status, int categoryID, float price, int quantity, String thumbnail, int weight, int degree, int time, String createdate, float discount, int productID) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [ProductName] = ?\n"
                    + "      ,[Title] = ?\n"
                    + "      ,[Description] = ?\n"
                    + "      ,[StatusID] = ?\n"
                    + "      ,[CategoryID] = ?\n"
                    + "      ,[Price] = ?\n"
                    + "      ,[Quantity] = ?\n"
                    + "      ,[Thumbnail] = ?\n"
                    + "      ,[Weight] = ?\n"
                    + "      ,[Degree] = ?\n"
                    + "      ,[Time] = ?\n"
                    + "      ,[CreateDate] = ?\n"
                    + "      ,[Discount] = ?\n"
                    + " WHERE ProductId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, productName);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, status);
            statement.setInt(5, categoryID);
            statement.setFloat(6, price);
            statement.setInt(7, quantity);
            statement.setString(8, thumbnail);
            statement.setInt(9, weight);
            statement.setInt(10, degree);
            statement.setInt(11, time);
            statement.setString(12, createdate);
            statement.setFloat(13, discount);
            statement.setInt(14, productID);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    // Select 6 available product to show on homepage
    public List<Product> getProductforHomepage() {
        String sql = "select top 6 * from Product where StatusID = 1";
        List<Product> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product products = new Product();
                products.setProductID(rs.getInt("productId"));
                products.setProductName(rs.getString("ProductName"));
                products.setTitle(rs.getString("title"));
                products.setThumbnail(rs.getString("thumbnail"));
                products.setPrice(rs.getFloat("price"));
                products.setWeight(rs.getInt("weight"));
                products.setTime(rs.getInt("time"));
                products.setDegree(rs.getInt("degree"));
                products.setDiscount(rs.getFloat("discount"));
                ls.add(products);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;
    }

    public List<Product> getListProduct() {
        String query = "select * from product p inner join Product_Status ps\n"
                + "on p.StatusID = ps.ProductStatusID \n"
                + "inner join Category c\n"
                + "on p.CategoryID = c.CategoryID";
        List<Product> ls = new ArrayList<>();

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("categoryName"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getString("Status"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                ls.add(p);

            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    public List<Product_Status> getAllProductStatus() {
        String query = "select * from Product_Status\n"
                + "order by ProductStatusID desc";
        List<Product_Status> ls = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product_Status productstatus = new Product_Status();
                productstatus.setProductStatusID(rs.getInt("productStatusID"));
                productstatus.setStatus(rs.getString("status"));
                ls.add(productstatus);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    public List<Product> search(String productname) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Product WHERE ProductName like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + productname + "%");
            ResultSet rs = statement.executeQuery();
            List<Product> ls = new ArrayList<>();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                ls.add(p);

            }
            return ls;
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getTopProduct(boolean status) {
        String query = "select top 3* from Product where StatusID= ? order by CreateDate desc ";
        List<Product> ls = new ArrayList<>();

        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                ls.add(p);

            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<Product> getlistProductByCid(int categoryid) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Product WHERE CategoryID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalProduct() {
        try {
            String sql = "select COUNT(ProductID) from Product";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Product> getListProductPagg(int page, int PAZE_SIZE) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product order by CreateDate desc\n"
                    + "offset (?-1)*? row fetch next ? rows only";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, page);
            statement.setInt(2, PAZE_SIZE);
            statement.setInt(3, PAZE_SIZE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductbyID(int pid) {
        try {
            String sql = "select p.*,c.*,ps.[Status] as StatusName from Product p inner join Category c\n"
                    + "on p.CategoryID = c.CategoryID\n"
                    + "inner join Product_Status ps\n"
                    + "on p.StatusID = ps.ProductStatusID\n"
                    + "where ProductId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product a = new Product();
                a.setProductID(rs.getInt("ProductID"));
                a.setProductName(rs.getString("ProductName"));
                a.setTitle(rs.getString("Title"));
                a.setDescription(rs.getString("Description"));
                a.setStatus(rs.getBoolean("StatusID"));
                a.setStatusName(rs.getString("StatusName"));
                Category cate = new Category();
                cate.setCategoryID(rs.getInt("categoryID"));
                cate.setCategoryName(rs.getString("categoryName"));
                a.setCategoryID(cate);
                a.setPrice(rs.getFloat("Price"));
                a.setQuantity(rs.getInt("Quantity"));
                a.setThumbnail(rs.getString("Thumbnail"));
                a.setWeight(rs.getInt("Weight"));
                a.setDegree(rs.getInt("Degree"));
                a.setTime(rs.getInt("Time"));
                a.setCreatedate(rs.getDate("CreateDate"));
                a.setDiscount(rs.getFloat("Discount"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> get4LastestProduct() {
        String query = "select top 4* from Product where StatusID= 1 order by CreateDate desc";
        List<Product> ls = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                ls.add(p);

            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<Product_Image> getAllProductImageByID(int piid) {
        List<Product_Image> list = new ArrayList<>();
        try {
            String sql = "select * from Product_Image "
                    + "where ProductID =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, piid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product_Image a = new Product_Image();
                a.setImage(rs.getString("image"));
                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getTotalProductByCid(boolean status, int keyword) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select COUNT(ProductID) from Product WHERE StatusID = ? and CategoryID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            statement.setInt(2, keyword);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Product> getListProductByCidPaging(boolean status, int categoryid, int page, int PAZE_SIZE) {
        String sql = "SELECT * FROM Product WHERE StatusID = ? and CategoryID = ?\n"
                + "order by CreateDate desc\n"
                + "offset (?-1)*? row fetch next ? rows only";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            statement.setInt(2, categoryid);
            statement.setInt(3, page);
            statement.setInt(4, PAZE_SIZE);
            statement.setInt(5, PAZE_SIZE);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int getTotalProductBySearchName(boolean status, String keyword) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select COUNT(ProductID) from Product WHERE StatusID = ? and ProductName like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            statement.setString(2, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Product> getListProductBySearchNamePaging(boolean status, String keyword, int page, int PAZE_SIZE) {
        String sql = "SELECT * FROM Product WHERE StatusID = ? and ProductName like ?\n"
                + "order by CreateDate desc\n"
                + "offset (?-1)*? row fetch next ? rows only";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            statement.setString(2, "%" + keyword + "%");
            statement.setInt(3, page);
            statement.setInt(4, PAZE_SIZE);
            statement.setInt(5, PAZE_SIZE);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<Product> getListProductPaging(boolean status, int page, int PAZE_SIZE) {
        String sql = "select * from Product\n"
                + "where StatusID = ?\n"
                + "order by CreateDate desc\n"
                + "offset (?-1)*? row fetch next ? rows only";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            statement.setInt(2, page);
            statement.setInt(3, PAZE_SIZE);
            statement.setInt(4, PAZE_SIZE);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int getTotalProductByCidAndCartId(boolean status, int keywordInt, int cartID) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select COUNT(a.ProductID) from\n"
                    + "(select p.* from Cart_Detail cd left join Product p\n"
                    + "on cd.ProductId =p.ProductId \n"
                    + "where p.StatusID = ? and p.CategoryID = ? and cd.CartId = ?)a";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            statement.setInt(2, keywordInt);
            statement.setInt(3, cartID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getTotalProductBySearchNameAndCartId(String skeyword, int cartID) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select COUNT(b.ProductId) from\n"
                    + "(select a.*, c.CategoryName from\n"
                    + "(select p.* from Cart_Detail cd left join Product p\n"
                    + "on cd.ProductId =p.ProductId\n"
                    + "where cd.CartId = ?)a left join Category c\n"
                    + "on a.CategoryID = c.CategoryID\n"
                    + "where a.ProductName like ? or c.CategoryName like ?)b";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cartID);
            statement.setString(2, "%" + skeyword + "%");
            statement.setString(3, "%" + skeyword + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getTotalProductByCartId(int cartID) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select Count(cd.ProductId) from Cart_Detail cd left join Product p\n"
                    + "on cd.ProductId =p.ProductId where cd.CartId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cartID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Product> filterAllProductByStatusAndCategory(String status, String Category) {
        String sql = "select *\n"
                + "from Product p inner join Category c \n"
                + "on p.CategoryID = c.CategoryID\n"
                + "where  c.categoryName like ? and p.status like ?";
        List<Product> ls = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + Category + "%");
            stm.setString(2, "%" + status + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("categoryName"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                ls.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ls;

    }

    public int updateProductStatus(Product p) {
        int n = 0;
        String sql = "Update Product set StatusID = ?  where ProductId = ?";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, p.isStatus());
            stm.setInt(2, p.getProductID());
            n = stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void addProduct(String productName, String title, String description, String status, int categoryID, float price, int quantity, String thumbnail, int weight, int degree, int time, String createdate, float discount) {
        try {
            String sql = "INSERT INTO [dbo].[Product]\n"
                    + "           ([ProductName]\n"
                    + "           ,[Title]\n"
                    + "           ,[Description]\n"
                    + "           ,[StatusID]\n"
                    + "           ,[CategoryID]\n"
                    + "           ,[Price]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[Thumbnail]\n"
                    + "           ,[Weight]\n"
                    + "           ,[Degree]\n"
                    + "           ,[Time]\n"
                    + "           ,[CreateDate]\n"
                    + "           ,[Discount])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, productName);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, status);
            statement.setInt(5, categoryID);
            statement.setFloat(6, price);
            statement.setInt(7, quantity);
            statement.setString(8, thumbnail);
            statement.setInt(9, weight);
            statement.setInt(10, degree);
            statement.setInt(11, time);
            statement.setString(12, createdate);
            statement.setFloat(13, discount);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Product getSqlThumbnail(int id) {
        Product p = new Product();
        try {

            String sql = "select thumbnail from Product\n"
                    + "where ProductId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                p.setThumbnail(rs.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public List<Product> getListProductByCid(boolean status, int categoryid) {
        String sql = "SELECT * FROM Product WHERE StatusID = ? and CategoryID = ?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            statement.setInt(2, categoryid);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<Product> getListProductBySearchName(boolean status, String urlkeyword) {
        String sql = "SELECT * FROM Product WHERE StatusID = ? and ProductName like ?\n"
                + "order by CreateDate desc\n"
                + "offset (?-1)*? row fetch next ? rows only";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            statement.setString(2, "%" + urlkeyword + "%");
            
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;    }

    public List<Product> getListProduct(boolean status) {
        String sql = "select * from Product\n"
                + "where StatusID = ?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getBoolean("StatusID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getInt("Weight"),
                        rs.getInt("Degree"),
                        rs.getInt("Time"),
                        rs.getDate("CreateDate"),
                        rs.getFloat("Discount"),
                        c);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public void updateQuantityProduct(int i, int productID) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [Quantity] = ?  \n"
                + " WHERE ProductId = ?";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, i);
            stm.setInt(2, productID);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateOutOfStockPro(int productID) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "SET StatusID = 0 \n"
                + "WHERE ProductId = ? ";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, productID);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getCountProductInRange(String before, String then) {
        int n = 0;
        try {
            String sql = "SELECT count(*)\n"
                    + "FROM [Product]\n"
                    + "where CreateDate between ? and ?";
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

    public int getCountProductInRange(String status, String before, String then) {
        int n = 0;
        try {
            String sql = "SELECT count(*)\n"
                    + "FROM [Product]  p inner join Product_Status  ps on ps.ProductStatusID = p.StatusID\n"
                    + "where  p.StatusID = ? and CreateDate between ? and ?  ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, before);
            stm.setString(3, then);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int getCountProduct() {
        int n = 0;
        try {
            String sql = "SELECT count(*)\n"
                    + "FROM [Product] ";
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
       
//        ls = new ProductDAO().getListProduct();
//        for (Product l : ls) {
//            System.out.println(l);
//        }


//Product p = new Product();
//p.setProductID(1);
//p.setStatus(true);
//    int n = new ProductDAO().updateProductStatus(p);
//        System.out.println(n);

        System.out.println(new ProductDAO().getProductforHomepage());
    }
}
