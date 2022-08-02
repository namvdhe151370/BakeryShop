/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Order;

import DAO.CartDAO;
import DAO.Cart_DetailDAO;
import Model.Cart;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HuyThai
 */
public class AddToCartFromOrder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int productID = Integer.parseInt(request.getParameter("productID"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.setAttribute("mess", "LoginForCart");
            request.getRequestDispatcher("../View/Homepage.jsp").forward(request, response);

        } else {
            CartDAO cartdao = new CartDAO();
            Cart cartUser = cartdao.getCartByUserID(user);

            if (cartUser.getCartID() == 0) {
                cartdao.InsertCart(user.getId());
                cartUser = cartdao.getCartByUserID(user);
            }

            if (cartUser.getCarts().containsKey(productID)) {
                new Cart_DetailDAO().UpdateCartDetail(productID, cartUser.getCartID(), cartUser.getCarts().get(productID).getQuantity() + 1);
            } else {
                new Cart_DetailDAO().InsertCartDetail(productID, cartUser.getCartID(), 1);
            }
//            String backUrl = (String) session.getAttribute("backUrl");
//            if (backUrl == null) {
//                backUrl = "/src/order/orderinformation";
//            }
            session.setAttribute("mess", "Product was added to Cart");
            session.setAttribute("cartUser", cartUser);
            response.sendRedirect("/src/order/orderinformation?OrderID="+orderId);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}