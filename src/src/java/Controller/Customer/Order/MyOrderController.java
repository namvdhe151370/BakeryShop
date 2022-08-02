/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Order;

import DAO.CategoryDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Order;
import Model.Order_Details;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class MyOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int page_index;
            try {
                page_index = Integer.parseInt(request.getParameter("page_index"));
            } catch (Exception e) {
                page_index = 1;
            }
            int page_size = 2;
            int totalpage = 0;
            OrderDAO orderDAO = new OrderDAO();
            int count = orderDAO.getNumberOfRecordsOrdersByUserId(user.getId());
            if (count % page_size == 0) {
                totalpage = count / page_size;
            } else {
                totalpage = count / page_size + 1;
            }   
            if(page_index<1||page_index>totalpage) page_index=1;
            ArrayList<Order> listOrders = orderDAO.getListOrders(page_size, page_index, user.getId());
            for (Order o : listOrders) {
                float total = 0;
                for (Order_Details od : o.getListOder_Details()) {
                    total += od.getPrice() * od.getQuantity() * (1-od.getDiscount());
                }
                o.setTotalMoney((double) Math.floor(total * 100) / 100);
            }                        
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            request.setAttribute("listProducts", productDAO.getTopProduct(true));
            request.setAttribute("listCategories", categoryDAO.getAll(true));
            request.setAttribute("listOrders", listOrders);
            request.setAttribute("totalpage", totalpage);
            request.setAttribute("page_index", page_index);
            request.getRequestDispatcher("/View/Customer/Order/MyOrders.jsp").forward(request, response);
        } else {
            response.sendRedirect("/src/homepage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}