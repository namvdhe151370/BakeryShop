/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer.Order;

import DAO.CartDAO;
import DAO.Cart_DetailDAO;
import DAO.OrderDAO;
import DAO.OrderDetailsDAO;
import Model.Cart;
import Model.Order;
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
public class UpdateQuantityOrderController extends HttpServlet {

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
        int orderid = Integer.parseInt(request.getParameter("orderId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        OrderDAO od = new OrderDAO();
        Order order = od.getOrdersByOrderId(orderid);
        request.setAttribute("order", order);
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        CartDAO cartdao = new CartDAO();
//        Cart cartUser = cartdao.getCartByUserID(user);
//        Cart_DetailDAO cddao = new Cart_DetailDAO();
//        int maxQuantity = cddao.getMaxQuantityByProDuctId(productId, cartUser.getCartID());
//        if (cartUser.getCarts().containsKey(productId)) {
//            if (quantity > maxQuantity) {
//                cddao.UpdateCartDetail(productId, cartUser.getCartID(), maxQuantity);
//            } else {
//                cddao.UpdateCartDetail(productId, cartUser.getCartID(), quantity);
//            }
//        }
        OrderDetailsDAO odd = new OrderDetailsDAO();
        odd.UpdateQuantity(productId, orderid, quantity);
        response.sendRedirect("/src/order/updateorder?OrderId="+orderid);
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