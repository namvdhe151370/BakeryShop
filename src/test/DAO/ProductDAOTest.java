/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Product;
import Model.Product_Image;
import Model.Product_Status;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hellb
 */
public class ProductDAOTest {
    
    public ProductDAOTest() {
    }
    
    

    /**
     * Test of addProduct method, of class ProductDAO.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        String productName = "Apan Cake";
        String title = "Cakekepl";
        String description = "delicion";
        String status = "1";
        int categoryID = 5;
        float price = 200;
        int quantity = 40;
        String thumbnail = "images";
        int weight = 0;
        int degree = 0;
        int time = 0;
        String createdate = "10/07/2022";
        float discount = 0.0F;
        ProductDAO instance = new ProductDAO();
        instance.addProduct(productName, title, description, status, categoryID, price, quantity, thumbnail, weight, degree, time, createdate, discount);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getSqlThumbnail method, of class ProductDAO.
     */
   
   
    
    
}
